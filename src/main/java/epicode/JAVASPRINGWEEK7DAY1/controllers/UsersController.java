package epicode.JAVASPRINGWEEK7DAY1.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import epicode.JAVASPRINGWEEK7DAY1.entities.User;
import epicode.JAVASPRINGWEEK7DAY1.entities.payloads.UserRegistrationPayload;
import epicode.JAVASPRINGWEEK7DAY1.exceptions.NotFoundException;
import epicode.JAVASPRINGWEEK7DAY1.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersService usersService;

	@GetMapping("")
	public Page<User> getUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy) {
		return usersService.find(page, size, sortBy);
	}

	@GetMapping("/{userId}")
	public User getById(@PathVariable UUID userId) {
		return usersService.findById(userId);
	}
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable UUID userId, @RequestBody UserRegistrationPayload body) throws Exception {
		return usersService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable UUID userId) throws NotFoundException {

		usersService.findByIdAndDelete(userId);
	}
}
