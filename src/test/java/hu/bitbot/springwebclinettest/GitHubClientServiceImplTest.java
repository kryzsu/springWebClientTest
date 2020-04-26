package hu.bitbot.springwebclinettest;

import java.net.URI;
import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hu.bitbot.springwebclinettest.dto.GitHubSearchResponseDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaResponseDto;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GitHubClientServiceImplTest {

	@Mock
	private ExchangeFunction exchangeFunction;
	@Mock
	private GithubHelper githubHelper;

	private GitHubClientServiceImpl gitHubClientService;

	@BeforeEach
	void init() {
		WebClient webClient = WebClient.builder()
				.exchangeFunction(exchangeFunction)
				.build();

		gitHubClientService = new GitHubClientServiceImpl(githubHelper, webClient);
	}

	ClientResponse buildMockSuccessResponse() throws JsonProcessingException {
		GitHubSearchResponseDto gitHubSearchResponseDto = new GitHubSearchResponseDto();
		String githubResponse = new ObjectMapper().writeValueAsString(gitHubSearchResponseDto);
		return ClientResponse.create(HttpStatus.OK)
				.body(githubResponse)
				.header("Content-Type", "application/json")
				.build();
	}

	@Test
	void isProjectJava() throws JsonProcessingException {
		// given
		IsProjectJavaDto isProjectJavaDto = new IsProjectJavaDto("test");

		when(exchangeFunction.exchange(anyObject())).thenReturn(
				Mono.just( buildMockSuccessResponse())
						 );

		when(githubHelper.prepareRequest(isProjectJavaDto)).thenReturn(URI.create("http://test"));
		when(githubHelper.processResponse(any(GitHubSearchResponseDto.class)))
				.thenReturn(new IsProjectJavaResponseDto(true));

		// when
		StepVerifier
				.create(gitHubClientService.isProjectJava(isProjectJavaDto))
				.expectNext( new IsProjectJavaResponseDto(true))
				.expectComplete()
				.verify();

		//then

	}

	@Test
	void isProjectJavaExceptionInWebClient_falseIsUsedAsDefault() {
		// given
		IsProjectJavaDto isProjectJavaDto = new IsProjectJavaDto("test");

		when(exchangeFunction.exchange(anyObject())).thenThrow(
				new RuntimeException()
		);

		when(githubHelper.prepareRequest(isProjectJavaDto)).thenReturn(URI.create("http://test"));

		// when
		StepVerifier
				.create(gitHubClientService.isProjectJava(isProjectJavaDto))
				.expectNext( new IsProjectJavaResponseDto(false))
				.expectComplete()
				.verify();

		//then

	}

	@Test
	void isProjectJavaExceptionDuringProcessTheResponse_falseIsUsedAsDefault() throws JsonProcessingException {
		// given
		IsProjectJavaDto isProjectJavaDto = new IsProjectJavaDto("test");

		when(exchangeFunction.exchange(anyObject())).thenReturn(
				Mono.just( buildMockSuccessResponse())
		);

		when(githubHelper.prepareRequest(isProjectJavaDto)).thenReturn(URI.create("http://test"));
		when(githubHelper.processResponse(any(GitHubSearchResponseDto.class)))
				.thenThrow( new RuntimeException());

		// when
		StepVerifier
				.create(gitHubClientService.isProjectJava(isProjectJavaDto))
				.expectNext( new IsProjectJavaResponseDto(false))
				.expectComplete()
				.verify();

		//then

	}

}