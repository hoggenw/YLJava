package hello.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

//如果 Web 应用程序采用了经典的三层分层结构的话，
// 最好在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释，
// 而用 @Component 对那些比较中立的类进行注释。
@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLoaction;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLoaction = Paths.get(properties.getLocation());
    }



    @Override
    public void init() {
        try {
            Files.createDirectories(rootLoaction);
        }catch (IOException e) {
            throw  new StorageException("Could not initialize storage",e);
        }

    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw  new StorageException("Failed to store empty file" + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(),this.rootLoaction.resolve(file.getOriginalFilename()));
        }catch (IOException e) {
            throw  new StorageException("Failed to store empty file" + file.getOriginalFilename(),e);
        }
    }

    @Override
    public Stream<Path> loadAll() {

        try {
            return  Files.walk(this.rootLoaction,1)
                    .filter(path -> !path.equals(this.rootLoaction))
                    .map(path -> this.rootLoaction.relativize(path));//构造路劲
        }catch (IOException e) {
            throw new StorageException("Failed to read stored files",e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLoaction.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return  resource;
            }else  {
                throw  new StorageFileNotFoundException("Could not read file" + resource);
            }

        }catch (MalformedURLException e) {
            throw  new StorageFileNotFoundException("Could not read file",e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLoaction.toFile());
    }
}



























