/**
 * The MessageSender class sends a single "Hello World!"
 * message to a Java Message System (JMS) queue.  This  
 * program is to be run in conjunction with the 
 * MessageReceiver class, which receives the message 
 * via the JMS.
 * To run the program: The JMS provider should be started, 
 * and a queue should have been created.  The name of the 
 * queue should be specified as command-line argument 
 * when this program is run. 
 *    M. Liu, based on examples in Java Message Service  
 *    Tutorial,http://java.sun.com/products/jms/tutorial/
 */
import javax.jms.*;          // for JMS classes
import javax.naming.*;       // for JNDI classes

public class MessageSender {

    public static void main(String[] args) {
        String                  queueName = null;
        Context                 jndiContext = null;
        QueueConnectionFactory  queueConnectionFactory 
                                   = null;
        QueueConnection         queueConnection = null;
        QueueSession            queueSession = null;
        Queue                   queue = null;
        QueueSender             queueSender = null;
        TextMessage             message = null;
        final int               NUM_MSGS;
 
        
        if ( (args.length != 1) ) {
            System.out.println("Usage: MessageSender ");
            System.exit(1);
        }
        queueName = new String(args[0]);
        System.out.println("Queue name is " + queueName);
       
        /* Create a JNDI InitialContext object if none 
           already exists  */

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

            /* create session */
            queueSession = 
                queueConnection.createQueueSession(false, 
                    Session.AUTO_ACKNOWLEDGE);

            /* create sender and a message object */
            queueSender = queueSession.createSender(queue);
            message = queueSession.createTextMessage();

            /* set the message */
            message.setText("Hello World!");
            System.out.println("Sending message: " + 
                    message.getText());

            /* send it to the JMS  */
            queueSender.send(message); 
            
        } catch (JMSException e) {
            System.out.println("Exception occurred: " + 
                e.toString());
        } finally {
            /* close the queue connection */
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {}
            }
        } //end finally
    }  //end main
} //end class
