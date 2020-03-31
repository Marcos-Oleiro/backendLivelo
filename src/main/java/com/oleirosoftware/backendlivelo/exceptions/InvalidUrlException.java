package com.oleirosoftware.backendlivelo.exceptions;

public class InvalidUrlException extends RuntimeException {

  private static final long serialVersionUID = 8838260936294652492L;


  public InvalidUrlException(String message) {
    super(message);
  }

}
