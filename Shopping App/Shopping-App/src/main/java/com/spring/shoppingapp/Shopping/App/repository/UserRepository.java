package com.spring.shoppingapp.Shopping.App.repository;

import com.spring.shoppingapp.Shopping.App.entity.User;
import jakarta.annotation.PreDestroy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Procedure(value = "sp_user_login")
    public int userLoginStatus(String user_email,String user_password);

    @Procedure(value = "sp_user_details")
    public User userDetails(String mail);
    public User findByEmail(String email);

//    @Query(value = "CALL sp_user_update(:fullName,:pass,:phoneNo,:birth_date,:wallet,:mail)",nativeQuery = true)
//    public void userUpdate(@Param("fullName") String fullName,@Param("pass") String pass,
//                           @Param("phoneNo") String phoneNo,@Param("birth_date") Date birth_date,
//                           @Param("wallet") double wallet,@Param("mail") String mail);

    @Procedure(value = "sp_user_update")
    public void userUpdate(String fullName,String pass,
                           String phoneNo,Date birth_date,
                           double wallet,int id);

    @Procedure(value="sp_user_create")
    public void userCreate(String name,String mail,String pass,String phone,Date dob,double balance);

    @Procedure(value = "sp_remove_cart_item")
    public void userCartItemRemove(int id);

    @Procedure(value = "prod_user_add_item_to_cart")
    public void userAddItemToCart(String mail,int prodId,int quant);
}
