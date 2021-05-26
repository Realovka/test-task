package by.realovka.telegrambot.service.exception;

public class CityAlreadyExistException extends RuntimeException {
    public CityAlreadyExistException() {
        super();
    }

    public CityAlreadyExistException(String message) {
        super(message);
    }

    public CityAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public CityAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected CityAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
