package com.organization.event.process;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.organization.event.model.OrganizationChangeModel;
import com.organization.utils.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProcessor {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProcessor.class);

    @Value("${action.operation.change.topic}")
    private String OUTPUT_TOPIC;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProcessor(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void process(String action, String organizationId) {

        logger.debug("Sending Kafka messages {} for OrganizationId: {}", action, organizationId);

        OrganizationChangeModel change = new OrganizationChangeModel(
                OrganizationChangeModel.class.getTypeName(), action, organizationId, UserContext.getCorrelationId());
        ObjectMapper mapper = new ObjectMapper();

        try {
            kafkaTemplate.send(OUTPUT_TOPIC, mapper.writeValueAsString(change));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
