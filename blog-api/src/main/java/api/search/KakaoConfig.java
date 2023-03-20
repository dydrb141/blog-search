package api.search;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

@Configuration
public class KakaoConfig implements ClientConfig {
/*	@Value("${app.service.kakao.api-key}")
	private String kakaoApiKey;

	@Value("${app.service.kakao.base-url}")
	private String kakaoBaseUrl;*/

	@Override
	public String getBaseUrl() {
		return "https://dapi.kakao.com";
	}

	@Override
	public Consumer<HttpHeaders> getDefaultHeaders() {
		Consumer<HttpHeaders> consumer = httpHeaders -> httpHeaders.add(org.springframework.http.HttpHeaders.AUTHORIZATION, "KakaoAK 56c46ee50c319532725d033c06dbf938");
		return consumer;
	}

	///naver clientId : Un5AhjOAUi0hc5uZWkLq
	//clientSecret  : re7BtxhYlU
}
