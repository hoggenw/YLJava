package com.hoggen.testFrame.errors;

/**
 * @author baogang
 */
public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(String resource, String identity) {
        super("识别码为:" + identity + "的资源【" + resource + "】尚未创建");
    }

}
