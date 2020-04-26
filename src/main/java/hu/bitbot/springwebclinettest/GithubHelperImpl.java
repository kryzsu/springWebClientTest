package hu.bitbot.springwebclinettest;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import hu.bitbot.springwebclinettest.dto.GitHubSearchResponseDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaResponseDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GithubHelperImpl implements GithubHelper {

	private final String baseUrl = "api.github.com";
	private final String repositorySearchPath = "search/repositories";

	@Override
	public URI prepareRequest(IsProjectJavaDto isProjectJavaDto) {
		return UriComponentsBuilder.newInstance()
				.scheme("https")
				.host(baseUrl)
				.path(repositorySearchPath)
				.query("q={REPO}")
				.buildAndExpand(isProjectJavaDto.getRepo()).encode().toUri();
	}

	@Override
	public IsProjectJavaResponseDto processResponse(GitHubSearchResponseDto gitHubSearchResponseDto) {
		return new IsProjectJavaResponseDto(gitHubSearchResponseDto.getItems() != null &&
				"JAVA".equals(gitHubSearchResponseDto.getItems()[0].getLanguage().toUpperCase()));
	}
}
