# SOA Assignments (1 & 2) 实验项目说明

---

## 1. 项目目录结构

本项目将 Assignment 1 和 Assignment 2 集成在同一工程中，通过包名（Package）进行区分。

```text
NJU_SOA_Assignments/
├── src/
│   └── cn/edu/nju/
│       ├── schema/              <-- [Assignment 1] XML处理相关代码
│       │   ├── AssignmentMain.java      // 作业1 统一入口程序
│       │   ├── DOMGenerator.java        // DOM 生成 XML3
│       │   ├── XSLTTransformer.java     // XSLT 转换 XML4
│       │   ├── SAXFilter.java           // SAX 过滤 XML5
│       │   └── XMLValidator.java        // Schema 校验工具
│       │
│       └── soap/                <-- [Assignment 2] Web Service相关代码
│           ├── auth/                    // (Task 1) 认证服务
│           │   ├── LoginService.java
│           │   └── AuthPublisher.java   // 认证服务启动器 (Port: 8080)
│           ├── biz/                     // (Task 2) 业务服务
│           │   ├── StudentService.wsdl  // WSDL源文件
│           │   ├── StudentServiceImpl.java
│           │   └── BizPublisher.java    // 业务服务启动器 (Port: 8081)
│           └── client/                  // (Client) 客户端
│               └── ClientApp.java       // 综合测试客户端
│
└── xml_data/                    <-- [公共数据资源目录]
    ├── [Assignment 1 Inputs]
    │   ├── xml1.xml, Student.xsd, Archive.xsd 等
    │   └── score_transform.xsl
    ├── [Assignment 1 Outputs]
    │   ├── xml3.xml, xml4.xml, xml5.xml (运行程序后生成)
    └── [Assignment 2 Resource]
        └── StudentService.wsdl
```

------

## 2. Assignment 1 运行指南

### 运行步骤

1. **定位入口：** 在 IDEA 中找到 `src/cn/edu/nju/schema/AssignmentMain.java`。
2. **执行程序：** **Run 'AssignmentMain.main()'**。
3. **等待完成：** 控制台输出“全部任务执行完毕”即表示运行成功。
4. **查看结果：**
   - 检查xml_data目录下是否新生成了以下文件：
     - `xml3.xml` (随机生成的学生成绩列表)
     - `xml4.xml` (按课程重组并排序后的列表)
     - `xml5.xml` (筛选出的不及格名单)

------

## 3. Assignment 2 运行指南

### Step 1: 启动认证服务

- **文件位置：** `src/cn/edu/nju/soap/auth/AuthPublisher.java`
- **操作：** 右键运行 `main` 方法。
- **成功标志：** 控制台输出 `认证服务已启动: http://0.0.0.0:8080/auth?wsdl`。
- **验证：** 浏览器访问 [http://localhost:8080/auth?wsdl](https://www.google.com/search?q=http://localhost:8080/auth%3Fwsdl)。

### Step 2: 启动业务服务

- **文件位置：** `src/cn/edu/nju/soap/biz/BizPublisher.java`
- **操作：** 右键运行 `main` 方法。
- **成功标志：** 控制台输出 `业务服务已启动: http://0.0.0.0:8081/student?wsdl`。
- **验证：** 浏览器访问 [http://localhost:8081/student?wsdl](https://www.google.com/search?q=http://localhost:8081/student%3Fwsdl)。

### Step 3: 运行测试客户端 (Client)

- **文件位置：** `src/cn/edu/nju/soap/client/ClientApp.java`
- **操作：** 运行 `main` 方法。