package collection.subscription;

import client.Client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;

/**
 * Class for a topic publisher
 */
public class Publisher extends ActiveMQTopicUser {

    private MessageProducer sender = null;
    private Message msg = null;

    public Publisher(Client client){
        super(client);
    }

    @Override
    void configure() throws JMSException {
        sender = session.createPublisher(topic);
    }

    public void publish(String topicKey){
        connect(topicKey);
        try {
            msg = session.createTextMessage(client.getName() + " has update " + topicKey);
            sender.send(msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
