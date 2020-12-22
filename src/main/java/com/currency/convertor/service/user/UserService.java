package com.currency.convertor.service.user;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.update.UpdateProfileModel;

public interface UserService {

    User updateUserProfile(UpdateProfileModel model,User user);

    User getUserProfile(User user);

    User fetchByUsername(String name);
}
