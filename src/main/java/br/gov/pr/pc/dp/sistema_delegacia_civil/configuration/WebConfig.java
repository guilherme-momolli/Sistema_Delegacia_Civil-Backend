package br.gov.pr.pc.dp.sistema_delegacia_civil.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String basePath = System.getProperty("user.home") + "/Documentos/Uploads/";

        registry.addResourceHandler("/uploads/images/**")
                .addResourceLocations("file:" + basePath + "Images/")
                .setCachePeriod(3600);

        registry.addResourceHandler("/uploads/audios/**")
                .addResourceLocations("file:" + basePath + "Audios/")
                .setCachePeriod(3600);

        registry.addResourceHandler("/uploads/videos/**")
                .addResourceLocations("file:" + basePath + "Videos/")
                .setCachePeriod(3600);
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof AbstractHttpMessageConverter<?>) {
                ((AbstractHttpMessageConverter<?>) converter).setDefaultCharset(StandardCharsets.UTF_8);
            }
        }
    }

}

