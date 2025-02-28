package com.correajose.cineplus.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private int id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private boolean enabled;
}
