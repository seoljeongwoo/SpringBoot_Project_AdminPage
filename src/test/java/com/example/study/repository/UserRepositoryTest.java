package com.example.study.repository;

import com.example.study.Repository.UserRepository;
import com.example.study.StudyApplicationTests;
import com.example.study.model.Entity.User;
import lombok.Data;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    // Dependency Injection : DI 는 SingleTon Patten
    @Autowired
    private UserRepository userRepository;

    // Test Class 이므로 annotation Test 를 달아준다.
    @Test
    public void create(){
        // DB : String sql = insert into user (%s, %s, %d) value(account, email, age);

        // JPA
        User user = new User();
        // ID는 DB에서 Auto Increment로 지정하여 Not Null이지만 따로 set해주지 않아도 됨.
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-3333-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser03");

        User newUser = userRepository.save(user);
        // ID를 set 해주지 않았음에도 불구하고 Auto Increment로 지정하였기 때문에 ID가 부여되어서 객체로 반환됨.
        System.out.println("newUser : " + newUser);
    }
    @Test
    @Transactional
    public void read(){
        Optional<User> user = userRepository.findByAccount("TestUser03");
        //Optional은 있을수도 있고 없을수도 있기때문에 있을때만 출력을 하겠다는 의미.
        user.ifPresent(selectUser ->{
            //System.out.println("user : " + selectUser);
            //System.out.println("email : " + selectUser.getEmail());
            selectUser.getOrderDetailList().stream().forEach(detail->{

                System.out.println(detail.getItem());

            });

        });
    }

    @Test
    public void update(){
        // Update 시에는 특정 Data Select가 우선이다.
        Optional<User> user = userRepository.findById(2L);
        // Data가 반드시 있어야 한다.
        user.ifPresent(selectUser ->{
            // selectUser.setId(3L); 작성시 2번의 Data를 Select 했음에도 3번의 Data가 Update 되어짐
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update Method()");

            // 해당 유저 save를 통하여 해당 유저 Save
            userRepository.save(selectUser);

            // create 의 save시 새로운 User 가 반환되었다. Update는 ?
            // JPA가 어떻게 Update인지 Create인지 구별할까?
            // JPA는 Primary Key가 존재하는지 먼저 판단한후 존재시 Update, 존재하지 않을시 Create로 판단

            //Update시 어떤 Data인지 찾고 ID를 가지고 한번더 Select후 Update실행
            // Select문 2번 수행후 Update문 1번 수행
        });
    }

    @Test
    @Transactional  // 실제 테스트 동작이 수행되지만 DB에서 마지막에 Roll Back 으로 되돌려줌
    public void delete(){
        // Update와 마찬가지로 Delete도 특정 Data Select가 우선이다.
        Optional<User> user = userRepository.findById(3L);

        // Delete는 반드시 삭제하고자하는 Id인 Data가 있어야한다.
        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser->{
            // selectUser.setId(3L); 적을시 ID = 3 이 Delete되어짐.
            // Select 후 Delete 수행
            userRepository.delete(selectUser);
        });
        
        // Update 와 마찬가지로 2번의 Select 문과 1번의 Delete문으로 Delete 수행

        // Delete가 제대로 수행되는지 판단하는 구문
        Optional<User> deleteUser = userRepository.findById(2L);

        //삭제 후에는 해당 ID인 Data가 반드시 없어야 한다.
        Assert.assertFalse(deleteUser.isPresent());
    }
}
