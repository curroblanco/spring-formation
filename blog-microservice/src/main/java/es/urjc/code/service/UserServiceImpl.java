package es.urjc.code.service;

import es.urjc.code.entity.AppUser;
import es.urjc.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findOne(long id) {

        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public AppUser findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public long insertOne(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        return userRepository.save(appUser).getId();
    }

    @Override
    public AppUser updateOne(long id, AppUser appUser) {
        Optional<AppUser> userById = userRepository.findById(id);

        return userById.map( u -> {
                    u.setPassword(appUser.getPassword());
                    u.setUsername(appUser.getUsername());
                    return userRepository.save(u);
                }
        ).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteOne(long id) {

        userRepository.deleteById(id);
    }
}
