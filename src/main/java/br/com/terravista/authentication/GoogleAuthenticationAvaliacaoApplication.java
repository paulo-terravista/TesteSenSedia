package br.com.terravista.authentication;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@Controller
public class GoogleAuthenticationAvaliacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleAuthenticationAvaliacaoApplication.class, args);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/user")
	public String getUserData(OAuth2Authentication principal, ModelMap model) throws JsonProcessingException {
		Map<String, Object> details = (Map<String, Object>) principal.getUserAuthentication().getDetails();
		model.addAttribute("name",details.get("name"));
		model.addAttribute("email",details.get("email"));
		model.addAttribute("link",details.get("link"));
		model.addAttribute("picture",details.get("picture"));
		
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("principal", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(principal));
		
		return "result";
	}


}
