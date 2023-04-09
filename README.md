# livinginhotel

호텔 예약 서비스

## sdkman

sdkman을 설치한다.

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk help
```

.sdkmanrc 파일이 존재하는 경우 프로젝트에 맞는 배포 버전이 설정된다.

```bash
sdk env
```

다른 버전을 원하는 경우 다음 명령으로 java 버전을 확인하여 원하는 배포 버전을 설정한다.

```bash
sdk list java
sdk install java 17.0.2.8.1-amzn
sdk use java 17.0.2.8.1-amzn
```

## IntelliJ 설정
### SDK

IntelliJ 를 재시작한다.

File -> Project Structure -> Project -> Project SDK 에서 원하는 버전을 선택하고 저장한다.

Preferences -> Build, Execution, Deployment -> Build Tools -> Gradle 에서 원하는 버전의 Gradle JVM 을 선택하고 저장한다.

### kotest

kotest 플러그인을 설치하도록 한다.

## 개발환경
- Java 17
- Kotlin 1.8.x
- Spring Boot 3.0.5.RELEASE 
- JPA 
- Gradle 
- Rest Doc + Swagger 
- Kotest
- Mysql
- Docker

# docker-mysql
## 실행 및 종료

실행은 다음과 같이 할 수 있다.

```shell
docker-compose up --build
```

종료는 다음과 같이 할 수 있다.

```shell
docker-compose down
```

마스터, 슬레이브 리플리케이션 구조로 구성되어 있다.

실행 후에는 다음 로컬 포트로 로컬 mysql 에 접근할 수 있다.

* 13307: master
* 13317: slave
* root/1234 로 db 접근가능
* 13307 마스터로 접근하여 db확인
* docker/mysql/init/db/hotel/origin.sql 경로에서 DDL 확인 가능.
## 쿼리 로그

쿼리 로그는 tool 디렉터리에서 다음과 같이 확인할 수 있다. 다음은 마스터 노드의 로그를 확인한다.

```shell
sh tail-general-log.sh 
```

다음은 슬레이브 노드의 로그를 확인한다.

```shell
sh tail-general-log.sh replica 
```

## 실행방법
```
./gradlew bootRun
#java -jar livinginhotel.jar
```

## docs
```bash
./gradlew copyOasToSwagger
```
open > resources/static/swagger-ui/swagger-ui.html for browser