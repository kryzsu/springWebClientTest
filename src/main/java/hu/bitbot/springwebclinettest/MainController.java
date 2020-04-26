package hu.bitbot.springwebclinettest;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.bitbot.springwebclinettest.dto.IsProjectJavaDto;
import hu.bitbot.springwebclinettest.dto.IsProjectJavaResponseDto;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MainController {
	private final GitHubClientService gitHubClientService;

	@GetMapping("/is-java")
	public Mono<IsProjectJavaResponseDto> isProjectJava(@RequestBody @Valid IsProjectJavaDto isProjectJavaDto) {
		return gitHubClientService.isProjectJava(isProjectJavaDto);
	}
}
