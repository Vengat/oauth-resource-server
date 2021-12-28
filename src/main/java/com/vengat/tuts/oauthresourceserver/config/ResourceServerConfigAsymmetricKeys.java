package com.vengat.tuts.oauthresourceserver.config;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Because the resource server now takes the public key from the /oauth/token_key endpoint of the authorization server, 
 * you don’t need to configure it in the resource server configuration class. The configuration class of the resource server can remain empty, the code inside the ResourceServerConfigAsymmetricKeys config class can be safely removed:


 * @author vengatramanan
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigAsymmetricKeys extends WebSecurityConfigurerAdapter	{
	
	@Value("{publicKey}")
	private String publicKey;
	
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore());
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		var converter = new JwtAccessTokenConverter();
		converter.setVerifierKey(publicKey);
		return converter;
	}
	
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
				.oauth2ResourceServer(
						c -> c.jwt(
								j -> j.decoder(jwtDecoder())
								));
	}
	
	
	@Bean
	public JwtDecoder jwtDecoder() {
		
		KeyFactory keyFactory = null;
		try {
			 keyFactory = KeyFactory.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		var key = Base64.getDecoder().decode(publicKey);
		var x509 = new X509EncodedKeySpec(key);
		RSAPublicKey rsaKey = null;
		try {
			rsaKey = (RSAPublicKey)	keyFactory.generatePublic(x509);
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return NimbusJwtDecoder.withPublicKey(rsaKey).build();
	}

}
