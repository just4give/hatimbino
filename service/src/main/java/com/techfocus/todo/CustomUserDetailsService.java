package com.techfocus.todo;

import javax.annotation.Resource;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techfocus.todo.model.Account;
import com.techfocus.todo.repository.AccountRepository;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

	@Resource
	  AccountRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Account account = userRepository.findByUsername(username);
      if(account != null) {
      	
      return new User(account.getUsername(), account.getPassword(), true, true, true, true,
              AuthorityUtils.createAuthorityList(account.getRoles()));
      } else {
        throw new UsernameNotFoundException("could not find the user '"
                + username + "'");
      }
    }
}
