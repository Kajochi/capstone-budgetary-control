package de.neuefische.capstone.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class generalMongoUser {


    String id;

    String userName;

    String password;

    String mailAddress;

}
