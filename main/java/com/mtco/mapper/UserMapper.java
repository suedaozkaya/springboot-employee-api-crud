package com.mtco.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mtco.domain.User;
import com.mtco.dto.response.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDTO userToUserDTO(User user);
	
	List<UserDTO> map(List<User> userList);
}
