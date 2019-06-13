package com.neo.drools.service;

import com.neo.drools.model.Rule;
import com.neo.drools.repository.RuleRepository;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by neo on 17/7/31.
 */

@Service
public class ReloadDroolsRulesService {

    public static KieContainer kieContainer;

    @Autowired
    private RuleRepository ruleRepository;

    @Resource
    private RuleEngineService ruleEngineService;

    public KieContainer reload() {
        KieContainer kieContainer = loadContainerFromString(loadRules());
//        ReloadDroolsRulesService.kieContainer = kieContainer;
        return kieContainer;
    }

    private List<Rule> loadRules() {
        List<Rule> rules = ruleRepository.findAll();
        return rules;
    }

    private KieContainer loadContainerFromString(List<Rule> rules) {
        long startTime = System.currentTimeMillis();
        KieServices ks = KieServices.Factory.get();
        KieRepository kr = ks.getRepository();
        KieFileSystem kfs = ks.newKieFileSystem();

        for (Rule rule : rules) {
            String drl = rule.getContent();
            kfs.write("src/main/resources/" + drl.hashCode() + ".drl", drl);
        }

        KieBuilder kb = ks.newKieBuilder(kfs);

        kb.buildAll();
        if (kb.getResults().hasMessages(Message.Level.ERROR)) {
            throw new RuntimeException("Build Errors:\n" + kb.getResults().toString());
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time to build rules : " + (endTime - startTime) + " ms");
        startTime = System.currentTimeMillis();
        KieContainer kContainer = ks.newKieContainer(kr.getDefaultReleaseId());
        endTime = System.currentTimeMillis();
        System.out.println("Time to load container: " + (endTime - startTime) + " ms");
        return kContainer;
    }

    /**
     * 通过drools规则引擎验证字符串是满足正则表达式库
     *
     * @param ruleType
     * @param context
     * @return java.lang.Object
     * @author lifangyu
     * @date 2018/11/2
     */
    public Object checkRegex(String ruleType, String context) {
        List<Rule> ruleList = ruleRepository.findRulesByRuleType(ruleType);
        if (ruleList == null || ruleList.isEmpty()) {
            return "未查询到相关的规则记录";
        }

        return null;
    }
}
