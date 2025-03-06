# 代码生成器项目 
## 一、basic版本  
实现步骤：  
1）生成静态文件，通过 Main 方法运行  
2）生成动态文件，通过 Main 方法运行  
3）同时生成静态和动态文件，通过 Main 方法运行，得到完整代码生成AxUDbTQuRor1jpc/CuCyJchGT2A0AxkST4vHqFzr05Q= 
4）开发命令行工具，接受用户的输入并生成完整代码
5）将工具封装为 jar 包和脚本，供用户调用  
### 1.生成静态文件  
```java
    public static void main(String[] args) {
        // 获取整个项目的根路径
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
//        File parentFile = new File(projectPath).getParentFile();
//        System.out.println(parentFile);
        // 输入路径：ACM 示例代码模板目录
        String inputPath = new File(projectPath, "acm-template").getAbsolutePath();
        System.out.println(inputPath);
        // 输出路径：直接输出到项目的根目录
        String outputPath =new File(projectPath, "demo").getAbsolutePath();
        System.out.println(outputPath);
        copyFilesByHutool(inputPath, outputPath);
    }

    /**
     * 递归拷贝文件（递归实现，会将输入目录完整拷贝到输出目录下）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile, outputFile);
        } catch (Exception e) {
            System.err.println("文件复制失败");
            e.printStackTrace();
        }
    }
```
### 2.动态生成文件  
需要确定动态生成有什么需求  
1. 定义数据模型，需要传递给模板
```java
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
    private String author;
    /**
     * 输出信息
     */
    private String outputText;
}

```
2. 编写动态模板，在resource/templates创建ftl模板文件  
```java
import java.util.Scanner;

/**
 * ACM 输入模板（多数之和）
 * @author ${author}
 */
public class MainTemplate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

<#if loop>
        while (scanner.hasNext()) {
</#if>
            // 读取输入元素个数
            int n = scanner.nextInt();
            // 读取数组
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }
            // 处理问题逻辑，根据需要进行输出
            // 示例：计算数组元素的和
            int sum = 0;
            for (int num : arr) {
                sum += num;
            }
            System.out.println("${outputText}" + sum);
<#if loop>
        }
</#if>
        scanner.close();
    }
}
```  
### 3.picocli命令行  
1）@Option 注解的 names 参数：指定选项英文名称。  
2）description 参数：指定描述信息，从而让生成的帮助手册和提示信息更清晰。  
3）@Parameters 注解的 paramLabel 参数：参数标签，作用类似于描述信息。  
4）@Parameters 注解的 defaultValue 参数：默认值，参考文档：https://picocli.info/#_default_values  
5）required 参数：要求必填，参考文档：https://picocli.info/#_required_arguments  