package com.dstevens.reader.web;

import java.util.Map;

public class PersonaAuthorization {

    private String status;
    private String email;
    private String audience;
    private long expires;
    private String issuer;

    public static PersonaAuthorization from(Map<Object, Object> responseObject) {
        String status = (String) responseObject.get("status");
        String email = (String) responseObject.get("email");
        String audience = (String) responseObject.get("audience");
        long expires = (Long) responseObject.get("expires");
        String issuer = (String) responseObject.get("issuer");
        return new PersonaAuthorization(status, email, audience, expires, issuer);
    }
    
    private PersonaAuthorization(String status, String email, String audience, long expires, String issuer) {
        this.status = status;
        this.email = email;
        this.audience = audience;
        this.expires = expires;
        this.issuer = issuer;
    }
    
    public boolean isOkay() {
        return status.equals("okay");
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public String toString() {
        return "PersonaAuthorization[" +
                    "status: " + status + ", " +
                    "email: " + email + ", " +
                    "audience: " + audience + ", " +
                    "expires: " + expires + ", " +
                    "issuer: " + issuer + 
        		"]";
    }
}
