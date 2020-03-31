package com.oleirosoftware.backendlivelo.dto;

import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class CityDto {

  @JsonProperty("cidade")
  @NotBlank(message = "O nome da cidade deve vir preenchido.")
  private String name;

  @NotBlank(message = "O nome do estado deve vir preenchido.")
  @JsonProperty("estado")
  private String state;

}
