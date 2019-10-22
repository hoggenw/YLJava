package hello.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * java中的properties文件是一种配置文件，主要用于表达配置信息，
 * 文件类型为*.properties，格式为文本文件，文件的内容是格式是"键=值"的格式，在properties
 *文件中，可以用"#"来作注释，properties文件在Java编程中用到的地方很多，操作很方便。
 * Properties类的重要方法
 Properties 类存在于胞 Java.util 中，该类继承自 Hashtable
 1． getProperty ( String  key) ，   用指定的键在此属性列表中搜索属性。也就是通过参数 key ，得到 key 所对应的 value。
 2． load ( InputStream  inStream) ，从输入流中读取属性列表（键和元素对）。通过对指定的文件（比如说上面的 test.properties 文件）进行装载来获取该文

 件中的所有键 - 值对。以供 getProperty ( String  key) 来搜索。
 3． setProperty ( String  key, String  value) ，调用 Hashtable 的方法 put 。他通过调用基类的put方法来设置 键 - 值对。
 4． store ( OutputStream  out, String  comments) ，   以适合使用 load 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素

 对）写入输出流。与 load 方法相反，该方法将键 - 值对写入到指定的文件中去。
 5． clear () ，清除所有装载的 键 - 值对。该方法在基类中提供。
 * */


@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
