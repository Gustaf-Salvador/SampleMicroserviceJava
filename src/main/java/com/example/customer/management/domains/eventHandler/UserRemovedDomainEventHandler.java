package com.example.customer.management.domains.eventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.customer.management.domains.event.UserRemovedDomainEvent;
import lombok.AllArgsConstructor;
import io.mediator.core.CommandHandler;

@AllArgsConstructor
@Component
public class UserRemovedDomainEventHandler implements CommandHandler<UserRemovedDomainEvent> {

    private final static Logger logger = LoggerFactory.getLogger(UserRemovedDomainEventHandler.class);

    @Override
    public void handle(UserRemovedDomainEvent userRemovedDomainEvent) {
        logger.info("User " + userRemovedDomainEvent.getCustomer().getId() +" Removed");

    }
}
