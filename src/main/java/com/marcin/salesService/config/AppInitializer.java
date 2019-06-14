package com.marcin.salesService.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                SalesServiceConfig.class
        };
    }

    protected String[] getServletMappings() {
        return new String[] {
                "/"
        };
    }
}
