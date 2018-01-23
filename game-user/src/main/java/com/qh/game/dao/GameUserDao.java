package com.qh.game.dao;

import com.qh.game.domain.GameUserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * @author chyzh
 * @email 3048427407@qq.com
 * @date 2018-01-18 16:58:32
 */
@Mapper
public interface GameUserDao {

	GameUserDO get(Integer userId);
	
	List<GameUserDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GameUserDO gameUser);
	
	int update(GameUserDO gameUser);
	
	int remove(Integer user_id);
	
	int batchRemove(Integer[] userIds);
}
