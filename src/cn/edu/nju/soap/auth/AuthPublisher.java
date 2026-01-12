package cn.edu.nju.soap.auth;

import javax.xml.ws.Endpoint;

public class AuthPublisher {
    public static void main(String[] args) {
        String address = "http://0.0.0.0:8080/auth";
        Endpoint.publish(address, new LoginServiceImpl());
        System.out.println("认证服务已启动: " + address + "?wsdl");
    }
}