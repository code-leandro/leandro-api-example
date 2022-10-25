package com.leandrosouza.leandroagrotis.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ConfigBeansTest {

    @Autowired
    ConfigBeans configBeans;

    @Test
    void messageSource() {
        MessageSource messageSource = configBeans.messageSource();
        assertThat(messageSource).isNotNull();
    }

    @Test
    void locale() {
        Locale locale = configBeans.locale();
        assertThat(locale).isNotNull();
        assertThat(locale.getCountry()).isEqualTo("BR");
        assertThat(locale.getLanguage()).isEqualTo("pt");
    }
}