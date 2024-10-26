
package com.example.koizaiauthdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
	AuthenticationManager authenticationManager;
	
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	
        clients.inMemory()
        .withClient("koizai-demo")
        .secret("koizai123")
            .scopes("read")
            .authorizedGrantTypes("password")
            
            .and()   
            
            .withClient("koizai-demo1")
            .secret("koizai1234")
            .scopes("read")
            .authorizedGrantTypes("authorization_code").redirectUris("http://localhost:8081/")
            
            
            .and()
            .withClient("koizai-demo2")
            .secret("koizai12345")
            .scopes("read")
            .authorizedGrantTypes("client_credentials").redirectUris("http://localhost:8082/")
            
            ;
            
    }
    
    

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub	
		endpoints.authenticationManager(authenticationManager);
	}
    
    
	
	
	
   
    
}

