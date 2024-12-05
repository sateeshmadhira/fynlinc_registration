package com.ess.registration.core.req;

import com.ess.registration.core.dto.AuthorizedPersonDetailsDto;
import com.ess.registration.core.dto.RegistrationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest extends ReqFilter{

   private RegistrationDto registrationDto=new RegistrationDto();
   private AuthorizedPersonDetailsDto personDetailsDto=new AuthorizedPersonDetailsDto();
}
