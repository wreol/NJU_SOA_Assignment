# 面向服务软件工程Assignment 1

## 1. Schema

- **Student.xsd**: 定义了基础的 `StudentType` 和 `CourseScoreType`，供其他模块复用 。
- **Archive.xsd**: 引用 `Student.xsd`，扩展了学生个人档案信息（如出生日期、地址）。
- **ScoreList.xsd**: 定义了以学生为聚合的成绩单结构（用于验证 XML3）。

------

## 2. 随机生成学生成绩

### 2.1 实现逻辑

使用 Java DOM API 创建 XML 文档树。程序首先读取基础数据，然后循环生成9名虚拟学生的信息。对于每位学生，随机生成5门课程的平时成绩和期末成绩，计算总评，并包含强制逻辑以确保至少有一名学生出现不及格成绩 。

### 2.2 运行结果 (XML3)

> ![001](.\screenshot\001.png)

------

## 3.按课程重组与排序

### 3.1 实现逻辑

- **分组 (Grouping)**：利用 Muenchian Method (或 `for-each-group`) 按 `courseId` 对成绩进行分组。
- **排序 (Sorting)**：在每门课程内部，使用 `<xsl:sort>` 按 `scoreTotal` 进行降序排列 。

### 3.2 运行结果（XML4)

> ![002](.\screenshot\002.png)

------

## 4. 筛选不及格名单

### 4.1 实现逻辑

使用 SAX 解析器流式读取 XML4。通过 `startElement` 监听 `StudentScore` 和分数节点，通过 `characters` 读取具体分数值。当检测到 `scoreTotal` 小于 60 分时，将该条记录写入缓冲区，并在 `endElement` 时决定是否输出到最终的 XML5 文件中 。

### 4.2 运行结果(XML5)

> ![003](.\screenshot\003.png)



