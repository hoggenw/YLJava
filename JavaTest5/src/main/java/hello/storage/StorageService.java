package hello.storage;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    /**
     * String getContentType()//获取文件MIME类型
     InputStream getInputStream()//后去文件流
     String getName() //获取表单中文件组件的名字
     String getOriginalFilename() //获取上传文件的原名
     long getSize()  //获取文件的字节大小，单位byte
     boolean isEmpty() //是否为空
     void transferTo(File dest)//保存到一个目标文件中。
     */
    void store(MultipartFile file);
   /**
    * Stream 作为 Java 8 的一大亮点，它与 java.io 包里的 InputStream 和 OutputStream 是完全不同的概念。
    * 它也不同于 StAX 对 XML 解析的 Stream，也不是 Amazon Kinesis 对大数据实时处理的 Stream。
    * Java 8 中的 Stream 是对集合（Collection）对象功能的增强，
    * 它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。
    *
    * Stream是元素的集合，这点让Stream看起来用些类似Iterator；
    *可以支持顺序和并行的对原Stream进行汇聚的操作；
    *
    * 最常用的创建Stream有两种途径：

    通过Stream接口的静态工厂方法（注意：Java8里接口可以带静态方法）；
    通过Collection接口的默认方法（默认方法：Default method，也是Java8中的一个新特性，
    就是接口中的一个带有实现的方法，后续文章会有介绍）–stream()，把一个Collection对象转换成Stream
    */
    Stream<Path> loadAll();

    //java.nio.file.Path 类。这个类是 NIO.2 的里程碑，所有应用程序中的 I/O 操作都和这个类密不可分
    Path load(String filename);

    Resource loadAsResource(String filename);
    void deleteAll();
}
