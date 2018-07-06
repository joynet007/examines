package com.exweb;

import com.excomm.SysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@ComponentScan(basePackages = {"com.exservice","com.exweb"})
@SpringBootApplication
public class ExwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExwebApplication.class, args);
	}


	/**
	 * 系统启动访问
	 * http://ip:port
	 * 直接进入此方法
	 * @return
	 */
	@RequestMapping("/")
	String syshome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(null != session.getAttribute(SysConfig.sessionKey)){
			return "main";
		}
		return "index";
	}
}
