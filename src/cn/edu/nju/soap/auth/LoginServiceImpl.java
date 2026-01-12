package cn.edu.nju.soap.auth;

import javax.jws.WebService;

@WebService(endpointInterface = "cn.edu.nju.soap.auth.LoginService")
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(String email, String password) throws Exception {
        if (email == null || email.isEmpty() || password == null) {
            throw new Exception("AuthError: Email or password cannot be empty.");
        }

        if (email.endsWith("@nju.edu.cn")) {
            return "TEACHER"; // 老师
        } else if (email.endsWith("@smail.nju.edu.cn")) {
            if (email.startsWith("MF") || email.startsWith("MG")) {
                return "GRADUATE"; // 研究生
            }
            return "UNDERGRADUATE"; // 本科生
        } else {
            throw new Exception("AuthError: Invalid email domain. Use nju.edu.cn or smail.nju.edu.cn");
        }
    }
}