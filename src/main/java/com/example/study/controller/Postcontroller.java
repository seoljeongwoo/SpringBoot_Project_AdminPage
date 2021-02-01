package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Postcontroller {

    // Post Method 사용 하는 곳 : HTML <form> , ajax 검색에 사용
    // 검색 파라미터가 많을경우 사용
    // json, xmL, multipart-form, text-plain (json방식이 아닌 다른 방식으로 받아야할경우 produces 로 명시


    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }
    @PutMapping("/putMethod")
    public void put(){

    }
    @PatchMapping("/patchMethod")
    public void patch(){

    }

}
