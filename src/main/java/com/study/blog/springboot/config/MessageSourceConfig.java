package com.study.blog.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageSourceConfig {
    private final String locale;

    public MessageSourceConfig(@Value("${spring.web.locale}") String locale) {
        this.locale = locale;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource mSource = new ReloadableResourceBundleMessageSource();
        mSource.setBasename("classpath:messages/messages");
        mSource.setDefaultEncoding("UTF-8");
        return mSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver lResolver = new SessionLocaleResolver();
        if (locale == null) {
            lResolver.setDefaultLocale(Locale.getDefault());
        } else {
            lResolver.setDefaultLocale(new Locale(locale));
        }
        return lResolver;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
