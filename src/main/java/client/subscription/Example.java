package client.subscription;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Example implements MessageListener {

    private TopicConnection connect = null;
    private TopicSession sendSession = null;
    private TopicSession receiveSession = null;
    private MessageProducer sender = null;
    private Topic topic = null;

    private void configurer() {

        try {    // Create a connection.
            TopicConnectionFactory factory = new ActiveMQConnectionFactory("user", "user", "tcp://localhost:61616");
            connect = factory.createTopicConnection("user", "user");
            // ce programme est donc en mesure d'acc�der au broker ActiveMQ, avec connecteur tcp (openwire)
            // Si le producteur et le consommateur �taient cod�s s�par�ment, ils auraient eu ce m�me bout de code
            System.out.println("Configuring shit...");
            this.configurerProducteur();
            this.configurerConsommateur();
            connect.start(); // on peut activer la connection. 

        } catch (JMSException jmse) {
            jmse.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.produire();
        }
    }

    private void configurerProducteur() throws JMSException {
        // Dans ce programme, on decide que le producteur cr�e la queue

        //La queue etant cr�e, il peut y acc�der en mode producteur, au sein d'une session
        sendSession = connect.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        topic = sendSession.createTopic("PENIS");
        sender = sendSession.createPublisher(topic);
    }

    private void configurerConsommateur() throws JMSException {
        // Pour consommer, il faudra simplement ouvrir une session
        receiveSession = connect.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        receiveSession.createSubscriber(topic).setMessageListener(this);
        // Now that 'receive' setup is complete, start the Connection
    }

    private void produire() {
        TextMessage msg;

        for (int i = 1; i <= 20; i++) {
            //Fabriquer un message
            try {
                msg = sendSession.createTextMessage("Blblbl" + i);
                sender.send(msg);
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Poster ce message dans la queue
            //Question1: fabriquer et poster le message via la console d'aministration
        }
        System.out.println("Messages sent");
    }

    public static void main(String[] args) {
        (new Example()).configurer();
    }

    @Override
    public void onMessage(Message message) {
        // Methode permettant au consommateur de consommer effectivement chaque msg recu
        // via la queue
        System.out.println("Recu un message de ta queue");
        try {
            System.out.println(((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
