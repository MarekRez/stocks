package fsa.stocks.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    // Called before the actual handler is executed
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(".................................................................................");
        logger.info("Incoming request: {} {}",
                request.getMethod(), request.getRequestURI());
        return true; // Continue with the next interceptor or the handler method
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        // Log response details
        logger.info("After Handle request: {} {}, status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }

//    // Called after the handler is executed
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
//                                Object handler, Exception ex) throws Exception {
//        logger.info("Completed request: {} {} with status {}",
//                request.getMethod(), request.getRequestURI(), response.getStatus());
//    }
}
