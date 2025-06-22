package eguce3g.bns.reglemetier.config;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public KieContainer kieContainer() {
        return KieServices.Factory.get().newKieClasspathContainer("session-drools");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow specific origins (replace "*" with "http://localhost:8000" for production)
        config.addAllowedOriginPattern("*");

        // Allow specific HTTP methods
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");

        // Allow headers
        config.addAllowedHeader("*");

        // Allow credentials (if needed, e.g., for authenticated requests)
        config.setAllowCredentials(true);

        // Set max age for preflight requests
        config.setMaxAge(3600L);

        // Apply CORS configuration to all paths
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
