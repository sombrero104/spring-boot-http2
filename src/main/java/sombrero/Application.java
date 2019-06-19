package sombrero;

/*import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;*/
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
    // 커넥터가 하나이기 때문에
    // 하나인 커넥터에 SSL 설정을 하면
    // 더이상 HTTP를 받을 수 없음.. HTTPS만 받음.


    /**
     * http2 옵션을 주었지만 아직까진 HTTP/1.1 버전으로 받음.
     *
     * (base) sombrero104ui-MacBookPro:springboothttps sombrero104$ curl -I -k --http2 https://localhost:8080/hello
     * HTTP/1.1 200 OK
     * Connection: keep-alive
     * Content-Type: text/plain;charset=UTF-8
     * Content-Length: 13
     * Date: Wed, 19 Jun 2019 12:31:36 GMT
     */


    /**
     * 위에서 설정한 HTTPS를 받는 커넥터가 하나밖에 없기 때문에
     * 커넥터를 하나 더 생성 (http를 받는 커넥터)
     */
    /*@Bean
    public ServletWebServerFactory serverFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(ceateStandardConnector());
        return tomcat;
    }

    private Connector ceateStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(8080);
        return connector;
    }*/


    /**
     *
     * 위 처럼 커넥터를 두개 (http(8080), https(8443))로 만든 후에는
     * 아래와 같이 둘 다 호출됨.
     *
     * curl -I -k --http2 http://localhost:8080/hello
     * curl -I -k --http2 https://localhost:8443/hello
     *
     *
     */


    /**
     *
     * server.http2.enabled=true
     * 사용 옵션을 추가한 후
     * 서블릿 컨테이너를 undertow로 실행하면
     * 아래처럼 HTTP/2 결과가 나옴.
     *
     * (base) sombrero104ui-MacBookPro:springboothttps sombrero104$ curl -I -k --http2 https://localhost:8443/hello
     * HTTP/2 200
     * content-type: text/plain;charset=UTF-8
     * content-length: 13
     * date: Wed, 19 Jun 2019 13:12:58 GMT
     */
}
