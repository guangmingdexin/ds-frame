package ds.guang.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动：Sa-OAuth2 Server端 
 * @author kong 
 */
@SpringBootApplication
public class DsOAuth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsOAuth2ServerApplication.class, args);
//		DsManager.setSaTokenContext(new DsTokenContextForThreadLocal());
		System.out.println("\nSa-Token-OAuth Server端启动成功");

	}
	
}
