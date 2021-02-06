package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
// Class에 대해서 주소가 동일하더라도 상관 없음.
public class Getcontroller {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")    //localhost:8080/api/getMethod
    public String getRequest(){

        return "Hi getMethod";
    }
    // Method에 대해서 주소가 동일한 Method가 존재시 Spring Boot에서는 실행 X
    @GetMapping("/getParameter")    //localhost:8080/api/getParameter?id=1234&password=abc
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        // Parameter로 들어오는 변수명과 GET방식을 통해 정보를 전달하는 변수명이 다를경우 정보를 받을수 없음.
        // Parameter 변수명을 지역변수로 사용 못함.
        // 꼭 사용해야할경우 @RequestParam(name = "GET방식 입력 변수")지정해주는 방식으로 변수명 변경가능
        String password = "bbbb";
        System.out.println("id : " + id);
        System.out.println("password : " + pwd);
        return id+pwd;
    }

    // localhost:8080/api/getMultiparameter?account=abcd&email=study@gmail.com&page=10
    @GetMapping("/getMultiparameter")
    public SearchParam getMultiparameter(SearchParam searchParam){
        // Parameter가 여러개 들어올경우 @RequestParam을 계속해서 써주어야하는데 한계가 있음
        // 객체로 받아오는 방법
        // 새로운 클래스를 생성후 검색 Parameter로 들어오는 변수명을 설정 그리고 Getter , Setter 생성
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // 네트워크 통신시 Json 형식으로 받아옴
        // {"account" : "", "email" : "", "page" : 0}으로 처리하기를 원할때는 받아온 searchParam을 return 시켜주면됨
        // Spring Boot 개발시 Jackson Library를 내장하고 있기떄문에 따로 처리하지않을시 Jackson으로 처리하라는 말임
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader(){

        // {"resultCode : "OK" , "description : "OK" }
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
