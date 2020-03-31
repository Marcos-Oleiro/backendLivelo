package com.oleirosoftware.backendlivelo.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class ClientDto {


  @JsonProperty("nome")
  @NotBlank(message = "O nome do cliente deve vir preenchido.")
  private String name;


  @JsonProperty("sexo")
  @Pattern(regexp = "[(F|f)]+|[(M|m)]+|[(O|o+)]+", message = "O campo sexo deve ser M/m, F/f ou O/o.")
  @Length(min = 1, max = 1, message = "O campo deve possuir apenas uma letra.")
  @NotBlank(message = "O sexo do cliente deve vir preenchido.")
  private String sex;

  @JsonFormat(pattern = "dd/MM/yyyy")
  @JsonProperty("nascimento")
  @NotNull(message = "A data de nascimento deve ser informada.")
  @Past(message = "A data de nascimento é inválida.")
  private LocalDate birthday;

  @JsonProperty("idCidade")
  @NotNull(message = "A identificação da cidade deve vir preenchido.")
  private Integer idCity;

  private Integer age;
}
