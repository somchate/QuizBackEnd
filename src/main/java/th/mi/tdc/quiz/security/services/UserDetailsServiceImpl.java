package th.mi.tdc.quiz.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.mi.tdc.quiz.entity.Nst;
import th.mi.tdc.quiz.repository.NstRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  NstRepository nstRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String citizen_id) throws UsernameNotFoundException {
    Nst nst = nstRepository.findByUsername(citizen_id);
    if (nst == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return UserDetailsImpl.build(nst);
  }
}
