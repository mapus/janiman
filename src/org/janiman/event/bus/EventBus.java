package org.janiman.event.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventBus {
	
	private static EventBus instance = new EventBus();
	Map<String,ArrayList<EventSubscriber>> subscriber = new HashMap<String,ArrayList<EventSubscriber>>();
	
	private EventBus()
	{
		
	}
	public void subscribe(EventSubscriber es,String key)
	{
		if(subscriber.get(key)==null)
			subscriber.put(key, new ArrayList<EventSubscriber>());
	
		ArrayList<EventSubscriber> keySub = subscriber.get(key);
		keySub.add(es);
		
	}
	public void publishEvent(String key, Object o)
	{
		ArrayList<EventSubscriber> keySub = subscriber.get(key);
		if(keySub!=null)
		{
		for(EventSubscriber es : keySub)
		{
			es.eventPerformed(key,o);
		}
		}
	}
	public static EventBus getInstance()
	{
		return instance;
	}
	
	

}
