# 使用 Maven 官方映像來構建應用程序
# 第一階段：使用 Maven 3.8.5 和 OpenJDK 17 來構建應用程序
FROM maven:3.8.5-openjdk-17 AS build

# 將源碼複製到容器中
COPY . /app

# 設置工作目錄
WORKDIR /app

# 使用 Maven 構建應用程序
RUN mvn clean package

# 使用官方的 OpenJDK 17 基礎映像
FROM openjdk:17

# 設置工作目錄
WORKDIR /app

# 從構建映像中複製生成的 JAR 文件
COPY --from=build /app/target/organization-server.jar /app/organization-server.jar

# 設置啟動命令來運行生成的 JAR 文件
CMD ["java", "-jar", "/app/organization-server.jar"]
