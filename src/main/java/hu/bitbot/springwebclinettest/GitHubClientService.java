package hu.bitbot.springwebclinettest;

import hu.bitbot.springwebclinettest.dto.IsProjectJavaDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaResponseDto;
import reactor.core.publisher.Mono;

public interface GitHubClientService {
	Mono<IsProjectJavaResponseDto> isProjectJava(IsProjectJavaDto isProjectJavaDto);
}
