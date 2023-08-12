package com.fishing.demo.api.service;

import com.fishing.demo.api.dto.request.RegistrationRequestDto;
import com.fishing.demo.api.dto.response.RegistrationResponseDto;
import com.fishing.demo.api.domain.entity.Users;
import com.fishing.demo.api.domain.repository.UsersRepository; // Make sure to import the correct UsersRepository
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UsersRepository usersRepository;

    public RegistrationResponseDto registerUser(RegistrationRequestDto request) {
        // Perform registration logic here
        // For example, you can create a new Users entity and save it to the database
        Users newUser = Users.builder() // Use the builder method from Users
                .nickName(request.getNickname()) // Set nickname using the setter method from UsersBuilder
                .id(request.getId()) // Set id using the setter method from UsersBuilder
                .password(request.getPassword()) // Set password using the setter method from UsersBuilder
                .profileImagePath(request.getProfileImagePath()) // Set profileImagePath using the setter method from
                                                                 // UsersBuilder
                .createdDate(new Date()) // Set createdDate using the setter method from UsersBuilder or provide the
                                         // date as needed
                .build(); // Build the Users object

        usersRepository.save(newUser);

        // Return a success response
        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setMessage("Registration successful!");
        return response;
    }
}
