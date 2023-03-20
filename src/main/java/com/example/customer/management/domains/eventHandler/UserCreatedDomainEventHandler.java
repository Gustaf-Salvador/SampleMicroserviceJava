package com.example.customer.management.domains.eventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.customer.management.domains.event.UserCreatedDomainEvent;
import com.example.customer.management.domains.event.UserRemovedDomainEvent;
import lombok.AllArgsConstructor;
import io.mediator.core.CommandHandler;

@AllArgsConstructor
@Component
public class UserCreatedDomainEventHandler implements CommandHandler<UserCreatedDomainEvent> {

    private final static Logger logger = LoggerFactory.getLogger(UserCreatedDomainEventHandler.class);

    @Override
    public void handle(UserCreatedDomainEvent userRemovedDomainEvent) {
        logger.info("User " + userRemovedDomainEvent.getCustomer().getId() +" Created");
    }
}
