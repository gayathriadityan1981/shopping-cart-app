package com.capco.shoppingcart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessionalClient extends Client {
    private String companyName;
    private String businessRegistrationNumber;
    private String vatNumber;
    private double annualRevenue;

    public ProfessionalClient(String clientId, String companyName,
                              double annualRevenue) {
        super(clientId);
        this.companyName = companyName;
        this.annualRevenue = annualRevenue;
    }
//
//    public ProfessionalClient(String clientId, String companyName,
//                               String businessRegistrationNumber,
//                               String vatNumber,
//                              double annualRevenue) {
//        super(clientId);
//        this.companyName = companyName;
//        this.businessRegistrationNumber = businessRegistrationNumber;
//        this.vatNumber = vatNumber;
//        this.annualRevenue = annualRevenue;
//    }

}
