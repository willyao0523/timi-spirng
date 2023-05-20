package org.buildyourown.timispring.context;

public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
