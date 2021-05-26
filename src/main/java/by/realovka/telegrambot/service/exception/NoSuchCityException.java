package by.realovka.telegrambot.service.exception;

public class NoSuchCityException extends RuntimeException {
    public NoSuchCityException() {
        super();
    }

    public NoSuchCityException(String message) {
        super(message);
    }

    public NoSuchCityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCityException(Throwable cause) {
        super(cause);
    }

    protected NoSuchCityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
