/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Spring Boot application entry point with OpenTelemetry bootstrap
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
