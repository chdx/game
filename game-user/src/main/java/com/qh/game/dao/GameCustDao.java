package com.qh.game.dao;

import com.qh.game.domain.GameCustDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 客户
 * @author chyzh
 * @email 3048427407@qq.com
 * @date 2018-01-18 16:58:32
 */
@Mapper
public interface GameCustDao {

	GameCustDO get(Integer userId);
	
	List<GameCustDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GameCustDO gameCust);
	
	int update(GameCustDO gameCust);
	
	int remove(Integer user_id);
	
	int batchRemove(Integer[] userIds);
}
