package com.thoughtworks.training.kmj.todoservice;


import com.thoughtworks.training.kmj.todoservice.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


//    @Test
//    public void shouldReturnUserWithName() {
////        Optional<User> user = userRepository.findOneByName("user1");
//
//        userRepository.save(new User(9,"user1","123"));
//        Optional<User> user = userRepository.findOneByName("user1");
//
//        assertTrue(user.isPresent());
//        assertThat(user.get().getName(),is("user1"));
//    }
}
