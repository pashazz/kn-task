package com.example.kndemo.auth.yaml.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a user in YAML configuration
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    private long id;
    private String login;
    private String password;
    private boolean canEdit;
}
