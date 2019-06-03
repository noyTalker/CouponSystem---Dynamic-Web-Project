package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import couponSystem.CouponSystem;
import exceptions.CouponSystemException;
import facade.CouponClientFacade;

import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;

@Path("/logIn")
@Consumes({ MediaType.APPLICATION_JSON })
public class LogIn {

	@Context
	HttpServletRequest request;

	@POST
	public Response logIn(classes.LogIn logIn) {
		try {
			request.getSession().invalidate();
			CouponSystem system = CouponSystem.getInstance();
			CouponClientFacade ccf = system.login(logIn.getName(), logIn.getPassword(), logIn.getClientType());
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60*7);
			session.setAttribute("Facade", ccf);
			return Response.accepted().build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().entity(json).build();
		}
	}

}
