package br.com.paulopinheiro.onlinestore.web.filters;

import java.io.IOException;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;

@WebFilter(filterName = "helloFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST}, initParams = {
    @WebInitParam(name = "initParam", value = "Init Param")})
public class HelloWorldFilter extends HttpFilter {

    @Override
    public void init(FilterConfig config) {
        //System.out.println(config.getInitParameter("initParam"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //System.out.println("Hello World from HelloWorldFilter");
        chain.doFilter(request, response);
    }
}
