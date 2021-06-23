package com.hack.azure.mediknot.service;

public interface EmailService {
    public void sendText(String from, String to, String subject, String body);
    public void sendHTML(String from, String to, String subject, String body);
}
