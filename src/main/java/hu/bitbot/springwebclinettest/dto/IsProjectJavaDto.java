package hu.bitbot.springwebclinettest.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsProjectJavaDto {
	@NotEmpty
	private String repo;
}
