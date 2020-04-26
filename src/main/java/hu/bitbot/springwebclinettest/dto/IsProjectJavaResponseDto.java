package hu.bitbot.springwebclinettest.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IsProjectJavaResponseDto {
	private Boolean java;
}
