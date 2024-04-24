package com.mentor.triptrekker.auth.service;


import com.mentor.triptrekker.auth.model.ValidationType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestValidationService {

    private final RestTemplate restTemplate;

    public RequestValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public ResponseEntity<?> forwardToValidateService(Object requestToValidate, ValidationType validationType){
        boolean isValidationPassed = callValidationService(requestToValidate, validationType);
        if (isValidationPassed) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED); // should be 102 status code
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 status code
        }

    }
    private boolean callValidationService(Object requestToValidate, ValidationType validationType) {
        ResponseEntity<?> response = restTemplate.postForEntity(
                "http://localhost:8080/v1/" + validationType.getValue(),
                requestToValidate,
                Boolean.class
        );

        return response.getStatusCode().is2xxSuccessful();
    }
}
