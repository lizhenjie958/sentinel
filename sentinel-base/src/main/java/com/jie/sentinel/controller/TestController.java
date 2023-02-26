package com.jie.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        //使用限流规则
        try (Entry entry = SphU.entry("HelloWorld")) {
            return "Hello Sentinel!";
        } catch (BlockException e) {
            e.printStackTrace();
            return "系统繁忙，请稍候";
        }
    }


    /**
     * 定义限流规则
     *
     * @PostConstruct:当前类的构造函数执行之后执行
     */
    /*@PostConstruct
    public void initFLowRules(){
        // 创建限流规则
        List<FlowRule> rules = new ArrayList<>();

        FlowRule flowRule = new FlowRule();
        // 定义资源
        flowRule.setResource("HelloWorld");
        // 定义限流规则
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(2);

        rules.add(flowRule);
        // 加载限流规则
        FlowRuleManager.loadRules(rules);

    }*/
}
