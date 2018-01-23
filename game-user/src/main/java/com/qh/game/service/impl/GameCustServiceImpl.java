package com.qh.game.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qh.game.dao.GameCustDao;
import com.qh.game.domain.GameCustDO;
import com.qh.game.service.GameCustService;



@Service
public class GameCustServiceImpl implements GameCustService {
	@Autowired
	private GameCustDao gameCustDao;
	
	@Override
	public GameCustDO get(Integer userId){
		return gameCustDao.get(userId);
	}
	
	@Override
	public List<GameCustDO> list(Map<String, Object> map){
		return gameCustDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gameCustDao.count(map);
	}
	
	@Override
	public int save(GameCustDO gameCust){
		return gameCustDao.save(gameCust);
	}
	
	@Override
	public int update(GameCustDO gameCust){
		return gameCustDao.update(gameCust);
	}
	
	@Override
	public int remove(Integer userId){
		return gameCustDao.remove(userId);
	}
	
	@Override
	public int batchRemove(Integer[] userIds){
		return gameCustDao.batchRemove(userIds);
	}
	
}
