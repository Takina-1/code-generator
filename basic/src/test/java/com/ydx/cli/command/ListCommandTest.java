package com.ydx.cli.command;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.*;
public class ListCommandTest {
@Test
    public void run() {
        String projectPath = System.getProperty("user.dir");
        // 整个项目的根路径
        System.out.println(projectPath);

        File parentFile = new File(projectPath).getParentFile();
        // 输入路径
        System.out.println(parentFile);

        String inputPath = new File(parentFile, "acm-template").getAbsolutePath();
        System.out.println(inputPath);

        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }
}