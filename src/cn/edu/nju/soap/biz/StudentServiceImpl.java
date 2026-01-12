package cn.edu.nju.soap.biz;

import javax.jws.WebService;
import cn.edu.nju.soap.auth.LoginServiceImpl;

@WebService(endpointInterface = "cn.edu.nju.soap.biz.StudentPortType",
        serviceName = "StudentService",
        targetNamespace = "http://jw.nju.edu.cn/wsdl")
public class StudentServiceImpl implements StudentPortType {

    @Override
    public GetStudentResponse getStudentInfo(GetStudentRequest parameters) throws ServiceError {
        try {

            LoginServiceImpl auth = new LoginServiceImpl();
            String role = auth.login(parameters.getAuthEmail(), "password123");

            if (!"TEACHER".equals(role)) {
                throw new Exception("权限不足：只有老师可以查看档案");
            }

            StudentType stu = new StudentType();
            stu.setId(parameters.getStudentId());
            stu.setName("Mock Student");
            stu.setStatus("Active");

            GetStudentResponse resp = new GetStudentResponse();
            resp.setStudent(stu);
            return resp;

        } catch (Exception e) {
            throw new ServiceError(e.getMessage(), "Server internal error: " + e.getMessage());
        }
    }

    @Override
    public CheckInResponse checkIn(CheckInRequest parameters) throws ServiceError {
        CheckInResponse resp = new CheckInResponse();
        resp.setIsSuccess(true);
        resp.setMessage("Student " + parameters.getStudentId() + " checked in for " + parameters.getCourseId());
        return resp;
    }
}