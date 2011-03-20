package com.oregano.oreganoERP.ui;

import javax.enterprise.inject.Instance;
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
    Instance<OreganoErpApplication> application;

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return OreganoErpApplication.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        return application.get();
    }

}
