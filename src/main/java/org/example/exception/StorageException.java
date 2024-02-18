package org.example.exception;

import lombok.experimental.StandardException;

@StandardException
public class StorageException extends RuntimeException {
  public static StorageException initialization(Exception cause){
    return new StorageException("Exception during storage from file initialization", cause);
  }

  public static StorageException writing(Exception cause){
    return new StorageException("Exception during writing to storage", cause);
  }
}
