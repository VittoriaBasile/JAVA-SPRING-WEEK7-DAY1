package epicode.JAVASPRINGWEEK7DAY1.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epicode.JAVASPRINGWEEK7DAY1.auth.payloads.AuthenticationSuccessfullPayload;
import epicode.JAVASPRINGWEEK7DAY1.entities.User;
import epicode.JAVASPRINGWEEK7DAY1.entities.payloads.UserLoginPayload;
import epicode.JAVASPRINGWEEK7DAY1.entities.payloads.UserRegistrationPayload;
import epicode.JAVASPRINGWEEK7DAY1.exceptions.NotFoundException;
import epicode.JAVASPRINGWEEK7DAY1.exceptions.UnauthorizedException;
import epicode.JAVASPRINGWEEK7DAY1.services.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UsersService usersService;
	@Autowired
	PasswordEncoder bcrypt;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		User createdUser = usersService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
			throws NotFoundException {

		User user = usersService.findByEmail(body.getEmail());

		if (!bcrypt.matches(body.getPassword(), user.getPassword()))
			throw new UnauthorizedException("Credenziali non valide");

		String token = JWTTools.createToken(user);

		return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
	}

}