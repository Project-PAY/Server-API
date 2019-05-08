# PAY

이 프로젝트는 [**PAY**]에서 사용될 서버로

Spring Boot로 구현되었습니다. (jdk: 1.8)

### 프로젝트 실행을 위해서

디버깅을 하기 위해서는 프로젝트 디렉토리에서, 다음 스크립트를 실행해주세요:

#### `java -jar ???.jar`

배포모드로 앱이 실행됩니다.

브라우저에서 [http://localhost:8000](http://localhost:8000)을 열어주세요.

## 프로젝트 설명

프로젝트내에서 참고해야할 부분을 설명하겠습니다. 여기서 설명이 없는 부분은 크게 중요하지 않은 부분이라고 생각하면 됩니다.

### 설계 구조

    /src/main/java/com/pay
        /controller
            HTTP Request와 API를 매칭시킵니다.
        /core
            Spring Boot의 설정 파일, 또는 추가적인 파일등이 있습니다.
        /domain
            모델(도메인)을 정의합니다.
        /repository
            JPA에서 지원하는 레포지토리를 상속받고 있습니다.
        /service
            비즈니스 로직을 설계하여 작성하였습니다.