package com.portfoliodeveloper.configuration;

import br.com.senioritymeter.security.gateway.SMAuthorizeRequest;
import com.portfoliodeveloper.controller.Resource;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SMAuthorizeRequestImpl implements SMAuthorizeRequest {

  @Override
  public void authorize(
      AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry
          request) {
    request.requestMatchers(Resource.SIGN_UP).permitAll();
    request.requestMatchers(Resource.SIGN_CONFIRM).permitAll();
    request.anyRequest().authenticated();
  }
}
