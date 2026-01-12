package cn.edu.nju.soap.auth;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;

@WebService
public interface LoginService {
    @WebMethod
    String login(@WebParam(name = "email") String email,
                 @WebParam(name = "password") String password) throws Exception;
}