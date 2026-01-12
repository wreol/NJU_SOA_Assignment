package cn.edu.nju.soap.client;

import cn.edu.nju.soap.auth.LoginService;
import cn.edu.nju.soap.biz.StudentPortType;
import cn.edu.nju.soap.biz.GetStudentRequest;
import cn.edu.nju.soap.biz.GetStudentResponse;
import cn.edu.nju.soap.biz.CheckInRequest;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class ClientApp {
    public static void main(String[] args) throws Exception {

        System.out.println(">>> 1. 测试 Login 服务");
        URL authUrl = new URL("http://localhost:8080/auth?wsdl");
        QName authQName = new QName("http://auth.soap.nju.edu.cn/", "LoginServiceImplService");
        Service authService = Service.create(authUrl, authQName);
        LoginService loginPort = authService.getPort(LoginService.class);

        try {
            System.out.println("Testing Teacher: " + loginPort.login("prof@nju.edu.cn", "123"));
            System.out.println("Testing Student: " + loginPort.login("std@smail.nju.edu.cn", "123"));
            System.out.println("Testing Error: ");
            loginPort.login("hacker@qq.com", "123");
        } catch (Exception e) {
            System.out.println("Expected Exception: " + e.getMessage());
        }

        System.out.println("\n>>> 2. 测试 Archive & CheckIn 服务");
        URL bizUrl = new URL("http://localhost:8081/student?wsdl");
        QName bizQName = new QName("http://jw.nju.edu.cn/wsdl", "StudentService");
        Service bizService = Service.create(bizUrl, bizQName);
        StudentPortType bizPort = bizService.getPort(StudentPortType.class);

        try {
            System.out.println("尝试获取档案(使用老师邮箱)...");
            GetStudentRequest req = new GetStudentRequest();
            req.setStudentId("1001");
            req.setAuthEmail("prof@nju.edu.cn");
            GetStudentResponse resp = bizPort.getStudentInfo(req);
            System.out.println("Success: Got student " + resp.getStudent().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("尝试签到...");
            CheckInRequest req = new CheckInRequest();
            req.setStudentId("1001");
            req.setCourseId("SOA_2024");
            System.out.println(bizPort.checkIn(req).getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}