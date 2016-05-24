package collection.subscription;

import client.Client;

import javax.jms.JMSException;
import javax.jms.MessageListener;

/**
 * Class for a topic subscriber
 */
public class Subscriber extends ActiveMQTopicUser {

    private MessageListener listener;

    public Subscriber(Client c, MessageListener ml){
        super(c);
        this.listener = ml;
    }

    @Override
    void configure() throws JMSException {
        session.createSubscriber(topic).setMessageListener(listener);
    }

    /**
     * Subscribe to a topic
     * @param topicKey the topic key
     */
    public void subscribe(String topicKey){
        connect(topicKey);
    }
}
