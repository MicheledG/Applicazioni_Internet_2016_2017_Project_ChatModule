package it.polito.ai.chat.exception;

/**
 * Created by france193 on 03/07/2017.
 */
public class UnknownTopic extends RuntimeException {


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UnknownTopic(String message) {
        super(message);
    }
}
