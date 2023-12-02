package th.mi.tdc.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
// Change to Tomcat Production running
public class QuizBackend extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources( QuizBackend.class );
	}

	public static void main(String[] args) {
		SpringApplication.run(QuizBackend.class, args);
	}
}