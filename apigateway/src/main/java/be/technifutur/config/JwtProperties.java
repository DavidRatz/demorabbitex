package be.technifutur.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.*;

@Component
@ConfigurationProperties("application.jwt")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtProperties {
    private String secret;
    private long expires;
    private String prefix;
    private String header;


}
