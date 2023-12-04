package by.learningvoyage.service;


import by.learningvoyage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public interface UserService {
    User create(User user);

    User getById(long id);

    Blob castToBlobs(MultipartFile object) throws IOException, SQLException;
}
