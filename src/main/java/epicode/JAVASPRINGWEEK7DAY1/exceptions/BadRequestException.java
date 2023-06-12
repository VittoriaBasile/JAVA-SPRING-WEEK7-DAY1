package epicode.JAVASPRINGWEEK7DAY1.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}

}