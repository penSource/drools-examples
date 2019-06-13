/*
 * com.neo.drools.service.impl.RuleEngineServiceImpl.java
 * Copyright 2018 Lifangyu, Inc. All rights reserved.
 * IYueDian PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.neo.drools.service.impl;

import com.neo.drools.service.RuleEngineService;
import org.springframework.stereotype.Service;
//import org.drools.RuleBase;

/**
 * Desc:TODO
 *
 * @author lifangyu
 * @date 2018/11/2.
 */
@Service
public class RuleEngineServiceImpl implements RuleEngineService {

//    private RuleBase ruleBase;

    /**
     * 初始化规则引擎
     *
     * @return void
     * @author lifangyu
     * @date 2018/11/2
     */
    @Override
    public void initEngine() {

    }

    /**
     * 刷新规则引擎中的规则 
     *
     * @return
     * @author lifangyu
     * @date 2018/11/2
     */
    @Override
    public void refreshEnginRule() {

    }

    /**
     * 执行规则引擎 
     *
     * @param domain 入参
     * @return
     * @author lifangyu
     * @date 2018/11/2
     */
    @Override
    public void executeRuleEngine(Object domain) {

    }
}