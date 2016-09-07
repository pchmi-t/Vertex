package com.fmi.javaee.vertex.task.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;

@Path("event")
@Produces(MediaType.APPLICATION_JSON)
public class EventService {
	
	private final EventDAO eventDAO;
	
	@Inject
	public EventService(EventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}
	
	@GET
	public Response getSubscribedEventsOfUser(@Context HttpServletRequest request) {
		String loggedEmail = request.getRemoteUser();
		if (loggedEmail == null) {
			return Response.status(HttpServletResponse.SC_UNAUTHORIZED).build();
		}
		
		Collection<EventEntity> eventsOfUser = eventDAO.getEventsOfUser(loggedEmail);
		if (eventsOfUser.isEmpty()) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		
		List<Event> events = new ArrayList<>();
		for (EventEntity eventEntity : eventsOfUser) {
			events.add(new Event(eventEntity));
		}
		return Response.ok(events).build();
	}

}
