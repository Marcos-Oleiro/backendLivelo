package com.oleirosoftware.backendlivelo.exceptions;

public class InvalidCityIdException extends RuntimeException {

  private static final long serialVersionUID = -810768466081795324L;


  public InvalidCityIdException() {
    super("Id da cidade informada inválida");
  }

}
