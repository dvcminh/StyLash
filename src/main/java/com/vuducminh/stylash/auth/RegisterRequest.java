package com.vuducminh.stylash.auth;

import com.vuducminh.stylash.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  private String email;
  private String address;
  private String phone_number;
  private String password;
  private Role role;
}
