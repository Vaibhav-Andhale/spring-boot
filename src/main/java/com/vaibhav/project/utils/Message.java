package com.vaibhav.project.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class Message {
    private final MessageSource messageSource;
    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key, Object... args){
        return messageSource.getMessage(key,args, LocaleContextHolder.getLocale());
    }
}
