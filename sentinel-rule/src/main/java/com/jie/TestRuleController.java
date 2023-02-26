package com.jie;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestRuleController {
    //定义资源 value:设置资源的名称 blockHandler:设置限流或降级的处理函数
//    @SentinelResource(value = "sentinel_rule", blockHandler = "exceptionHandler")
    @GetMapping("/rule")
    public String hello() {
        return "Hello Sentinel!";
    }

    //被限流的处理函数
    public String exceptionHandler(BlockException ex) {
        ex.printStackTrace();
        return "系统繁忙，请稍候";
    }



    /**
     * 定义限流规则
     *
     * @PostConstruct:当前类的构造函数执行之后执行
     */
    @PostConstruct
    public void initFLowRules(){
        // 创建限流规则
        List<DegradeRule> rules = new ArrayList<>();

        DegradeRule flowRule = new DegradeRule();
        // 定义资源
        flowRule.setResource("sentinel_rule");
        // 定义限流规则
        flowRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        flowRule.setCount(0.01);
        flowRule.setTimeWindow(10);

        rules.add(flowRule);
        // 加载限流规则
        DegradeRuleManager.loadRules(rules);
    }
}
