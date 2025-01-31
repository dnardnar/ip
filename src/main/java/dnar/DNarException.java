package dnar;

/**
 * Represents exceptions specific to the DNar chatbot.
 */
class DNarException extends Exception {

    /**
     * Constructs a new DNarException with the specified error message.
     *
     * @param message The detail message explaining the exception.
     */
    public DNarException(String message) {
        super(message);
    }
}
