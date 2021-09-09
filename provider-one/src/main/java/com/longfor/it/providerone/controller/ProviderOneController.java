package com.longfor.it.providerone.controller;

import com.longfor.it.providerone.service.ProviderOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author daiguanjun
 */
@RestController
public class ProviderOneController {
    @Autowired
    private ProviderOneService providerService;

    @RequestMapping("test1")
    public String test1() {
        providerService.test1();
        return "来了！";
    }

}
