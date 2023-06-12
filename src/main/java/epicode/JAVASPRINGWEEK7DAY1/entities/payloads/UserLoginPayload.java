package epicode.JAVASPRINGWEEK7DAY1.entities.payloads;

import lombok.Getter;

@Getter
public class UserLoginPayload {
	String email;
	String password;
}