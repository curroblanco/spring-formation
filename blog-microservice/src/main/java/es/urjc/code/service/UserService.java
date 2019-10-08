package es.urjc.code.service;

import es.urjc.code.entity.AppUser;

import java.util.List;

public interface UserService {

    List<AppUser> findAll();
    AppUser findOne(long id);
    AppUser findByUsername(String username);
    long insertOne(AppUser appUser);
    AppUser updateOne(long id, AppUser appUser);
    void deleteOne(long id);
}
