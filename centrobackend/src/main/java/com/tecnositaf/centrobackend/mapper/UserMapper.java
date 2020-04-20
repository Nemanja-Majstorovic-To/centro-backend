package com.tecnositaf.centrobackend.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.*;

import com.tecnositaf.centrobackend.model.User;

@Mapper
public interface UserMapper {
	
	@Select("SELECT * FROM users")	
	ArrayList<User> getUserList();

	@Select("SELECT * FROM users WHERE id_user = #{idUser}")	
	User getUserById(Integer idUser);
	
	@Insert("INSERT into users (username,password,mail,birthday,is_female)"
			+ "VALUES(#{username},#{password},#{mail},#{birthday},#{is_female})")
	@Options(useGeneratedKeys = true, keyProperty="idUser", keyColumn="id_user")
	Integer addUser(User user);
	
	@Update("UPDATE users SET username = #{username}, password = #{password}, mail = #{mail},"
			+ "birthday = #{birthday}, is_female = #{is_female} WHERE id_user = #{idUser} ")
	Integer updateUser(User user);
	
	@Delete("DELETE from users WHERE id_user = #{idUser}")
	Integer deleteUser(Integer idUser);
		 
}
