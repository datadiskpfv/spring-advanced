package uk.co.datadisk.demo.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.demo.domain.User;
import uk.co.datadisk.demo.repositories.UserRepository;
import uk.co.datadisk.demo.services.UserService;
import uk.co.datadisk.demo.services.security.EncryptionService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
@ManagedResource("Datadisk:application=userservices")
public class UserServiceRepoImpl implements UserService {

    private UserRepository userRepository;
    private EncryptionService encryptionService;

    @Autowired
    public UserServiceRepoImpl(UserRepository userRepository, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<?> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    @Cacheable("user")
    public User saveOrUpdate(User domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        return userRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
