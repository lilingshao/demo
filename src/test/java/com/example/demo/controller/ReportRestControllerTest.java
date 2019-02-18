package com.example.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
public class ReportRestControllerTest {

    private final static String url =  "http://localhost:8070/";
    private static RestTemplate restTemplate = new RestTemplate();

    @Test
    public void showUserPost() {
        MultiValueMap<String,Object> postParameters = new LinkedMultiValueMap<>();
        postParameters.add("id","1");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String,Object>> entity = new HttpEntity<>(postParameters,headers);
        String nurl = url+"demo/showUser";
        System.out.println("nurl=="+nurl);
        ResponseEntity<String> response = restTemplate.exchange(nurl ,
                HttpMethod.POST,entity,String.class);
        System.out.println("code: " + response.getStatusCode());
        System.out.println("codevalue: " + response.getStatusCodeValue());
        System.out.println("result: " + response.getBody());
    }
    @Test
    public void showUserGet() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/x-www-form-urlencoded");
        HttpEntity<MultiValueMap<String,Object>> entity = new HttpEntity<>(headers);
        String nurl = url+"demo/showUser?id=1";

        ResponseEntity<String> response = restTemplate.exchange(nurl ,
                HttpMethod.GET,entity,String.class);
        System.out.println("code: " + response.getStatusCode());
        System.out.println("codevalue: " + response.getStatusCodeValue());
        System.out.println("result: " + response.getBody());
    }
}