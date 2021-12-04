package com.example.response;

public class SuccessResponse {
  private final boolean success;
  private final String message;
  private final Object data;

  public static SuccessResponse ok(boolean success) {
    return new SuccessResponse(success);
  }

  public static SuccessResponse ok() {
    return new SuccessResponse(true);
  }

  public static SuccessResponse fail(Exception ex) {
    return new SuccessResponse(ex);
  }

  public static SuccessResponse fail() {
    return new SuccessResponse(false);
  }


  public static SuccessResponse fail(String message) {
    return new SuccessResponse(false, message);
  }

  public SuccessResponse(Exception ex) {
    this(false, ex.getMessage());
  }


  public SuccessResponse(boolean success, String message, Object data) {
    this.success = success;
    this.message = message;
    this.data = data;
  }

  public SuccessResponse(boolean success, String message) {
    this(success, message, null);
  }

  public SuccessResponse(boolean sucess) {
    this(sucess, null, null);
  }

  public SuccessResponse() {
    this(true);
  }

  public SuccessResponse(String message) {
    this(true, message, null);
  }

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public Object getData() {
    return data;
  }
}
