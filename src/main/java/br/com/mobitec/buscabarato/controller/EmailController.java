/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mobitec.buscabarato.controller;

import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.mobitec.buscabarato.model.Usuario;
import br.com.mobitec.buscabarato.util.Linker;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

/**
 *
 * @author Sensum
 */
public class EmailController {
    
    private final Environment env;
    private final Mailer mailer;
    private final ServletContext context;
    private static final Logger logger = Logger.getLogger(EmailController.class);
    private final Linker link;
    
    /**
     * @deprecated CDI eyes only
     */
    protected EmailController() {
        this(null, null, null, null);
    }

    @Inject
    public EmailController(Environment env, Mailer mailer, ServletContext context, Linker link) {
        this.env = env;
	this.mailer = mailer;
        this.context = context;
        this.link = link;
    }

    public void send(@Observes Usuario usuario) throws EmailException {
        
        logger.info("Enviando e-mail para "+ usuario.getEmail());
        
        //SimpleEmail email = new SimpleEmail();
        HtmlEmail email = new HtmlEmail();
        
        email.addTo(usuario.getEmail());
        email.setSubject("Acesso ao Sistema Busca Barato");
        //email.setMsg("Para ativar o seu cadastro, clique no link abaixo.\n"+ usuario.getToken());
        email.setHtmlMsg(carregaTemplateHTML(usuario));
        
        
        mailer.send(email);
        
        logger.info("E-mail enviado");
    }
    
    protected String carregaTemplateHTML(Usuario usuario) {
        String arquivo = context.getRealPath("/WEB-INF/template/nova_conta.html"); //"nova_conta.html";
        link.buildLinkTo(UsuarioController.class).ativar(usuario.getToken());
        
        Map<String, String> input = new HashMap<>();
               input.put("#nome#", usuario.getNome());
               input.put("#link#", link.getURL() )  ;
        
        return readEmailFromHtml(arquivo, input);
    }
    
    /**
     * Lê o arquivo HTML e retorna a string com as palavras chaves já trocadas
     * @param filePath
     * @param input
     * @return 
     */
    protected String readEmailFromHtml(String filePath, Map<String, String> input) {
        String msg = readContentFromFile(filePath);
        try {
            Set<Entry<String, String>> entries = input.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return msg;
    }
    
    
    /**
     * Lê o arquivo HTML 
     * @param fileName
     * @return string com o conteúdo do arquivo
     */
    private String readContentFromFile(String fileName) {
        StringBuilder contents = new StringBuilder();

        try {
            //use buffering, reading one line at a time
            //BufferedReader reader = new BufferedReader(new FileReader(fileName));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
            try {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return contents.toString();
    }
    
}
