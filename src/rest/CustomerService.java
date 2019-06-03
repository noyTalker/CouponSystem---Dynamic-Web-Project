package rest;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import classes.Coupon;
import exceptions.CouponSystemException;
import facade.CustomerFacade;

@Path("/customer")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CustomerService {

	@Context
	HttpServletRequest request;

	CustomerFacade custFcd = null;
	ObjectMapper objectMapper = new ObjectMapper();

	public CustomerService() {
	}

	@PostConstruct
	public void getFacade() {
		HttpSession session = request.getSession(false);
		custFcd = (CustomerFacade) session.getAttribute("Facade");
	}

	@POST
	@Path("{id}")
	public Response purchaseCoupon(@PathParam("id") int id) {
		Coupon coupon = new Coupon();
		coupon.setId(id);
		try {
			custFcd.purchaseCoupon(coupon);
			String json = objectMapper.writeValueAsString(coupon);
			return Response.ok(json).build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		} catch (JsonProcessingException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().entity(json).build();
		}
	}

	@GET
	public Response getAllPurchaseCoupons() {
		Set<Coupon> set = new HashSet<>();
		try {
			set = custFcd.getAllPurchaseCoupons();
			String json = objectMapper.writeValueAsString(set);
			return Response.ok(json).build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		} catch (JsonProcessingException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().entity(json).build();
		}
	}

	@GET
	@Path("{type}")
	public Response getPurchaseCouponsByType(@PathParam("type") Coupon.Type type) {
		Set<Coupon> set = new HashSet<>();
		Coupon coupon = new Coupon();
		coupon.setType(type);
		try {
			set = custFcd.getPurchaseCouponsByType(coupon);
			String json = objectMapper.writeValueAsString(set);
			return Response.ok(json).build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		} catch (JsonProcessingException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().entity(json).build();
		}
	}

	@GET
	@Path("{price}")
	public Response getPurchaseCouponsUntilPrice(@PathParam("price") double price) {
		Set<Coupon> set = new HashSet<>();
		Coupon coupon = new Coupon();
		coupon.setPrice(price);
		try {
			set = custFcd.getPurchaseCouponsUntilPrice(coupon);
			String json = objectMapper.writeValueAsString(set);
			return Response.ok(json).build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		} catch (JsonProcessingException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().entity(json).build();
		}
	}
}
