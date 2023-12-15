package agencias.service.service;

import agencias.service.models.dto.Request.LoginDto;
import agencias.service.models.dto.Response.JwtDto;

public interface LoginService {

    JwtDto login(LoginDto loginDto);
}
