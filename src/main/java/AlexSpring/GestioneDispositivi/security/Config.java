package AlexSpring.GestioneDispositivi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class Config {

	// Per poter configurare a piacimento la Security Filter Chain dobbiamo creare un Bean
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// Possiamo disabilitare dei comportamenti di default
		httpSecurity.formLogin(http -> http.disable()); // Non voglio il form di login (avremo React per quello)
		httpSecurity.csrf(http -> http.disable()); // Non voglio la protezione da CSRF (per l'applicazione media non è necessaria e complicherebbe tutta la faccenda, anche lato FE)
		httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Non voglio le sessioni (perché utilizzeremo la token based authentication con JWT)

		// Possiamo aggiungere dei filtri custom

		// Aggiungere/Rimuovere determinate regole di protezione per gli endpoint
		// Possiamo decidere se debba essere necessaria o meno un'autenticazione per accedere agli endpoint
		httpSecurity.authorizeHttpRequests(http -> http.requestMatchers("/**").permitAll());

		return httpSecurity.build();
	}
}
