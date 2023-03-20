package api.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class SearchController {

	@GetMapping("/hello")
	public KakaoDTO helloWorld() {
		return getBlogs();
	}

	private final WebClientConfig webClientConfig;
	private final KakaoConfig kakaoConfig;

	/*public WebClientResponseDTO getSearchBlog() {
		//PageRequest pageable = PageRequest.of(searchDTO.getPage(), searchDTO.getSize());

		KakaoDTO kakaoDTO = getBlogs(searchDTO, pageable);

		return new WebClientResponseDTO(kakaoDTO, pageable);
	}*/

	private KakaoDTO getBlogs() {
		WebClient webClient = webClientConfig.webClient(kakaoConfig);

		return webClient
				.get()
				.uri(uriBuilder -> uriBuilder
						.path("/v2/search/blog")
						.queryParam("query", "이효리")
						.queryParam("sort","accuracy")
						//.queryParam("page", pageable.getPageNumber())
						//.queryParam("size", pageable.getPageSize())
						.build()
				)
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, response ->
						response.bodyToMono(String.class).flatMap(body -> {
							//log.error("{}", body);
							//WebClientError webClientError = new ObjectMapper().readValue(body, WebClientError.class);
							return Mono.error(new IllegalAccessException("dfdf"));
						})
				)
				.onStatus(HttpStatusCode::is5xxServerError, response ->
						Mono.error(new RuntimeException())
				)
				.bodyToMono(KakaoDTO.class)
				.block();
	}
}
