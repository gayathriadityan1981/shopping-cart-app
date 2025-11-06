package com.capco.shoppingcart.model;

public class IndividualClient extends Client {
    private String firstName;
    private String lastName;

    public IndividualClient(String clientId, String firstName, String lastName) {
        super(clientId);
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
