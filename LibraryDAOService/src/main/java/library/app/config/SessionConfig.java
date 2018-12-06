package library.app.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
@EnableJdbcHttpSession
public class SessionConfig {
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
            String token = request.getParameter("JSESSIONID");
            if (token == null) {
                throw new MyException();
            }
            return true;
        }
    }
	
	@Bean
    @Autowired
    public MappedInterceptor getMappedInterceptor(MyHandlerInterceptor myHandlerInterceptor) {
        return new MappedInterceptor(new String[] { "/" }, myHandlerInterceptor);
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
