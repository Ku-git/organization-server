package com.organization.config;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        //預設是false，改為true之後就可以在zipkin看到相關kafka傳送的行為，但是如要想在接收方看到相關行為
        //則需要再接收方那新增相關屬性:spring.kafka.listener.observation-enabled=true
        kafkaTemplate.setObservationEnabled(true);
        //同上述的行為但可以新增額外的資訊到kafka topic的message，暫且保留
        //若要實作，需要額外使用bean初始化
//        kafkaTemplate.setProducerInterceptor(tracingKafkaInterceptor());

        return kafkaTemplate;
    }


}
