package com.example.part2.managementwithsmartwearables.data;

import com.example.part2.managementwithsmartwearables.data.model.User;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<User> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            User fakeUser =
                    new User(
                            1,
                            "user1",
                            "Jane Doe",
                            "profile.jpg");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}