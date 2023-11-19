package kr.inhatc.spring.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersoningPersonController {

    // 트위터 버전 관리 스타일 - URI 버전 기반
    @GetMapping("/v1/person")
    public PersonV1 getFirstVesionOfPerson(){
        return new PersonV1("Kim Kitae");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVesionOfPerson(){
        return new PersonV2(new Name("Kim", "Kitae"));
    }

    // 아마존 버전 관리 스타일 - Request Parameter 버전 기반
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVesionOfPersonRequestParameter(){
        return new PersonV1("Kim Kitae");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVesionOfPersonRequestParameter(){
        return new PersonV2(new Name("Kim", "Kitae"));
    }

    // 마이크로소프트 버전 관리 스타일 - Headers 버전 기반
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVesionOfPersonRequestHeader(){
        return new PersonV1("Kim Kitae");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVesionOfPersonRequestHeader(){
        return new PersonV2(new Name("Kim", "Kitae"));
    }

    // GitHub 버전 관리 스타일 - Media type 버전 기반
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVesionOfPersonAcceptHeader(){
        return new PersonV1("Kim Kitae");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVesionOfPersonAcceptHeader(){
        return new PersonV2(new Name("Kim", "Kitae"));
    }
}
