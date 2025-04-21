package fsa.stocks.security.oauth2_jwt;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    /**
     * Configuration of the security filter chain
     *
     * @param jwtDecoder - custom JWT decoder for custom validation
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtDecoder jwtDecoder) throws Exception {
        return http
                // Enabling CORS
                .cors(Customizer.withDefaults())
                // Disabling CSRF protection
                .csrf(AbstractHttpConfigurer::disable)
                // Configuration of rules for authorizing HTTP requests
                .authorizeHttpRequests(this::configureAuthorizationRules)
                // Configuration of the OAuth2 resource server
                .oauth2ResourceServer(oauth2 -> configureOauth2ResourceServer(oauth2, jwtDecoder))
                // Building the security filter chain
                .build();
    }

    // Method for configuring authorization rules
    private void configureAuthorizationRules(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth
                // Allowing access to actuator endpoints without authorization
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                // All other requests require authorization
                .anyRequest().authenticated();
    }

    // Method for configuring the OAuth2 resource server
    private void configureOauth2ResourceServer(OAuth2ResourceServerConfigurer<HttpSecurity> oauth2, JwtDecoder jwtDecoder) {
        oauth2
                .jwt(jwt -> {
                    jwt.decoder(jwtDecoder);
                    jwt.jwtAuthenticationConverter(JwtConverter::new);
                });
    }

}
