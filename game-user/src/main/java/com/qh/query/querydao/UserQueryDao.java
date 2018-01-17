package com.qh.query.querydao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.qh.system.domain.UserDO;

@Mapper
public interface UserQueryDao {
	UserDO get(Integer userId);
	
	UserDO getByUserName(String username);
	
	List<UserDO> list(Map<String,Object> map);
	
	List<UserDO> listByUserType(@Param("userType")Integer userType);
	
	int count(Map<String,Object> map);
	
	Integer[] listAllDept();

}
