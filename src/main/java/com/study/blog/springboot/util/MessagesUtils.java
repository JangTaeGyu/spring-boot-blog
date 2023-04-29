package com.study.blog.springboot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
public class MessagesUtils {
    private static MessageSource messageSource;
    private static String language;

    public MessagesUtils(MessageSource messageSource, @Value("${spring.web.locale}") String language) {
        MessagesUtils.messageSource = messageSource;
        MessagesUtils.language = language;
    }

    public static String by(String key) {
        try {
            return messageSource.getMessage(key, null, new Locale(language));
        } catch (NoSuchMessageException e) {
            log.error(e.getMessage());
            return key;
        }
    }
}
