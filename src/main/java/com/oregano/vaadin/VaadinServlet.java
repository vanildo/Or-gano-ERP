package com.oregano.vaadin;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;


@WebServlet(urlPatterns = "/*")
public class VaadinServlet extends AbstractApplicationServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject
    MyVaadinApplication application;

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return MyVaadinApplication.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        return application;
    }

}
