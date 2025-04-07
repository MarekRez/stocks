package fsa.stocks.exception;

import java.util.Date;

public record ErrorResponse(Date timestamp, String message, String details) {

}
