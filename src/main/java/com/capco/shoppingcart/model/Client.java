package com.capco.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "clientType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IndividualClient.class, name = "INDIVIDUAL"),
        @JsonSubTypes.Type(value = ProfessionalClient.class, name = "PROFESSIONAL")
})
@NoArgsConstructor
public class Client {
    protected String clientId;

    public Client(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
    }
}
