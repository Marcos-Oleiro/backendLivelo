package com.oleirosoftware.backendlivelo.exceptions;

public class InvalidRequestBodyException extends RuntimeException {

  private static final long serialVersionUID = -3405175671216999842L;

  public InvalidRequestBodyException(String msg) {
    super(msg);
  }

}
