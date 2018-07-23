package common.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStopped implements ApplicationListener<ContextStoppedEvent>
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(ContextStopped.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(ContextStoppedEvent ev)
    {
        System.out.println(" Spring ContextStopped ");
    }
}
