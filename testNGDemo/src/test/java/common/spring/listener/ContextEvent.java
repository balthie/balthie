package common.spring.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextEvent implements ApplicationListener<ApplicationContextEvent>
{
    protected static final Logger LOGGER = LoggerFactory.getLogger(ContextEvent.class);
    
    @SuppressWarnings("unchecked")
    @Override
    public void onApplicationEvent(ApplicationContextEvent ev)
    {
        System.out.println(" Spring ContextEvent " + ev.getClass());
    }
}
