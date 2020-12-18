package com.currency.convertor.service.user;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.update.UpdateEmailModel;
import com.currency.convertor.domain.model.update.UpdatePasswordModel;
import com.currency.convertor.domain.model.update.UpdateUsernameModel;
import com.currency.convertor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User updatePassword(UpdatePasswordModel model, User user) {
        User updateUser = fetchByUsername(user.getUsername());
        updateUser.setPassword(encoder.encode(model.getPassword()));
        return this.userRepository.saveAndFlush(updateUser);
    }

    @Override
    public User updateUsername(UpdateUsernameModel model, User user) {
        User updateUser = fetchByUsername(user.getUsername());
        updateUser.setUsername(model.getUsername());
        return this.userRepository.saveAndFlush(updateUser);
    }

    @Override
    public User updateEmail(UpdateEmailModel model, User user) {
        User updateUser = fetchByUsername(user.getUsername());
        updateUser.setEmail(model.getEmail());
        return this.userRepository.saveAndFlush(updateUser);
    }

    @Override
    public User fetchByUsername(String name) {
        return this.userRepository.findByUsername(name).orElseThrow(() -> new IllegalArgumentException("User does not exist"));
    }

}
