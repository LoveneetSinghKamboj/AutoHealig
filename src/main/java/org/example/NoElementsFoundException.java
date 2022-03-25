package org.example;

/**
 *   NoElementsFoundException - thrown when filtering results in an empty list
 */
public class NoElementsFoundException extends Exception {
    NoElementsFoundException(String message) {
        super(message);
    }
}
