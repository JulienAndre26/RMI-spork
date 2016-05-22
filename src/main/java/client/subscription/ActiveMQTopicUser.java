package client.subscription;

import client.Client;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Mother class for a topic user
 */
abstract class ActiveMQTopicUser {
    /** ActiveMQ DNS */
    private final static String ACTIVE_MQ_DNS = "localhost";

    /** ActiveMQ port */
    private final static String ACTIVE_MQ_PORT = "61616";

    /** ActiveMQ url */
    private final static String ACTIVE_MQ_URL = "tcp://" + ACTIVE_MQ_DNS + ":" + ACTIVE_MQ_PORT;

    /** ActiveMQ user password */
    private final static String ACTIVE_MQ_PW = "user";

    /** ActiveMQ user key */
    private final static String ACTIVE_MQ_USER = "user";

    /** Client */
    Client client;

    /** The connection */
    private TopicConnection connect = null;

    /** The user session */
    TopicSession session = null;
    /** The user topic */
    Topic topic = null;

    /**
     * Constructor for topic user in order to have information about the client using it
     * @param client
     */
    public ActiveMQTopicUser(Client client){
        this.client = client;
    }

    /** Connect the user to activeMQ */
    void connect(String topicKey) {
        // connection factory
        TopicConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVE_MQ_USER, ACTIVE_MQ_PW, ACTIVE_MQ_URL);
        try {
            connect = factory.createTopicConnection(ACTIVE_MQ_USER, ACTIVE_MQ_PW); // connection
            session = connect.createTopicSession(false, Session.AUTO_ACKNOWLEDGE); // session creation
            topic = session.createTopic(topicKey);
            configure(); // specific stuff of the topic user
            connect.start(); // start after configuration done
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure for specific user, i.e. publisher or subscriber
     */
    abstract void configure() throws JMSException;
}
