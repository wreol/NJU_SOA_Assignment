package cn.edu.nju.soap.biz;

import javax.xml.ws.Endpoint;

public class BizPublisher {
    public static void main(String[] args) {
        String address = "http://0.0.0.0:8081/student";
        Endpoint.publish(address, new StudentServiceImpl());
        System.out.println("业务服务已启动: " + address + "?wsdl");
    }
}