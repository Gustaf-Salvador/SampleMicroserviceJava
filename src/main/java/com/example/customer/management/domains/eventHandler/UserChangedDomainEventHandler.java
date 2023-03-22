package com.example.customer.management.domains.eventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.customer.management.domains.event.UserChangedDomainEvent;
import lombok.AllArgsConstructor;
import io.mediator.core.CommandHandler;

@AllArgsConstructor
@Component
public class UserChangedDomainEventHandler implements CommandHandler<UserChangedDomainEvent> {

    private final static Logger logger = LoggerFactory.getLogger(UserChangedDomainEventHandler.class);

    @Override
    public void handle(UserChangedDomainEvent userChangedDomainEvent) {
        logger.info("User " + userChangedDomainEvent.getCustomer().getId() +
            "change the field " + userChangedDomainEvent.getFieldName() +
            " from value " + userChangedDomainEvent.getOldValue() +
            " to new value " + userChangedDomainEvent.getNewValue());
    }
}
