package com.hoggen.testFrame.errors;

public class DataHasCreateException extends  BusinessException{
    public DataHasCreateException(String resource, String identity) {
        super("识别码为:" + identity + "的资源【" + resource + "】已经创建");
    }
}
