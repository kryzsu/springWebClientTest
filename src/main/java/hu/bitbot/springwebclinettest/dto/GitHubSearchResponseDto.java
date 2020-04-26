package hu.bitbot.springwebclinettest.dto;

import lombok.Data;

@Data
public class GitHubSearchResponseDto {
	// private int total_count; // unnecessary at the moment
	private GItHubRepoDto items[];
}
