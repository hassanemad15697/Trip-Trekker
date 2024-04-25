package com.mentor.triptrekker.auth.service;


import com.mentor.triptrekker.auth.auditingAware.ApplicationAuditAware;
import com.mentor.triptrekker.auth.model.ValidationType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RequestValidationService {

    private final RestTemplate restTemplate;
    private final ApplicationAuditAware applicationAuditAware;

    public ResponseEntity<?> forwardToValidateService(Object requestToValidate, ValidationType validationType){
        boolean isValidationPassed = callValidationService(requestToValidate, validationType);
        System.out.println("user id who book : "+ applicationAuditAware.getCurrentAuditor().get());
        if (isValidationPassed) {
            return new ResponseEntity<>(HttpStatus.ACCEPTED); // should be 102 status code
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 status code
        }

    }
    private boolean callValidationService(Object requestToValidate, ValidationType validationType) {
        ResponseEntity<?> response = restTemplate.postForEntity(
                "http://validation-service:8088/v1/" + validationType.getValue(),
                requestToValidate,
                Boolean.class
        );

        return response.getStatusCode().is2xxSuccessful();
    }
}
