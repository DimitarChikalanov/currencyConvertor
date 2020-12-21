package com.currency.convertor.service.user;

import com.currency.convertor.domain.entity.User;
import com.currency.convertor.domain.model.update.UpdateEmailModel;
import com.currency.convertor.domain.model.update.UpdatePasswordModel;
import com.currency.convertor.domain.model.update.UpdateUsernameModel;

public interface UserService {

    User updatePassword(UpdatePasswordModel model, User user);

    User updateUsername(UpdateUsernameModel model, User user);

    User updateEmail(UpdateEmailModel model,User user);

    User getUserProfile(User user);

    User fetchByUsername(String name);
}
