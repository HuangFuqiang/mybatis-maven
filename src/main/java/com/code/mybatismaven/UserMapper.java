package com.code.mybatismaven;

import java.util.List;

public interface UserMapper {

    void insertRecode(User user);

    List<User> selectAll();
}
