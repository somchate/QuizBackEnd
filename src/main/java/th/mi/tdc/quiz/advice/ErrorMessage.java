package th.mi.tdc.quiz.advice;

import java.util.Date;

public class ErrorMessage {
  private int statusCode;
//  private Date timestamp;
  private String message;
  private String description;

  public ErrorMessage(int statusCode, String message, String description) {
    this.statusCode = statusCode;
//    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }

  public ErrorMessage(int value, Date date, String message, String description) {
  }

  public int getStatusCode() {
    return statusCode;
  }

//  public Date getTimestamp() {
//    return timestamp;
//  }

  public String getMessage() {
    return message;
  }

  public String getDescription() {
    return description;
  }
}