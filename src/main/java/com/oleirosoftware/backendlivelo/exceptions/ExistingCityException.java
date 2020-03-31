package com.oleirosoftware.backendlivelo.exceptions;

public class ExistingCityException extends RuntimeException {

  private static final long serialVersionUID = 3171504667100131008L;

  public ExistingCityException() {
    super("Cidade já cadastrada.");
  }

}
