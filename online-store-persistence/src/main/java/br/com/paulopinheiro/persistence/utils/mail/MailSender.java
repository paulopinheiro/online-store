package br.com.paulopinheiro.persistence.utils.mail;

public interface MailSender {
    void sendEmail(String sendTo, String messageToSend);
}
