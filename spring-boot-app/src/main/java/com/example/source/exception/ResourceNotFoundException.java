/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Custom exception for resource not found (maps to HTTP 404)
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
