package common.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStarted implements ApplicationListener<ContextStartedEvent>
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(ContextStarted.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(ContextStartedEvent ev)
    {
        System.out.println(" Spring ContextStarted ");
    }
}
