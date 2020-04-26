package hu.bitbot.springwebclinettest;

import java.net.URI;

import hu.bitbot.springwebclinettest.dto.GitHubSearchResponseDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaResponseDto;

public interface GithubHelper {
	URI prepareRequest(IsProjectJavaDto isProjectJavaDto);
	IsProjectJavaResponseDto processResponse(GitHubSearchResponseDto gitHubSearchResponseDto);
}
