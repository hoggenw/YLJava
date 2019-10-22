package com.hoggen.COMangerment.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

public class BMPConvertJPGUtil {

	/**
	 * 图片格式转换 BMP -> JPG
	 * 
	 * @param file
	 * @param dstFile
	 */
	public static InputStream bmpTojpg(InputStream bmpInputStream) {
		try {

			Image TheImage = ImageIO.read(bmpInputStream);
			// Image TheImage = read(bmpInputStream);
			int wideth = TheImage.getWidth(null);
			int height = TheImage.getHeight(null);

//			BufferedImage newbuff = JAI.create("stream", new MemoryCacheImageInputStream(bmpInputStream))
//					.getAsBufferedImage();

			BufferedImage tag = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(TheImage, 0, 0, wideth, height, null);

			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
			ImageIO.write(tag, "jpeg", imOut);
			InputStream is = new ByteArrayInputStream(bs.toByteArray());

			return is;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static int constructInt(byte[] in, int offset) {
		int ret = ((int) in[offset + 3] & 0xff);
		ret = (ret << 8) | ((int) in[offset + 2] & 0xff);
		ret = (ret << 8) | ((int) in[offset + 1] & 0xff);
		ret = (ret << 8) | ((int) in[offset + 0] & 0xff);
		return (ret);
	}

	public static int constructInt3(byte[] in, int offset) {
		int ret = 0xff;
		ret = (ret << 8) | ((int) in[offset + 2] & 0xff);
		ret = (ret << 8) | ((int) in[offset + 1] & 0xff);
		ret = (ret << 8) | ((int) in[offset + 0] & 0xff);
		return (ret);
	}

	public static long constructLong(byte[] in, int offset) {
		long ret = ((long) in[offset + 7] & 0xff);
		ret |= (ret << 8) | ((long) in[offset + 6] & 0xff);
		ret |= (ret << 8) | ((long) in[offset + 5] & 0xff);
		ret |= (ret << 8) | ((long) in[offset + 4] & 0xff);
		ret |= (ret << 8) | ((long) in[offset + 3] & 0xff);
		ret |= (ret << 8) | ((long) in[offset + 2] & 0xff);
		ret |= (ret << 8) | ((long) in[offset + 1] & 0xff);
		ret |= (ret << 8) | ((long) in[offset + 0] & 0xff);
		return (ret);
	}

	public static double constructDouble(byte[] in, int offset) {
		long ret = constructLong(in, offset);
		return (Double.longBitsToDouble(ret));
	}

	public static short constructShort(byte[] in, int offset) {
		short ret = (short) ((short) in[offset + 1] & 0xff);
		ret = (short) ((ret << 8) | (short) ((short) in[offset + 0] & 0xff));
		return (ret);
	}

	static class BitmapHeader {
		public int iSize, ibiSize, iWidth, iHeight, iPlanes, iBitcount, iCompression, iSizeimage, iXpm, iYpm, iClrused,
				iClrimp;

		// 读取bmp文件头信息
		public void read(InputStream fs) throws IOException {
			final int bflen = 14;
			byte bf[] = new byte[bflen];
			fs.read(bf, 0, bflen);
			final int bilen = 40;
			byte bi[] = new byte[bilen];
			fs.read(bi, 0, bilen);
			iSize = constructInt(bf, 2);
			ibiSize = constructInt(bi, 2);
			iWidth = constructInt(bi, 4);
			iHeight = constructInt(bi, 8);
			iPlanes = constructShort(bi, 12);
			iBitcount = constructShort(bi, 14);
			iCompression = constructInt(bi, 16);
			iSizeimage = constructInt(bi, 20);
			iXpm = constructInt(bi, 24);
			iYpm = constructInt(bi, 28);
			iClrused = constructInt(bi, 32);
			iClrimp = constructInt(bi, 36);
		}
	}

	public static Image read(InputStream fs) {
		try {
			BitmapHeader bh = new BitmapHeader();
			bh.read(fs);
			System.out.println("bh.iBitcount: " + bh.iBitcount);
			if (bh.iBitcount == 24) {
				return (readImage24(fs, bh));
			}
			if (bh.iBitcount == 32 || bh.iBitcount == 16) {
				return (readImage32(fs, bh));
			}
			fs.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return (null);
	}

	// 24位
	protected static Image readImage24(InputStream fs, BitmapHeader bh) throws IOException {
		Image image;
		if (bh.iSizeimage == 0) {
			bh.iSizeimage = ((((bh.iWidth * bh.iBitcount) + 31) & ~31) >> 3);
			bh.iSizeimage *= bh.iHeight;
		}
		int npad = (bh.iSizeimage / bh.iHeight) - bh.iWidth * 3;
		int ndata[] = new int[bh.iHeight * bh.iWidth];
		byte brgb[] = new byte[(bh.iWidth + npad) * 3 * bh.iHeight];
		fs.read(brgb, 0, (bh.iWidth + npad) * 3 * bh.iHeight);
		int nindex = 0;
		for (int j = 0; j < bh.iHeight; j++) {
			for (int i = 0; i < bh.iWidth; i++) {
				ndata[bh.iWidth * (bh.iHeight - j - 1) + i] = constructInt3(brgb, nindex);
				nindex += 3;
			}
			nindex += npad;
		}
		image = Toolkit.getDefaultToolkit()
				.createImage(new MemoryImageSource(bh.iWidth, bh.iHeight, ndata, 0, bh.iWidth));
		fs.close();
		return (image);
	}

//	// 16位
//	protected static Image readImage16(InputStream fs, BitmapHeader bh) throws IOException {
//		Image image;
//		int ndata[] = new int[bh.iHeight * bh.iWidth];
//		byte brgb[] = new byte[bh.iWidth * 2 * bh.iHeight];
//		fs.read(brgb, 0, bh.iWidth * 2 * bh.iHeight);
//		int nindex = 0;
//		for (int j = 0; j < bh.iHeight; j++) {
//			for (int i = 0; i < bh.iWidth; i++) {
//				ndata[bh.iWidth * (bh.iHeight - j - 1) + i] = constructInt3(brgb, nindex);
//				nindex += 2;
//			}
//		}
//		image = Toolkit.getDefaultToolkit()
//				.createImage(new MemoryImageSource(bh.iWidth, bh.iHeight, ndata, 0, bh.iWidth));
//		fs.close();
//		return (image);
//	}

	// 32位
	protected static Image readImage32(InputStream fs, BitmapHeader bh) throws IOException {
		Image image;
		int ndata[] = new int[bh.iHeight * bh.iWidth];
		byte brgb[] = new byte[bh.iWidth * 4 * bh.iHeight];
		fs.read(brgb, 0, bh.iWidth * 4 * bh.iHeight);
		int nindex = 0;
		for (int j = 0; j < bh.iHeight; j++) {
			for (int i = 0; i < bh.iWidth; i++) {
				ndata[bh.iWidth * (bh.iHeight - j - 1) + i] = constructInt3(brgb, nindex);
				nindex += 4;
			}
		}
		image = Toolkit.getDefaultToolkit()
				.createImage(new MemoryImageSource(bh.iWidth, bh.iHeight, ndata, 0, bh.iWidth));
		fs.close();
		return (image);
	}

}
