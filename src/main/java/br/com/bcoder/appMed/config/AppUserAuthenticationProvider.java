package br.com.bcoder.appMed.config;

import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppUserAuthenticationProvider implements AuthenticationProvider {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AppUserAuthenticationProvider ( UserRepository userRepository, PasswordEncoder passwordEncoder ) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }
  @Override
  public Authentication authenticate ( Authentication authentication ) throws AuthenticationException {
    String username = authentication.getName ( );
    String password = authentication.getCredentials ( ).toString ( );
    UserModel user = userRepository.findByEmail ( username );
    if (user == null) {
      throw new BadCredentialsException( "No user registered with this details!" );
    }
    if (passwordEncoder.matches ( password , user.getPassword ( ) )) {
      List <GrantedAuthority> authorities = new ArrayList<>( );
      authorities.add ( new SimpleGrantedAuthority( "user") );
      return new UsernamePasswordAuthenticationToken ( username , password , authorities );
    }
    return authentication;
  }

  @Override
  public boolean supports ( Class < ? > authentication ) {
    return ( UsernamePasswordAuthenticationToken.class.isAssignableFrom ( authentication ) );
  }
}
