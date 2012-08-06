package org.janiman.event.bus;

public interface EventSubscriber {

	public void eventPerformed(String key,Object o);
	
}
