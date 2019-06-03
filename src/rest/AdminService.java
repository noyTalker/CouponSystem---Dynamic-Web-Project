package rest;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import classes.Company;
import classes.Customer;
import exceptions.CouponSystemException;
import facade.AdminFacade;

@Path("/admin")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class AdminService {

	@Context
	HttpServletRequest request;

	AdminFacade adminFcd = null;
	ObjectMapper objectMapper = new ObjectMapper();

	@PostConstruct
	public void getFacade() {
		HttpSession session = request.getSession(false);
		adminFcd = (AdminFacade) session.getAttribute("Facade");
	}

	@POST
	@Path("/company")
	public Response createCompany(Company company) {
		try {
			adminFcd.createCompany(company);
			String json = objectMapper.writeValueAsString(company);
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
	@Path("/company/{id}")
	public Response removeCompany(@PathParam("id") long id) {
		try {
			Company company = new Company();
			company.setId(id);
			adminFcd.removeCompany(company);
			return Response.noContent().build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		}
	}

	@PUT
	@Path("/company/{id}")
	public Response updateCompany(@PathParam("id") long id, Company company) {
		try {
			company.setId(id);
			adminFcd.updateCompany(company);
			String json = objectMapper.writeValueAsString(company);
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
	@Path("/company/{id}")
	public Response getCompany(@PathParam("id") long id) {
		Company company = new Company();
		try {
			company = adminFcd.getCompany(id);
			String json = objectMapper.writeValueAsString(company);
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
	@Path("/companies")
	public Response getAllCompanies() {
		Set<Company> set = new HashSet<>();
		try {
			set = adminFcd.getAllCompanies();
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

	@POST
	@Path("/customer")
	public Response createCustomer(Customer customer) {
		try {
			adminFcd.createCustomer(customer);
			String json = objectMapper.writeValueAsString(customer);
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
	@Path("/customer/{id}")
	public Response removeCustomer(@PathParam("id") long id) {
		try {
			Customer customer = new Customer();
			customer.setId(id);
			adminFcd.removeCustomer(customer);
			return Response.noContent().build();
		} catch (CouponSystemException e) {
			String json = String.format("{\"messege\":\"%s\"}", e.getMessage());
			return Response.serverError().status(Status.BAD_REQUEST).entity(json).build();
		}
	}

	@PUT
	@Path("/customer/{id}")
	public Response updateCustomer(@PathParam("id") long id, Customer customer) {
		try {
			customer.setId(id);
			adminFcd.updateCustomer(customer);
			String json = objectMapper.writeValueAsString(customer);
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
	@Path("/customer/{id}")
	public Response getCustomer(@PathParam("id") long id) {
		Customer customer = new Customer();
		try {
			customer = adminFcd.getCustomer(id);
			String json = objectMapper.writeValueAsString(customer);
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
	@Path("/customers")
	public Response getAllCustomers() {
		Set<Customer> set = new HashSet<>();
		try {
			set = adminFcd.getAllCustomers();
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
