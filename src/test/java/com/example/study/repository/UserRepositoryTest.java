package com.example.study.repository;

import com.example.study.Repository.UserRepository;
import com.example.study.StudyApplicationTests;
import com.example.study.model.Entity.User;
import com.example.study.model.enumclass.UserStatus;
import jdk.vm.ci.meta.Local;
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

        // ID는 DB에서 Auto Increment로 지정하여 Not Null이지만 따로 set해주지 않아도 됨
        // ID를 set 해주지 않았음에도 불구하고 Auto Increment로 지정하였기 때문에 ID가 부여되어서 객체로 반환됨.

        String account = "Test03";
        String password = "Test03";
        //UserStatus status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();


        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        //user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);


        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);




    }
    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

        user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("-------------------주문묶음------------------");
            System.out.println("수령인 : " + orderGroup.getRevName());
            System.out.println("수령지 : " + orderGroup.getRevAddress());
            System.out.println("총금액 : " + orderGroup.getTotalPrice());
            System.out.println("총수량 : " + orderGroup.getTotalQuantity());

            System.out.println("--------------주문상세--------------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름 : " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태 : " + orderDetail.getStatus());
                System.out.println("도착 예정 일자 : " + orderDetail.getArrivalDate());


            });

        });
        Assert.assertNotNull(user);

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
