package com.correajose.cineplus.dtos.customer;

import com.correajose.cineplus.models.customer.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseDTO {
    private Integer id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private Boolean enabled;
    private Role role;
}
