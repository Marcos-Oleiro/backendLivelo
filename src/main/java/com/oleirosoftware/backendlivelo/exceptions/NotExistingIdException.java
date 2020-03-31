package com.oleirosoftware.backendlivelo.exceptions;

public class NotExistingIdException extends RuntimeException {

  private static final long serialVersionUID = -1931608632731342962L;


  public NotExistingIdException() {
    super("ID não existente");
  }

}
