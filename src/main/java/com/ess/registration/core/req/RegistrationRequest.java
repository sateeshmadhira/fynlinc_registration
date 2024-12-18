package com.ess.registration.core.req;

import com.ess.registration.core.dto.RegistrationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest extends ReqFilter{

   private RegistrationDto registration =new RegistrationDto();
}
