package dnar;

/**
 * Represents exceptions specific to the DNar chatbot.
 * This custom exception class allows the chatbot to handle and
 * report errors in a more structured and user-friendly manner.
 * It extends the base Exception class to provide specific error
 * handling for DNar-related issues.
 */
class DNarException extends Exception {

    /**
     * Constructs a new DNarException with the specified error message.
     * This message should provide a clear explanation of the error
     * that occurred, to aid in debugging or user feedback.
     *
     * @param message The detail message explaining the exception.
     */
    public DNarException(String message) {
        super(message);
    }
}
