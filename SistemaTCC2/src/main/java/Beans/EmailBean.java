package Beans;

import java.util.logging.Level;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.*;

@Stateless
public class EmailBean {
    
    @Resource(name = "java:jboss/mail/Gmail") //Nome do Recurso que criamos no Wildfly
    private Session session; //Objeto que vai reprensentar uma sessão de email
    
    private static final Logger log = LoggerFactory.getLogger(EmailBean.class); //Opcional apenas para lo
    
    public EmailBean() {
    }

    @Asynchronous //Metodo Assíncrono para que a aplicação continue normalmente sem ficar bloqueada até que o email seja enviado    
    public void send(String to, String subject, String body) {
    	try {
    		Message message = new MimeMessage(session);
    		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
    		message.setSubject(subject);
    		message.setText(body);
    		
    		Transport.send(message);
    		log.debug("Email enviado");
    	}catch (MessagingException e) {
    		log.error("Erro a enviar o email : " + e.getMessage());
    	}
        
    }
}
