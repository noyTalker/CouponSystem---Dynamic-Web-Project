package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.PathParam;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import classes.Coupon;
import exceptions.CouponSystemException;
import facade.CompanyFacade;

@Path("/company")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CompanyService {

	@Context
	HttpServletRequest request;

	CompanyFacade compFcd = null;
	ObjectMapper objectMapper = new ObjectMapper();

	public CompanyService() {
	}

	@PostConstruct
	public void getFacade() {
		HttpSession session = request.getSession(false);
		compFcd = (CompanyFacade) session.getAttribute("Facade");
	}

	@POST
	public Response createCoupon(Coupon coupon) {
		try {
			compFcd.createCoupon(coupon);
			String json = objectMapper.writeValueAsString(coupon);
			return Response.ok(json).status(Status.CREATED).build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		} catch (JsonProcessingException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().entity(json).build();
		}
	}

	@DELETE
	@Path("{id}")
	public Response removeCoupon(@PathParam("id") long id) {
		try {
			Coupon coupon = new Coupon();
			coupon.setId(id);
			compFcd.removeCoupon(coupon);
			return Response.noContent().build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		} 
	}

	@PUT
	@Path("{id}")
	public Response updateCoupon(@PathParam("id") long id, Coupon coupon) {
		try {
			coupon.setId(id);
			compFcd.updateCoupon(coupon);
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
	@Path("{id}")
	public Response getCoupon(@PathParam("id") long id) {
		Coupon coupon = new Coupon();
		try {
			coupon = compFcd.getCoupon(id);
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
	public Response getAllCoupons() {
		Set<Coupon> set = new HashSet<>();
		try {
			set = compFcd.getAllCoupons();
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
	public Response getCouponsByType(@PathParam("type") Coupon.Type type) {
		Set<Coupon> set = new HashSet<>();
		Coupon coupon = new Coupon();
		coupon.setType(type);
		try {
			set = compFcd.getCouponsByType(coupon);
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
	public Response getCouponsUntilPrice(@PathParam("price") double price) {
		Set<Coupon> set = new HashSet<>();
		Coupon coupon = new Coupon();
		coupon.setPrice(price);
		try {
			set = compFcd.getCouponsUntilPrice(coupon);
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
	@Path("{date}")
	public Response getCouponsUntilDate(@PathParam("date") Date date) {
		Set<Coupon> set = new HashSet<>();
		Coupon coupon = new Coupon();
		coupon.setEndDate(date);
		try {
			set = compFcd.getCouponsUntilPrice(coupon);
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
