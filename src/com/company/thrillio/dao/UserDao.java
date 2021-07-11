package com.company.thrillio.dao;

import com.company.thrillio.DataStore;
import com.company.thrillio.entities.User;

import java.util.List;

public class UserDao {
    public List<User> getUsers() {
        return DataStore.getUsers();
    }
}
