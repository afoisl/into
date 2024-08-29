package dw.into.exception;

public class InvalidRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;  // 선택사항
    private String message;

    public InvalidRequestException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
