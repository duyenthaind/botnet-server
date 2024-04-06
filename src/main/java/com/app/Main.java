package com.app;

import com.app.service.ApplicationService;
import com.app.service.ApplicationServiceImpl;

/**
 * @author duyenthai
 */
public class Main {
    public static void main(String[] args) throws Exception {

        ApplicationService applicationService = new ApplicationServiceImpl();
        applicationService.process();
    }
}