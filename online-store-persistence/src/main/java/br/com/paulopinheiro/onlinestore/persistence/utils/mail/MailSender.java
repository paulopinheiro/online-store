package br.com.paulopinheiro.onlinestore.persistence.utils.mail;

public interface MailSender {
    void sendEmail(String sendTo, String messageToSend);
}
