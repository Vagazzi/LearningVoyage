package by.learningvoyage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "t_role")
@NoArgsConstructor
@Getter
@Setter
public class Roles implements GrantedAuthority {

    @Id
    private Long id;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    public static final String DEFAULT_USER = "DEFAULT_USER ";
    public static final String PRIVILEGED_USER = "PRIVILEGED_USER";
    public static final String ADMIN = "ADMIN";

    private String name;

    public Roles(long id, String defaultUser) {
        this.id = id;
        this.name = defaultUser;

    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
