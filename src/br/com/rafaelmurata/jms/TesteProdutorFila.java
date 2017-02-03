package br.com.rafaelmurata.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TesteProdutorFila {

	public static void main(String[] args) throws JMSException, NamingException {

		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection(); 
		connection.start();
		
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		
		Destination fila= (Destination) context.lookup("financeiro");
		
		MessageProducer producer = session.createProducer(fila);
		
		Message message =  session.createTextMessage("<pedido> <id>12</id> </pedido> ");
		producer.send(message );
	/*	for (int i = 0; i < 1000; i++) {
			Message message =  session.createTextMessage("<pedido> <id>"+i+"</id> </pedido> ");
			producer.send(message );
			
		}
		*/
		
		
		//new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}

}
