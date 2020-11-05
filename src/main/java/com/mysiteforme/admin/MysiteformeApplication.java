package com.mysiteforme.admin;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@MapperScan("com.mysiteforme.admin.dao")
@EnableSwagger2
public class MysiteformeApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MysiteformeApplication.class);
    }
    private static final Log log = LogFactory.get();
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext context = SpringApplication.run(MysiteformeApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        String url = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + environment.getProperty("server.port");
        log.info("启动成功，访问链接：" + url);
    }
//    重写 configure
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(MysiteformeApplication.class);
//    }

    /*@Component
    public class RunHomePage implements CommandLineRunner {
        //启动成功后自动弹出页面
        @Override
        public void run(String... args) {
            try {
                Runtime.getRuntime().exec("cmd   /c   start   http://localhost:8081/");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }*/
}
