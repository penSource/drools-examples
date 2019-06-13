package com.neo.drools.controller;

import com.neo.drools.model.Address;
import com.neo.drools.model.fact.AddressCheckResult;
import com.neo.drools.service.ReloadDroolsRulesService;
import com.neo.drools.service.RuleEngineService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;


@RequestMapping("/test")
@Controller
@Api
public class TestController {

    @Resource
    private ReloadDroolsRulesService rules;

    @ResponseBody
    @GetMapping("/address")
    public void test(int num) {
        Address address = new Address();
        address.setPostcode(generateRandom(num));
        KieSession kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        if (result.isPostCodeResult()) {
            System.out.println("规则校验通过");
        }
    }

    /**
     * 从数据加载最新规则
     *
     * @return
     * @throws IOException
     */
    @ResponseBody
    @GetMapping("/reload")
    public String reload() throws IOException {
        KieContainer kieContainer = rules.reload();
        KieSession kieSession = kieContainer.newKieSession();
        AddressCheckResult result = new AddressCheckResult();
        Address address = new Address();
        address.setPostcode("342366");
        kieSession.insert(address);
        kieSession.insert(result);
        int ruleFiredCount = kieSession.fireAllRules();
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");

        if (result.isPostCodeResult()) {
            System.out.println("规则校验通过");
        }
        return "ok";
    }


    /**
     * 生成随机数
     *
     * @param num
     * @return
     */
    public String generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number = number.append(chars.charAt(rand));
        }
        return number.toString();
    }

    /**
     * 通过规则引擎验证其正则表达式是哪一种
     *
     * @param context
     * @return java.lang.Object
     * @author lifangyu
     * @date 2018/11/2
     */
    @ResponseBody
    @PostMapping("/checkRegex")
    public Object checkRegex(String context) {
        if (StringUtils.isBlank(context)) {
            return "请求参数不符合要求";
        }
        return rules.checkRegex("rule_regex",context);
    }
}
