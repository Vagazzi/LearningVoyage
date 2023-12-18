package by.learningvoyage.service;


import by.learningvoyage.model.Roles;
import by.learningvoyage.model.User;
import by.learningvoyage.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import by.learningvoyage.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private RolesRepository rolesRepository;





    public User getById(long id) {
        return userRepository.findById(id).get();
    }



    public Blob castToBlobs(MultipartFile object) throws SQLException, IOException {

        byte[] photoBytes = object.getBytes();
        return new javax.sql.rowset.serial.SerialBlob(photoBytes);
    }



    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByLogin(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        userRepository.save(user);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
