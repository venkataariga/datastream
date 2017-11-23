package com.solace.connector;

import org.apache.log4j.Logger;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.receiver.Receiver;

import com.solacesystems.jcsmp.BytesXMLMessage;
import com.solacesystems.jcsmp.TextMessage;
import com.solacesystems.jms.SupportedProperty;

import java.util.Hashtable;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener; 
import javax.jms.JMSException; 
import javax.jms.Message;
import javax.jms.MessageConsumer; 
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;


public class JMSReceiver extends Receiver<String> implements MessageListener{
	
	private static final Logger log = Logger.getLogger(JMSReceiver.class);
	
	private static final String SOLJMS_INITIAL_CONTEXT_FACTORY =	"com.solacesystems.jndi.SolJNDIInitialContextFactory";
	private StorageLevel _storageLevel;
	private String _brokerURL; 
	private String _vpn;
	private String _username;
	private String _password;
	private String _queueName; private String _connectionFactory; 
	private Connection _connection;

	public JMSReceiver(String brokerURL, String vpn, String username, String password, String queueName, String connectionFactory, StorageLevel storageLevel)
	{
	super(storageLevel);
	_storageLevel = storageLevel; _brokerURL = brokerURL;
	_vpn = vpn;
	_username = username;
	_password = password;
	_queueName = queueName; 
	_connectionFactory = connectionFactory;
	}

	
	public void onStart() {
		System.out.println("Starting up..."); try
		{
			
			Hashtable<String, String> env = new Hashtable<String, String>(); 
			env.put(InitialContext.INITIAL_CONTEXT_FACTORY,
					SOLJMS_INITIAL_CONTEXT_FACTORY);
             env.put(InitialContext.PROVIDER_URL, _brokerURL);
             env.put(Context.SECURITY_PRINCIPAL, _username);
             env.put(Context.SECURITY_CREDENTIALS, _password); 
             env.put(SupportedProperty.SOLACE_JMS_VPN, _vpn);
             javax.naming.Context context = new javax.naming.InitialContext(env);
             ConnectionFactory factory = (ConnectionFactory)
            		 context.lookup(_connectionFactory);
              Destination queue = (Destination) context.lookup(_queueName);
              System.out.println("queue"+queue);
            _connection = factory.createConnection(); 
             _connection.setExceptionListener(new JMSReceiverExceptionListener());
           Session session = _connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
           System.out.println("Session is : "+session);
           MessageConsumer consumer;
           consumer = session.createConsumer(queue); 
           consumer.setMessageListener(this);
               _connection.start();
           System.out.println("Completed startup.");
		} catch (Exception ex)
		{
		    // Caught exception, try a restart
		System.out.println("Callback onStart caught exception, restarting "+ ex);
		restart("Callback onStart caught exception, restarting ", ex);
		    }
		}
	public void onStop() {
		System.out.println("Callback onStop called"); try
		{
		_connection.close(); 
		
		} catch (JMSException ex) {
		System.out.println("onStop exception"+ ex); }
		}
	
	@Override
	public void onMessage(Message message) {
		String	stringMessage  = "";
		if (message instanceof BytesMessage){
			BytesMessage byteMessage = (BytesMessage) message;
			byte[] byteData = null;
			
			try {
				byteData = new byte[(int) byteMessage.getBodyLength()];
				byteMessage.readBytes(byteData);
				byteMessage.reset();
				stringMessage =  new String(byteData);
	
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			}

	
	store(stringMessage);
	//try {
//	message.acknowledge(); 
	
//	} catch (JMSException ex) {
//	System.out.println("Callback onMessage failed to ack message"+ ex);
	//}
	}

	private class JMSReceiverExceptionListener implements ExceptionListener
	{
		@Override
		public void onException(JMSException ex) {
			System.out.println("JMS exceptionListener caught exception, , restarting "+ ex);
			restart("JMS exceptionListener caught exception, , restarting ");
		}
}

	
	@Override
	public String toString(){
		return "JMSReceiver{" +
				"brokerURL='" + _brokerURL + '\'' +
				", vpn='" + _vpn + '\'' +
				", username='" + _username + '\'' +
				", queueName='" + _queueName + '\'' +
				", connectionFactory='" + _connectionFactory + '\'' +
				'}';
}
}


