package com.telegram.tourist.exception;

public class AlreadyExistsException extends RuntimeException {

  public AlreadyExistsException(String message) {
    super(message);
  }
}
