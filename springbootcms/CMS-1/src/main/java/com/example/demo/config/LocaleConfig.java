package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class LocaleConfig extends AcceptHeaderLocaleResolver implements WebMvcConfigurer
{

    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String BASENAME_ERROR_MESSAGES = "classpath:errorMessages";
    public static final String BASENAME_MESSAGES = "classpath:messages";
    public static final Long CACHEMILLIS = 3600L;
    public static final Boolean USEDEFAULTCODEMESSAGE = true;
    @Override
    public Locale resolveLocale(HttpServletRequest request)
    {
        if (ObjectUtils.isEmpty(request.getHeader(ACCEPT_LANGUAGE)))
        {
            return Locale.US;
        }
        else
        {
            return new Locale(request.getHeader(ACCEPT_LANGUAGE));
        }
    }

    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(BASENAME_ERROR_MESSAGES, BASENAME_MESSAGES);
        //refresh cache once per hour
        messageSource.setCacheMillis(CACHEMILLIS);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setUseCodeAsDefaultMessage(USEDEFAULTCODEMESSAGE);
        return messageSource;
    }
}
