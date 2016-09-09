package com.fmi.javaee.vertex.task.event;

import java.util.Comparator;

public class EventDateComparator implements Comparator<Event> {

	@Override
	public int compare(Event event1, Event event2) {
		return event1.getTimestamp().compareTo(event2.getTimestamp());
	}

}
