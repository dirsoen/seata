package com.longfor.it.providertwo.controller;

import com.longfor.it.providertwo.service.ProviderTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daiguanjun
 */
@RestController
public class ProviderTwoController {
    @Autowired
    private ProviderTwoService providerService;

    @RequestMapping("test1")
    public String test1() {
        providerService.test1();
        return "success！";
    }

    @RequestMapping("test2")
    public String test2() {
        providerService.test2();
        return "success！";
    }

}
