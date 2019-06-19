package sombrero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*
    *
    *     keytool -genkey
          -alias tomcat
          -storetype PKCS12
          -keyalg RSA
          -keysize 2048
          -keystore keystore.p12
          -validity 4000

    * */

    // 위 설정을 하면
    // 스프링 커넥터에 SSL 적용을 해줌.
    // (스프링은 하나의 커넥터만 사용.)


    /**
     * (base) sombrero104ui-MacBookPro:springboothttps sombrero104$ curl -I -k --http2 https://localhost:8080/hello
     * HTTP/1.1 200 OK
     * Connection: keep-alive
     * Content-Type: text/plain;charset=UTF-8
     * Content-Length: 13
     * Date: Wed, 19 Jun 2019 12:31:36 GMT
     */
}
