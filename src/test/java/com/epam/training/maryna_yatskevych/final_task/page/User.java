package com.epam.training.maryna_yatskevych.final_task.page;

public class User {
    private final String username;
    private final String password;
    public static User standardUser() {
        return new User.Builder().withUsername("standard_user").build();
    }

    public static User lockedOutUser() {
        return new User.Builder().withUsername("locked_out_user").build();
    }

    public static User problemUser() {
        return new User.Builder().withUsername("problem_user").build();
    }

    public static User performance_glitch_user() {
        return new User.Builder().withUsername("performance_glitch_user").build();
    }

    public static User error_user() {
        return new User.Builder().withUsername("error_user").build();
    }
    public static User visual_user() {
        return new User.Builder().withUsername("visual_user").build();
    }

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private String username;
        private String password = "secret_sauce";

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
