package com.collins.ploutos.ploutos.service;

import com.collins.ploutos.ploutos.repository.UserRepository;
import com.collins.ploutos.ploutos.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {


    @Autowired
    ///  instance of the user repository
    private UserRepository userRepository;

    @Autowired
    /// instance of profile repository
    private ProfileRepository profileRepository;


}
