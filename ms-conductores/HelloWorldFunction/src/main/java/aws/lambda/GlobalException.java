package aws.lambda;

public class GlobalException extends RuntimeException {
    public GlobalException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
