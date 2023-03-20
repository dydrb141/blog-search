package api.search;

import org.springframework.http.HttpHeaders;

import java.util.function.Consumer;

public interface ClientConfig {
	String getBaseUrl();

	Consumer<HttpHeaders> getDefaultHeaders();
}
