package com.rabbitmq.spring.connection;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RabbitConnectionFactory implements DisposableBean {

    private static final Log log = LogFactory.getLog(RabbitConnectionFactory.class);

    // spring injected
    private ConnectionFactory connectionFactory;
    private String[] hosts;
    private ShutdownListener[] shutdownListeners;

    private Connection connection;
    private Address[] knownHosts;

    public synchronized Connection getConnection() throws IOException {

        if (knownHosts == null) {
            collectInitialKnownHosts();
        }

        while (connection == null || !connection.isOpen()) {

            if (log.isInfoEnabled()) {
                log.info(String.format("Establishing connection to one of [%s] using virtualhost [%s]", ObjectUtils.nullSafeToString(hosts), connectionFactory.getVirtualHost()));
            }

            try {
                connection = connectionFactory.newConnection(knownHosts);

                // always keep the original hosts
                Set<Address> hosts = new HashSet<Address>(Arrays.asList(knownHosts));
                hosts.addAll(Arrays.asList(knownHosts));
                knownHosts = hosts.toArray(new Address[hosts.size()]);

                if (log.isDebugEnabled()) {
                    log.debug(String.format("New known hosts list is [%s]", ObjectUtils.nullSafeToString(knownHosts)));
                }

                addShutdownListeners();

                if (log.isInfoEnabled()) {
                    log.info(String.format("Connected to [%s:%d]", connection.getAddress(), connection.getPort()));
                }
            } catch (Exception e) {
                log.error("Error connecting, trying again in 5 seconds...", e);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e1) {
                    log.warn("Interrupted while waiting");
                }
            }

        }

        return connection;

    }

    private void collectInitialKnownHosts() {
        List<Address> addresses = new ArrayList<Address>(hosts.length);
        for (String host : hosts) {
            addresses.add(Address.parseAddress(host));
        }
        knownHosts = addresses.toArray(new Address[hosts.length]);
    }

    private void addShutdownListeners() {
        if (shutdownListeners != null) {
            for (ShutdownListener shutdownListener : shutdownListeners) {
                connection.addShutdownListener(shutdownListener);
            }
        }
    }

    public void destroy() throws Exception {
        if (connection != null && connection.isOpen()) {
            connection.close();
        }
    }

    @Required
    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Required
    public void setHosts(String[] hosts) {
        this.hosts = hosts;
    }

    public void setShutdownListeners(ShutdownListener... shutdownListeners) {
        this.shutdownListeners = shutdownListeners;
    }
}