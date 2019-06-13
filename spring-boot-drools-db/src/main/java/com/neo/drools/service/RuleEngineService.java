/*
 * com.neo.drools.service.RuleEngineService.java
 * Copyright 2018 Lifangyu, Inc. All rights reserved.
 * IYueDian PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.neo.drools.service;

/**
 * Desc:规则引擎服务类
 *
 * @author lifangyu
 * @date 2018/11/2.
 */
public interface RuleEngineService {
    /**
     * 初始化规则引擎
     *
     * @return void
     * @author lifangyu
     * @date 2018/11/2
     */
    void initEngine();

    /**
     * 刷新规则引擎中的规则 
     *
     * @param
     * @return
     * @author lifangyu
     * @date 2018/11/2
     */
    void refreshEnginRule();

    /**
     * 执行规则引擎 
     *
     * @param domain 入参
     * @return
     * @author lifangyu
     * @date 2018/11/2
     */
    void executeRuleEngine(final Object domain);
}