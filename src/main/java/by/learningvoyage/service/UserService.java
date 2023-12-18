package by.learningvoyage.service;

import by.learningvoyage.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

public interface UserService extends UserDetailsService {
     Blob castToBlobs(MultipartFile object) throws IOException, SQLException;

    boolean saveUser(User user);

}
