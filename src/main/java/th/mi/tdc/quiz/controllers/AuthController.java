package th.mi.tdc.quiz.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.mi.tdc.quiz.exception.TokenRefreshException;
import th.mi.tdc.quiz.entity.RefreshToken;
import th.mi.tdc.quiz.payload.request.LogOutRequest;
import th.mi.tdc.quiz.payload.request.LoginRequest;
import th.mi.tdc.quiz.payload.request.TokenRefreshRequest;
import th.mi.tdc.quiz.payload.response.JwtResponse;
import th.mi.tdc.quiz.payload.response.MessageResponse;
import th.mi.tdc.quiz.payload.response.TokenRefreshResponse;
import th.mi.tdc.quiz.repository.NstRepository;
import th.mi.tdc.quiz.security.jwt.JwtUtils;
import th.mi.tdc.quiz.security.services.RefreshTokenService;
import th.mi.tdc.quiz.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  NstRepository userRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RefreshTokenService refreshTokenService;

//  @CrossOrigin(origins = "https://gdcc.tdc.mi.th", maxAge = 3600)
  @PostMapping({"/v1/auth/signin"})
  public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
    String jwt = this.jwtUtils.generateJwtToken(userDetails);
    RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(userDetails.getId());
    return ResponseEntity.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails
            .getUsername(), userDetails.getFirst_name(), userDetails.getLast_name(), userDetails.getGender_id(),
            userDetails.getNst_class(), userDetails.getPre_name(), userDetails.getAc_name(), userDetails.getQuiz_date(), userDetails.getDescription()));
  }

//  @CrossOrigin(origins = "https://gdcc.tdc.mi.th", maxAge = 3600)
  @PostMapping("/v1/auth/refreshtoken")
  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
    String requestRefreshToken = request.getRefreshToken();

    return refreshTokenService.findByToken(requestRefreshToken)
        .map(refreshTokenService::verifyExpiration)
        .map(RefreshToken::getUser)
        .map(user -> {
          String token = jwtUtils.generateTokenFromUsername(user.getUsername());

          RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

          return ResponseEntity.ok(new TokenRefreshResponse(token, refreshToken.getToken()));
        })
        .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
            "Refresh token is not in database!"));
  }

//  @CrossOrigin(origins = "https://gdcc.tdc.mi.th", maxAge = 3600)
  @PostMapping("/v1/auth/logout")
  public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
    refreshTokenService.deleteByUserId(logOutRequest.getUserId());
    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
  }

}
