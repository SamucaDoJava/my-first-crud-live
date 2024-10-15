package com.curso.de.tecnologia.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private boolean enabled;

}
