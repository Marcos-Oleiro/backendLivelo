package com.oleirosoftware.backendlivelo.handlers;

import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement(name = "error")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

  private HttpStatus status;
  private List<String> errors;

  public ApiError(HttpStatus status, String error) {
    this.status = status;
    errors = Arrays.asList(error);
  }
}
