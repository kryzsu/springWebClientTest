package hu.bitbot.springwebclinettest;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import hu.bitbot.springwebclinettest.dto.GitHubSearchResponseDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class GitHubClientServiceImpl implements GitHubClientService {
	private final GithubHelper githubHelper;
 	private final WebClient webClient;

	@Override
	public Mono<IsProjectJavaResponseDto> isProjectJava(IsProjectJavaDto isProjectJavaDto) {
		return webClient
				.get()
					.uri(githubHelper.prepareRequest(isProjectJavaDto))
				.exchange()
				.doOnError( error -> {
					log.warn("http request error", error);
				} )
				.flatMap(response ->
						response.bodyToMono(GitHubSearchResponseDto.class)
								.map(githubHelper::processResponse))
				.doOnError( error -> {
					log.warn("Error in response process", error);
				} )
				.onErrorReturn( new IsProjectJavaResponseDto(false));
	}
}
