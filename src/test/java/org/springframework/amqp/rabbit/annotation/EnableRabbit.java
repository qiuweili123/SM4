/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.amqp.rabbit.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enable Rabbit listener annotated endpoints that are created under the cover
 * by a {@link org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory
 * RabbitListenerContainerFactory}. To be used on
 * {@link org.springframework.context.annotation.Configuration Configuration}
 * classes as follows:
 * <p>
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableRabbit
 * public class AppConfig {
 * &#064;Bean
 * public SimpleRabbitListenerContainerFactory myRabbitListenerContainerFactory() {
 * SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
 * factory.setConnectionFactory(connectionFactory());
 * factory.setMaxConcurrentConsumers(5);
 * return factory;
 * }
 * // other &#064;Bean definitions
 * }</pre>
 * <p>
 * The {@code RabbitListenerContainerFactory} is responsible to create the listener container
 * responsible for a particular endpoint. Typical implementations, as the
 * {@link org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory SimpleRabbitListenerContainerFactory}
 * used in the sample above, provides the necessary configuration options that are supported by
 * the underlying {@link org.springframework.amqp.rabbit.listener.MessageListenerContainer MessageListenerContainer}.
 * <p>
 * <p>{@code @EnableRabbit} enables detection of {@link RabbitListener} annotations on any
 * Spring-managed bean in the container. For example, given a class {@code MyService}:
 * <p>
 * <pre class="code">
 * package com.acme.foo;
 * <p>
 * public class MyService {
 * &#064;RabbitListener(containerFactory="myRabbitListenerContainerFactory", queues="myQueue")
 * public void process(String msg) {
 * // process incoming message
 * }
 * }</pre>
 * <p>
 * The container factory to use is identified by the {@link RabbitListener#containerFactory() containerFactory}
 * attribute defining the name of the {@code RabbitListenerContainerFactory} bean to use.  When none
 * is set a {@code RabbitListenerContainerFactory} bean with name {@code rabbitListenerContainerFactory} is
 * assumed to be present.
 * <p>
 * <p>the following configuration would ensure that every time a {@link org.springframework.amqp.core.Message}
 * is received on the {@link org.springframework.amqp.core.Queue} named "myQueue", {@code MyService.process()}
 * is called with the content of the message:
 * <p>
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableRabbit
 * public class AppConfig {
 * &#064;Bean
 * public MyService myService() {
 * return new MyService();
 * }
 * <p>
 * // Rabbit infrastructure setup
 * }</pre>
 * <p>
 * Alternatively, if {@code MyService} were annotated with {@code @Component}, the
 * following configuration would ensure that its {@code @RabbitListener} annotated
 * method is invoked with a matching incoming message:
 * <p>
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableRabbit
 * &#064;ComponentScan(basePackages="com.acme.foo")
 * public class AppConfig {
 * }</pre>
 * <p>
 * Note that the created containers are not registered against the application context
 * but can be easily located for management purposes using the
 * {@link org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry RabbitListenerEndpointRegistry}.
 * <p>
 * <p>Annotated methods can use flexible signature; in particular, it is possible to use
 * the {@link org.springframework.messaging.Message Message} abstraction and related annotations,
 * see {@link RabbitListener} Javadoc for more details. For instance, the following would
 * inject the content of the message and a a custom "myCounter" AMQP header:
 * <p>
 * <pre class="code">
 * &#064;RabbitListener(containerFactory = "myRabbitListenerContainerFactory", queues = "myQueue")
 * public void process(String msg, @Header("myCounter") int counter) {
 * // process incoming message
 * }</pre>
 * <p>
 * These features are abstracted by the {@link org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory
 * MessageHandlerMethodFactory} that is responsible to build the necessary invoker to process
 * the annotated method. By default, {@link org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory
 * DefaultMessageHandlerMethodFactory} is used.
 * <p>
 * <p>When more control is desired, a {@code @Configuration} class may implement
 * {@link RabbitListenerConfigurer}. This allows access to the underlying
 * {@link org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar RabbitListenerEndpointRegistrar}
 * instance. The following example demonstrates how to specify an explicit default
 * {@code RabbitListenerContainerFactory}
 * <p>
 * <pre class="code">
 * {@code
 * &#064;Configuration
 * &#064;EnableRabbit
 * public class AppConfig implements RabbitListenerConfigurer {
 * &#064;Override
 * public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
 * registrar.setContainerFactory(myRabbitListenerContainerFactory());
 * }
 * <p>
 * &#064;Bean
 * public RabbitListenerContainerFactory<?> myRabbitListenerContainerFactory() {
 * // factory settings
 * }
 * <p>
 * &#064;Bean
 * public MyService myService() {
 * return new MyService();
 * }
 * }
 * }</pre>
 * <p>
 * For reference, the example above can be compared to the following Spring XML
 * configuration:
 * <pre class="code">
 * {@code <beans>
 * <rabbit:annotation-driven container-factory="myRabbitListenerContainerFactory"/>
 * <p>
 * <bean id="myRabbitListenerContainerFactory"
 * class="org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory">
 * // factory settings
 * </bean>
 * <p>
 * <bean id="myService" class="com.acme.foo.MyService"/>
 * </beans>
 * }</pre>
 * <p>
 * It is also possible to specify a custom {@link org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry
 * RabbitListenerEndpointRegistry} in case you need more control on the way the containers
 * are created and managed. The example below also demonstrates how to customize the
 * {@code RabbitHandlerMethodFactory} to use with a custom {@link org.springframework.validation.Validator
 * Validator} so that payloads annotated with {@link org.springframework.validation.annotation.Validated
 * Validated} are first validated against a custom {@code Validator}.
 * <p>
 * <pre class="code">
 * {@code
 * &#064;Configuration
 * &#064;EnableRabbit
 * public class AppConfig implements RabbitListenerConfigurer {
 * &#064;Override
 * public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
 * registrar.setEndpointRegistry(myRabbitListenerEndpointRegistry());
 * registrar.setMessageHandlerMethodFactory(myMessageHandlerMethodFactory);
 * }
 * <p>
 * &#064;Bean
 * public RabbitListenerEndpointRegistry<?> myRabbitListenerEndpointRegistry() {
 * // registry configuration
 * }
 * <p>
 * &#064;Bean
 * public RabbitHandlerMethodFactory myMessageHandlerMethodFactory() {
 * DefaultRabbitHandlerMethodFactory factory = new DefaultRabbitHandlerMethodFactory();
 * factory.setValidator(new MyValidator());
 * return factory;
 * }
 * <p>
 * &#064;Bean
 * public MyService myService() {
 * return new MyService();
 * }
 * }
 * }</pre>
 * <p>
 * For reference, the example above can be compared to the following Spring XML
 * configuration:
 * <pre class="code">
 * {@code <beans>
 * <rabbit:annotation-driven registry="myRabbitListenerEndpointRegistry"
 * handler-method-factory="myRabbitHandlerMethodFactory"/&gt;
 * <p>
 * <bean id="myRabbitListenerEndpointRegistry"
 * class="org.springframework.amqp.rabbit.config.RabbitListenerEndpointRegistry">
 * // registry configuration
 * </bean>
 * <p>
 * <bean id="myRabbitHandlerMethodFactory"
 * class="org.springframework.amqp.rabbit.config.DefaultRabbitHandlerMethodFactory">
 * <property name="validator" ref="myValidator"/>
 * </bean>
 * <p>
 * <bean id="myService" class="com.acme.foo.MyService"/>
 * </beans>
 * }</pre>
 * <p>
 * Implementing {@code RabbitListenerConfigurer} also allows for fine-grained
 * control over endpoints registration via the {@code RabbitListenerEndpointRegistrar}.
 * For example, the following configures an extra endpoint:
 * <p>
 * <pre class="code">
 * {@code
 * &#064;Configuration
 * &#064;EnableRabbit
 * public class AppConfig implements RabbitListenerConfigurer {
 * &#064;Override
 * public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
 * SimpleRabbitListenerEndpoint myEndpoint = new SimpleRabbitListenerEndpoint();
 * // ... configure the endpoint
 * registrar.registerEndpoint(endpoint, anotherRabbitListenerContainerFactory());
 * }
 * <p>
 * &#064;Bean
 * public MyService myService() {
 * return new MyService();
 * }
 * <p>
 * &#064;Bean
 * public RabbitListenerContainerFactory<?> anotherRabbitListenerContainerFactory() {
 * // ...
 * }
 * <p>
 * // Rabbit infrastructure setup
 * }
 * }</pre>
 * <p>
 * Note that all beans implementing {@code RabbitListenerConfigurer} will be detected and
 * invoked in a similar fashion. The example above can be translated in a regular bean
 * definition registered in the context in case you use the XML configuration.
 *
 * @author Stephane Nicoll
 * @see RabbitListener
 * @see RabbitListenerAnnotationBeanPostProcessor
 * @see org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar
 * @see org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry
 * @since 1.4
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RabbitBootstrapConfiguration.class)
public @interface EnableRabbit {
}
