# 阶段一：构建阶段（使用 JDK 21 编译）
FROM maven:3.9.6-eclipse-temurin-21 AS build

# 设置工作目录
WORKDIR /app

# 先复制 pom.xml，利用 Docker 缓存依赖
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并打包
COPY src ./src
RUN mvn package -DskipTests

# 阶段二：运行阶段（使用轻量级 JRE 21）
FROM eclipse-temurin:21-jre-alpine

# 设置工作目录
WORKDIR /app

# 从构建阶段复制打包好的 JAR 文件
COPY --from=build /app/target/*.jar app.jar

# 暴露应用端口
EXPOSE 8080

# 启动命令
ENTRYPOINT ["java", "-jar", "app.jar"]