package dinhnguyen.demo.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ADMIN, 
	READER,
	WRITER;

  public String getAuthority() {
    return name();
  }

}


