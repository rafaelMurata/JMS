package br.com.rafaelmurata.jms;

import java.io.StringWriter;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.bind.JAXB;

import br.com.rafaelmurata.jms.modelo.Pedido;
import br.com.rafaelmurata.jms.modelo.PedidoFactory;

public class TesteProdutorTopico {

	public static void main(String[] args) throws JMSException, NamingException {

		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection(); 
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination topico= (Destination) context.lookup("loja");
		
		MessageProducer producer = session.createProducer(topico);
		
		Pedido pedido = new PedidoFactory().geraPedidoComValores();

	//	StringWriter writer = new StringWriter();

	//	JAXB.marshal(pedido, writer);

	//	String xml = writer.toString();

		Message message = session.createObjectMessage(pedido);

		//message.setBooleanProperty("ebook", true);
		producer.send(message );
		
		//new Scanner(System.in).nextLine();
		
		session.close();
		connection.close();
		context.close();
	}

}
