package by.learningvoyage.model;

import javax.persistence.Table;

@Table(name = "roles")
public enum Roles {
    DEFAULT_USER, PRIVILEGED_USER, ADMIN
}
