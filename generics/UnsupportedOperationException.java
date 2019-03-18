package generics;

public class UnsupportedOperationException extends RuntimeException {
    private String message;

    UnsupportedOperationException() {
        message = "This operation is unsupported. You can use add, toArray and iterator methods.";
    }

    public String getMessage() {
        return message;
    }
}
