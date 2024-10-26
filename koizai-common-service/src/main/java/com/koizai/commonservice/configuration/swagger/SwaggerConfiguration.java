package com.koizai.commonservice.configuration.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


/**
 * A {@link Configuration} used to enable swagger API documentation.
 * <br/>
 * This configuration wil not be used in production profile
 *
 * @author erik.malik@devstack.com.au
 */

@Configuration
@EnableSwagger2
@Profile ("!production")
public class SwaggerConfiguration
{

	/**
	 * Configure the global information for the generated API documentation
	 *
	 * @return {@link ApiInfo}
	 */
	private ApiInfo apiInfo ()
	{
		return new ApiInfoBuilder ()
				.title ("KoiZai API v1 Documentation")
				.description ("The API documentation for KoiZai system")
				.version ("V1")
				.build ();
	}


	/**
	 * Configure swagger documentation
	 *
	 * @return {@link Docket} a bean that used in generating swagger documentation
	 */
	@Bean
	public Docket api ()
	{
		return new Docket (DocumentationType.SWAGGER_2)
					.select ()
					.apis (RequestHandlerSelectors.basePackage ("com.koizai.commonservice"))
					.paths (PathSelectors.any ())
					.build ()
					.apiInfo (apiInfo ())
					.securitySchemes (Collections.singletonList (apiKey ()))
					.securityContexts (Collections.singletonList (authorizationContext ()));
	}


	/**
	 * A configuration for setting security context in swagger-ui
	 *
	 * @return {@link SecurityContext}
	 */
	private SecurityContext authorizationContext ()
	{
		return SecurityContext.builder ()
					.securityReferences (Collections.singletonList (new SecurityReference ("apiKey", new AuthorizationScope[0])))
					.build ();
	}


	/**
	 * A configuration for api key used in {@link SecurityContext}
	 *
	 * @return {@link SecurityContext}
	 */
	private ApiKey apiKey ()
	{
		return new ApiKey ("apiKey", "Authorization", "header");
	}

}
