package hello;

import hello.storage.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileUploadIntegrationTests {
    /**
     * @Autowired注释进行自动注入时，spring容器中匹配的候选Bean数目必须有且仅有一个。
    当找不到一个匹配的Bean时，spring容器将抛出BeanCreationException异常，并指出必须至少拥有一个匹配的Bean。
    如果spring容器中拥有多个候选Bean，spring容器在启动时也会抛出BeanCreationException
    通过 @Autowired的使用来消除 set ，get方法。

     */
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StorageService storageService;

    @LocalServerPort
    private int port;

    @Test
    public void shouldUploadFile() throws Exception {
        ClassPathResource resource = new ClassPathResource("testupload.txt",getClass());
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<String,Object>();
        map.add("file",resource);
        ResponseEntity<String> response = this.restTemplate.postForEntity("/",map,String.class);
        assertThat(response.getStatusCode()).isEqualByComparingTo(HttpStatus.FOUND);
        assertThat((response.getHeaders().getLocation().toString()).startsWith("http://localhost:" + this.port + "/"));
        then(storageService).should().store(any(MultipartFile.class));
    }

    @Test
    public void shouldDownloadFile() throws Exception {
        ClassPathResource resource = new ClassPathResource("testupload.txt",getClass());
        given(this.storageService.loadAsResource("testupload.txt")).willReturn(resource);
        ResponseEntity<String> response = this.restTemplate
                .getForEntity("/files/{filename}",String.class,"testupload.txt");
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_DISPOSITION))
                .isEqualTo("attachment; filename=\"testupload.txt\"");
        assertThat(response.getBody()).isEqualTo("Spring Framework");


    }

}






























