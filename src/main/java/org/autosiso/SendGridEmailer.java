package org.autosiso;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class SendGridEmailer {
  public void sendSignInEmail(String userEmail) throws IOException {
    Email to = new Email(userEmail);
    String subject = "Auto sign in";
    Content content = new Content("text/html",
        "Hi <strong>" + userEmail.subSequence(0, userEmail.indexOf(".")) + "</strong>, You are successfully signed in ");
    Mail mail = new Mail(getFromEmail(), subject, to, content);
    sendEmail(mail);
  }

  public void sendSignOutEmail(String userEmail) throws IOException {
    Email to = new Email(userEmail);
    String subject = "Auto sign out";
    Content content = new Content("text/html",
        "Hi <strong>" + userEmail.subSequence(0, userEmail.indexOf(".")) + "</strong>, You are successfully logged out ");
    Mail mail = new Mail(getFromEmail(), subject, to, content);
    sendEmail(mail);
  }

  public void sendEmailToCheckInManually(String userEmail) throws IOException {
    Email to = new Email(userEmail);
    String subject = "Manual check In";
    Content content = new Content("text/html", "Hi <strong>" + userEmail.subSequence(0, userEmail.indexOf("."))
        + "</strong>, There was some technical issue please log-in manually ");
    Mail mail = new Mail(getFromEmail(), subject, to, content);
    sendEmail(mail);
  }


  private void sendEmail(Mail mail) throws IOException {
    SendGrid sg = new SendGrid("<SENDGRID_API_KEY>");
    Request request = new Request();

    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");
    request.setBody(mail.build());

    Response response = sg.api(request);

    System.out.println(response.getStatusCode());
    System.out.println(response.getHeaders());
    System.out.println(response.getBody());
  }

  private Email getFromEmail() {
    return new Email("zohoautomation90@gmail.com");
  }
}
