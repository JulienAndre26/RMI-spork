package client.subscription.messagelisteners;

import client.Client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message listener tilter
 */
public class Tilter implements MessageListener {
    private Client client;
    public Tilter(Client client){
        this.client = client;
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(client.getName() + "> " + ((TextMessage)message).getText());
        } catch (JMSException e) {
            System.out.println("Error in casting message in TextMessage.");
            e.printStackTrace();
        }
    }
}
