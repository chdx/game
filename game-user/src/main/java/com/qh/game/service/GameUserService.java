package com.qh.game.service;

import com.qh.common.utils.R;
import com.qh.game.domain.GameUserDO;

import java.util.List;
import java.util.Map;

/**
 * 用户
 * 
 * @author chyzh
 * @email 3048427407@qq.com
 * @date 2018-01-18 16:58:32
 */
public interface GameUserService {
	
	GameUserDO get(Integer userId);
	
	List<GameUserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	R save(Integer parentUserType, GameUserDO gameUser);
	
	R update(GameUserDO gameUser);
	
	int remove(Integer userId);
	
	int batchRemove(Integer[] userIds);

    GameUserDO get(Integer userType, Integer userId);
    
    GameUserDO getParent(Integer childUserType, Integer userId);
}
