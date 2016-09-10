package com.fmi.javaee.vertex.task.event;

import java.util.ArrayList;
import java.util.Collections;
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
	
	private static final int DEFAULT_MAX_EVENTS = 6;
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
		
		List<EventEntity> eventsOfUser = eventDAO.getEventsOfUser(loggedEmail, DEFAULT_MAX_EVENTS);
		if (eventsOfUser.isEmpty()) {
			return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
		}
		
		List<Event> events = new ArrayList<>();
		for (EventEntity eventEntity : eventsOfUser) {
			events.add(new Event(eventEntity));
		}
		
		Collections.sort(events, Collections.reverseOrder(new EventDateComparator()));
		return Response.ok(events).build();
	}

}
