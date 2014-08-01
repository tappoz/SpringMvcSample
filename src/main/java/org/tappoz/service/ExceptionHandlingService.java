package org.tappoz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * Created by ag on 24/06/14.
 */
@Service
public class ExceptionHandlingService {

    private final static Logger log = LoggerFactory.getLogger(ExceptionHandlingService.class);

    /**
     * This method is dedicated to REST calls, it involves HTTP codes to be returned!
     *
     * @param exception
     * @return
     */
    public ResponseEntity treatRestCalls(Throwable exception) {

        ResponseEntity badResponse;
        ResponseEntity notFoundResponse;

        badResponse = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        if (exception instanceof NullPointerException) {

            notFoundResponse = new ResponseEntity(HttpStatus.NOT_FOUND);
            log.error("We've got a NullPointerException... About to print the stack trace!");
            log.warn("Returning response HttpStatus: " + notFoundResponse.getStatusCode());
            exception.printStackTrace();
            return badResponse;
        } else if
                (
                        exception instanceof IllegalArgumentException
                    ||  exception instanceof IllegalStateException
                    ||  exception instanceof UnsupportedOperationException
                ) {
            log.warn("Something went wrong in the retrieval process... The message was: " + exception.getMessage());
            notFoundResponse = new ResponseEntity(HttpStatus.NOT_FOUND);
            log.warn("Returning response HttpStatus: " + notFoundResponse.getStatusCode());
            return notFoundResponse;
        } else {

            log.error("A serious exception occurred, the message was: " + exception.getMessage());
            log.warn("Returning response HttpStatus: " + badResponse.getStatusCode());
            return badResponse;
        }
    }
}
