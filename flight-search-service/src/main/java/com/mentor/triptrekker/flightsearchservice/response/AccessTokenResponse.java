package com.mentor.triptrekker.flightsearchservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccessTokenResponse {
    private String type;
    private String username;
    private String application_name;
    private String client_id;
    private String token_type;
    private String access_token;
    private String state;
    private String scope;
    private long expiresIn;
}
