package com.qh.common.service.impl;

import com.qh.common.dao.UserBankDao;
import com.qh.common.domain.UserBankDO;
import com.qh.common.service.UserBankService;
import com.qh.api.utils.ParamUtil;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserBankServiceImpl implements UserBankService {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserBankServiceImpl.class);
	@Autowired
	private UserBankDao userBankDao;
	
	@Override
	public UserBankDO get(String username,String bankNo){
		return userBankDao.get(username,bankNo);
	}
	
	@Override
	public List<UserBankDO> list(Map<String, Object> map){
		return userBankDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userBankDao.count(map);
	}
	
	@Override
	public int save(UserBankDO userBank){
		return userBankDao.save(userBank);
	}
	
	@Override
	public int update(UserBankDO userBank){
		return userBankDao.update(userBank);
	}

	@Override
	public int save(String phone,String username) {
		logger.info("save");
		UserBankDO userBank = userBankDao.get(username, "");
		if(userBank == null){
			userBank = new UserBankDO();
			if(ParamUtil.isNotEmpty(phone)){
				userBank.setPhone(phone);
			}
			userBank.setUsername(username);
			return save(userBank);
		}else{
			if(ParamUtil.isNotEmpty(phone)){
				userBank.setPhone(phone);
			}
			userBank.setUsername(username);
			return update(userBank);
		}
	}
	
}
