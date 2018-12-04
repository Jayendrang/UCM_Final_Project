package library.app.LibraryDAOService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.MappedInterceptor;

@SpringBootApplication
@ComponentScan("library.app")
@EntityScan("library.app")
@EnableJpaRepositories("library.app")
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableEurekaClient
public class LibraryDAOService {

	public static void main(String[] args) {
		SpringApplication.run(LibraryDAOService.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Component
    public static class MyHandlerInterceptor implements HandlerInterceptor {

        @Autowired
        TestBean testBean;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            return testBean.judgeToken(request);
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                Exception ex) throws Exception {

        }
    }
	
	public static class MyException extends RuntimeException {

    }
	
	@Component
    public static class TestBean {
        public boolean judgeToken(HttpServletRequest request) {
            String token = request.getParameter("token");
            if (token == null) {
                throw new MyException();
            }
            return true;
        }
    }
	
	@Bean
    @Autowired
    public MappedInterceptor getMappedInterceptor(MyHandlerInterceptor myHandlerInterceptor) {
        return new MappedInterceptor(new String[] { "/library/" }, myHandlerInterceptor);
    }
	 @RestControllerAdvice
	    public static class MyExceptionHandler {
	        @ExceptionHandler(MyException.class)
	        @ResponseBody
	        public Map<String, Object> handelr() {
	            Map<String, Object> m1 = new HashMap();
	            m1.put("status", "error");
	            m1.put("message", "Token doesn't exists");
	            return m1;
	        }
	    }
}
