package com.ydx.model;

import lombok.Data;

/**
 * 动态模版配置
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否生成循环
     */
    private boolean loop;

    /**
     * 作者注释
     */
    private String author = "takina";

    /**
     * 输出信息
     */
    private String outputText = "sum = ";
}
