package com.hits.backend2.backend;

import com.hits.backend2.backend.presentation.EmployeeController;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class Backend2Application {

	public static void main(String[] args) {
		ApplicationContext context =
				new SpringApplicationBuilder(Backend2Application.class)
				.registerShutdownHook(true)
						.run(args);

		System.out.println(context.getBean(EmployeeController.class));
	}
}
