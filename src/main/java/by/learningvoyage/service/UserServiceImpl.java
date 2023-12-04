package by.learningvoyage.service;


import by.learningvoyage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import by.learningvoyage.repository.UserRepository;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Blob castToBlobs(MultipartFile object) throws IOException, SQLException {

        byte[] photoBytes = object.getBytes();
        return new javax.sql.rowset.serial.SerialBlob(photoBytes);
    }
}
