package com.pi4home.configurations;

import com.pi4home.messageBroker.LightsQueueConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")
@Configuration
public class RabbitConfiguration
{
    private static final String LISTENER_METHOD_LIGHTS = "receiveMessageLights";
    private static final String LISTENER_METHOD_YEELIGHTS = "receiveMessageYeelights";
    private static final String LISTENER_METHOD_BLINDS = "receiveMessageBlinds";

    @Value("${lights.queue.name}")
    private String queueNameLights;
    @Value("${lights.fanout.exchange}")
    private String fanoutExchangeLights;

    @Value("${blinds.queue.name}")
    private String queueNameBlinds;
    @Value("${blinds.fanout.exchange}")
    private String fanoutExchangeBlinds;

    @Value("${yeelights.queue.name}")
    private String queueNameYeelights;
    @Value("${yeelights.fanout.exchange}")
    private String fanoutExchangeYeelights;

    @Bean
    Queue queueLights() {
        return new Queue(queueNameLights, true);
    }
    @Bean
    FanoutExchange exchangeLights() {
        return new FanoutExchange(fanoutExchangeLights);
    }

    @Bean
    Queue queueYeelights() {
        return new Queue(queueNameYeelights, true);
    }
    @Bean
    FanoutExchange exchangeYeelights() {
        return new FanoutExchange(fanoutExchangeYeelights);
    }

    @Bean
    Queue queueBlinds() {
        return new Queue(queueNameBlinds, true);
    }
    @Bean
    FanoutExchange exchangeBlinds() {
        return new FanoutExchange(fanoutExchangeBlinds);
    }

    @Bean
    Binding bindingLights(Queue queueLights, FanoutExchange exchangeLights) {
        return BindingBuilder.bind(queueLights).to(exchangeLights);
    }
    @Bean
    Binding bindingBlinds(Queue queueBlinds, FanoutExchange exchangeBlinds) {
        return BindingBuilder.bind(queueBlinds).to(exchangeBlinds);
    }
    @Bean
    Binding bindingYeelihts(Queue queueYeelights, FanoutExchange exchangeYeelights) {
        return BindingBuilder.bind(queueYeelights).to(exchangeYeelights);
    }

    @Bean
    SimpleMessageListenerContainer containerLights(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapterLights) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNameLights);
        container.setMessageListener(listenerAdapterLights);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerBlinds(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapterBlinds) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNameBlinds);
        container.setMessageListener(listenerAdapterBlinds);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerYeelights(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapterYeelights) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueNameYeelights);
        container.setMessageListener(listenerAdapterYeelights);
        return container;
    }


    @Bean
    MessageListenerAdapter listenerAdapterLights(LightsQueueConsumer consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD_LIGHTS);
    }

    @Bean
    MessageListenerAdapter listenerAdapterBlinds(LightsQueueConsumer consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD_BLINDS);
    }
    @Bean
    MessageListenerAdapter listenerAdapterYeelights(LightsQueueConsumer consumer) {
        return new MessageListenerAdapter(consumer, LISTENER_METHOD_YEELIGHTS);
    }
}