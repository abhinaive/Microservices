package com.microservices.restfulwebservices.user;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component   // Want Spring to manage it
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static Integer userSequence = 0;

    static {
        users.add(new User(++userSequence,"Abhinav", LocalDate.now().minusYears(30)));
        users.add(new User(++userSequence,"Komal", LocalDate.now().minusYears(20)));
        users.add(new User(++userSequence,"Eve", LocalDate.now().minusYears(40)));
        users.add(new User(++userSequence,"Adam", LocalDate.now().minusYears(50)));
    }
    public List<User> findAll(){
        return users;
    }

    public User findOne(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public void deleteById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

    public User saveUser(User user){
        user.setId(++userSequence);
        users.add(user);
        return user;
    }


}
