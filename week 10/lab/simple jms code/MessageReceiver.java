/**
 * The MessageReceiver class is to be used with  
 * the MessageSender class, which sends a message
 * via the JMS.
 * The same queue name as specified with MessageSender
 * should be entered as command line argument.
 *    M. Liu, based on examples in Java Message Service
 *    Tutorial,http://java.sun.com/products/jms/tutorial/
 */

import javax.jms.*;
import javax.naming.*;

public class MessageReceiver {

    public static void main(String[] args) {
        String                  queueName = null;
        Context                 jndiContext = null;
        QueueConnectionFactory  queueConnectionFactory 
                                   = null;
        QueueConnection         queueConnection = null;
        QueueSession            queueSession = null;
        Queue                   queue = null;
        QueueReceiver           queueReceiver = null;
        TextMessage             message = null;
                
        if (args.length != 1) {
            System.out.println("Usage: java " +
                "SimpleQueueReceiver <queue-name>");
            System.exit(1);
        }
        queueName = new String(args[0]);
        System.out.println("Queue name is " + queueName);
        
        /* Create a JNDI InitialContext object if none 
           exists */
        
        try {
            jndiContext = new InitialContext();
        } catch (NamingException e) {
            System.out.println("Could not create JNDI " +
                "context: " + e.toString());
            System.exit(1);
        }
        
        /* Look up connection factory and queue.  If either
           does not exist, exit. */
        try {
            queueConnectionFactory = 
              (QueueConnectionFactory)
              jndiContext.lookup("QueueConnectionFactory");
            queue = (Queue) jndiContext.lookup(queueName);
        } catch (NamingException e) {
            System.out.println("JNDI lookup failed: " +
                e.toString());
            System.exit(1);
        }

        try {
            /* create connection */
            queueConnection = 
              queueConnectionFactory.createQueueConnection();
            /* create session from connection */
            queueSession = 
                queueConnection.createQueueSession(false, 
                    Session.AUTO_ACKNOWLEDGE);
            /* create a receiver */
            queueReceiver = queueSession.createReceiver(queue);
            queueConnection.start();
            /* receive the message */
            Message m = queueReceiver.receive(1);
            message = (TextMessage) m;
            System.out.println("Reading message: " +
                                message.getText());
        } catch (JMSException e) {
            System.out.println("Exception occurred: " + 
                e.toString());
        } finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {}
            }
        }//end finally
    }//end main
}//end class
