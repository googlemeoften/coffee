package cn.edu.coffee.utils;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;


/**
 * Created by HeYong on 2017/3/31.
 */
public class MailUtils {

    public static void sendMail(String subject, HashMap<String, Object> data,int totalProfits) throws MessagingException, IOException {

        //邮件发送
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.163.com");
        properties.setProperty("mail.smtp.auth", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("a1919435320", "***a941213a");
            }
        };

        Session session = Session.getInstance(properties, auth);

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("a1919435320@163.com"));//发件人
        msg.setRecipients(Message.RecipientType.TO, "758960151@qq.com");//设置收件人
        msg.setSubject(subject);

        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        for (String key : data.keySet()) {
            sb.append("<tr>")
                    .append("<td>")
                    .append(key)
                    .append("</td>")
                    .append("<td>")
                    .append(data.get(key))
                    .append("</td>")
                    .append("</tr>");
        }

        sb.append("<tr>")
                .append("<td>")
                .append("今日收入")
                .append("</td>")
                .append("<td>")
                .append(totalProfits)
                .append("</td>")
                .append("</tr>")
                .append("</table>");


        msg.setContent(sb.toString(), "text/html;charset=utf-8");

        Transport.send(msg);

    }
}
