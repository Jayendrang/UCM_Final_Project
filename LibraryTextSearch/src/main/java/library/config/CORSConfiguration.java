package library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class CORSConfiguration extends WebMvcConfigurerAdapter {

	public void includeCorsServletMapping(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**").allowedMethods("GET","POST");
	}
	
}
