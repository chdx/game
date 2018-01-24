package com.qh.game.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qh.api.constenum.GameType;
import com.qh.api.constenum.UserType;
import com.qh.api.utils.Md5Util;
import com.qh.api.utils.ParamUtil;
import com.qh.common.config.CfgKeyConst;
import com.qh.common.utils.R;
import com.qh.common.utils.ShiroUtils;
import com.qh.game.GameConstants;
import com.qh.game.dao.GameUserDao;
import com.qh.game.domain.GameUserDO;
import com.qh.game.service.GameUserService;
import com.qh.redis.RedisConstants;
import com.qh.redis.service.RedisUtil;
import com.qh.system.dao.UserDao;
import com.qh.system.domain.UserDO;
import com.qh.system.service.UserService;

@Service
public class GameUserServiceImpl implements GameUserService {
    @Autowired
    private GameUserDao gameUserDao;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDao userMapper;
    
    @Override
    public GameUserDO get(Integer userId) {
        return gameUserDao.get(userId);
    }

    @Override
    public List<GameUserDO> list(Map<String, Object> map) {
        return gameUserDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return gameUserDao.count(map);
    }

    @Override
    public R save(Integer parentUserType,GameUserDO gameUser) {
        Integer parentId = gameUser.getParentId();
        if (UserType.platform.id() == gameUser.getUserType()) {
            setPlatformRate(gameUser);
            gameUser.setParentId(GameConstants.default_game_user_id);
        }else {
            //设置从属关系
            if(parentId == null || parentUserType == null) {
                return R.error("上级信息为空");
            }
        }
        String username = gameUser.getUsername();
        UserDO user = userService.getByUsername(username);
        if(user != null) {
            return R.error(username + "，该用户已经存在");
        }
        if((user = createUserForGameUser(gameUser))  != null){
            gameUser.setUserId(user.getUserId());
        }else {
            return R.error("用户信息保存失败");
        }
        gameUser.setPlatformId(GameConstants.default_game_user_id);
        gameUser.setCenterId(GameConstants.default_game_user_id);
        gameUser.setMemberId(GameConstants.default_game_user_id);
        gameUser.setAgentId(GameConstants.default_game_user_id);
        //设置从属关系
        if (UserType.platform.id() != gameUser.getUserType()) {
            GameUserDO parent = get(parentUserType, parentId);
            if(parent == null) {
                return R.error("查询父级信息为空");
            }
            String msg = checkRates(parent,gameUser);
            if(ParamUtil.isNotEmpty(msg)) {
                return R.error(msg);
            }
            if(UserType.platform.id() == parentUserType) {
                gameUser.setPlatformId(parent.getUserId());
            }else if(UserType.center.id() == parentUserType) {
                gameUser.setPlatformId(parent.getPlatformId());
                gameUser.setCenterId(parent.getUserId());
            }else if(UserType.member.id() == parentUserType) {
                gameUser.setPlatformId(parent.getPlatformId());
                gameUser.setCenterId(parent.getCenterId());
                gameUser.setMemberId(parent.getUserId());
            }else if(UserType.agent.id() == parentUserType) {
                gameUser.setPlatformId(parent.getPlatformId());
                gameUser.setCenterId(parent.getCenterId());
                gameUser.setMemberId(parent.getAgentId());
                gameUser.setAgentId(parent.getUserId());
            }
        }
        //设置邀请码、积分账号
        int gCode = ParamUtil.generateCode4();
        String idNo = String.format("%04d", RedisUtil.getIdNo().intValue());
        gameUser.setInviteCode(gCode + idNo);
        idNo = String.valueOf(RedisUtil.getIdNo().intValue());
        if(idNo.length() == 1) {
            gameUser.setIntNum(idNo+ParamUtil.generateCode8());
        }else if(idNo.length() == 2) {
            gameUser.setIntNum(idNo+ParamUtil.generateCode7());
        }else if(idNo.length() == 3) {
            gameUser.setIntNum(idNo+ParamUtil.generateCode6());
        }else if(idNo.length() == 4) {
            gameUser.setIntNum(idNo+ParamUtil.generateCode5());
        }else {
            gameUser.setIntNum(idNo+ParamUtil.generateCode4());
        }
        int count = gameUserDao.save(gameUser);
        if (count == 0) {
            return R.error("保存失败");
        }
        this.putToCache(gameUser);
        return R.ok("操作成功");
    }

    /***
     * 
     * @Description 交易手续费比例已经分成比例
     * @param parent
     * @param gameUser
     * @return
     */
    private String checkRates(GameUserDO parent, GameUserDO gameUser) {
        String msg = null;
        if(gameUser.getHandRate() == null) {
            gameUser.setHandRate(new HashMap<>());
        }
        if(gameUser.getProportion() == null) {
            gameUser.setProportion(new HashMap<>());
        }
        if(parent.getHandRate() == null) {
            parent.setHandRate(new HashMap<>());
        }
        if(parent.getProportion() == null) {
            parent.setProportion(new HashMap<>());
        }
        BigDecimal parentValue = null;
        BigDecimal currValue = null;
        String gameTypeDesc = "";
        for(String gameType : GameType.desc().keySet()) {
            gameTypeDesc = GameType.desc().get(gameType);
            parentValue = parent.getHandRate().get(gameType);
            if(parentValue == null) {
                msg = "上级"+ gameTypeDesc+"手续费率为空";
                break;
            }
            currValue = gameUser.getHandRate().get(gameType);
            if(currValue == null) {
                msg = "当前用户"+ gameTypeDesc+"手续费率为空";
                break;
            }
            if(currValue.compareTo(parentValue) > 0 || currValue.compareTo(new BigDecimal(1)) > 0) {
                msg = "当前用户"+ gameTypeDesc+"手续费率超出范围";
                break;
            }
            
            parentValue = parent.getProportion().get(gameType);
            if(parentValue == null) {
                msg = "上级"+ gameTypeDesc+"分成比例为空";
                break;
            }
            currValue = gameUser.getProportion().get(gameType);
            if(currValue == null) {
                msg = "当前用户"+ gameTypeDesc+"分成比例为空";
                break;
            }
            if(currValue.compareTo(parentValue) > 0 || currValue.compareTo(new BigDecimal(1)) > 0) {
                msg = "当前用户"+ gameTypeDesc+"分成比例超出范围";
                break;
            }
        }
        return msg;
    }

    /**
     * @Description 设置平台费率
     * @param gameUser
     */
    private void setPlatformRate(GameUserDO gameUser) {
        Map<String, String> gameTypes = GameType.desc();
        Map<String, BigDecimal> rateMap = new HashMap<>();
        for (String key : gameTypes.keySet()) {
            rateMap.put(key, new BigDecimal(1));
        }
        gameUser.setHandRate(rateMap);
        gameUser.setProportion(rateMap);
    }

    @Override
    public R update(GameUserDO gameUser) {
        Integer parentId = gameUser.getParentId();
        if (UserType.platform.id() == gameUser.getUserType()) {
            setPlatformRate(gameUser);
            gameUser.setParentId(GameConstants.default_game_user_id);
        }else {
            //设置从属关系
            if(parentId == null) {
                return R.error("上级信息为空");
            }
        }
        //设置从属关系
        if (UserType.platform.id() != gameUser.getUserType()) {
            GameUserDO parent = this.getParent(gameUser.getUserType(), parentId);
            if(parent == null) {
                return R.error("查询父级信息为空");
            }
            String msg = checkRates(parent,gameUser);
            if(ParamUtil.isNotEmpty(msg)) {
                return R.error(msg);
            }
        }
        int count = gameUserDao.update(gameUser);
        if (count == 0) {
            return R.error("保存失败");
        }
        this.putToCache(gameUser);
        return R.ok("操作成功");
    }

    @Override
    public int remove(Integer userId) {
        return gameUserDao.remove(userId);
    }

    @Override
    public int batchRemove(Integer[] userIds) {
        return gameUserDao.batchRemove(userIds);
    }

    @Override
    public GameUserDO get(Integer userType, Integer userId) {
        UserType utEnum = UserType.getUserType(userType);
        if (utEnum == null) {
            return null;
        }
        GameUserDO gameUser = getFromCache(userId, utEnum);
        if (gameUser == null) {
            gameUser = this.get(userId);
        }else {
            return gameUser;
        }

        if (gameUser != null) {
            putToCache(gameUser);
        }
        return gameUser;
    }

    private void putToCache(GameUserDO gameUser) {
        UserType utEnum = UserType.getUserType(gameUser.getUserType());
        switch (utEnum) {
            case platform:
                RedisUtil.setHashValue(RedisConstants.cache_platform_, gameUser.getUserId(), gameUser);
                break;
            case center:
                RedisUtil.setHashValue(RedisConstants.cache_center_, gameUser.getUserId(), gameUser);
                break;
            case member:
                RedisUtil.setHashValue(RedisConstants.cache_member_, gameUser.getUserId(), gameUser);
                break;
            case agent:
                RedisUtil.setHashValue(RedisConstants.cache_agent_, gameUser.getUserId(), gameUser);
                break;
            default:
                break;
         }
    }

    private GameUserDO getFromCache(Integer userId, UserType utEnum) {
        GameUserDO gameUser = null;
        switch (utEnum) {
            case platform:
                gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_platform_, userId);
                break;
            case center:
                gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_center_, userId);
                break;
            case member:
                gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_member_, userId);
                break;
            case agent:
                gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_agent_, userId);
                break;
            default:
                break;
        }
        if (gameUser == null) {
            gameUser = this.get(userId);
        }else {
            return gameUser;
        }
        if (gameUser != null) {
            putToCache(gameUser);
        }
        return gameUser;
    }

    
    private UserDO createUserForGameUser(GameUserDO gameUser){
        UserDO user = new UserDO();
        user.setUserIdCreate(ShiroUtils.getUserId());
        user.setUsername(gameUser.getUsername());
        user.setPassword(Md5Util.MD5(RedisUtil.getSysConfigValue(CfgKeyConst.pass_default_gameUser)));
        user.setName(gameUser.getName());
        String state  = RedisUtil.getSysConfigValue(CfgKeyConst.state_default_gameUser);
        if(ParamUtil.isNotEmpty(state)){
            user.setStatus(Integer.parseInt(state));
        }
        user.setUserType(gameUser.getUserType());
        if(userMapper.save(user) > 0){
            return user;
        }else{
            return null;
        }
    }

    @Override
    public GameUserDO getParent(Integer childUserType, Integer userId) {
        UserType utEnum = UserType.getUserType(childUserType);
        if (utEnum == null) {
            return null;
        }
        GameUserDO gameUser = null;
        switch (utEnum) {
            case platform:
                break;
            case center:
                gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_platform_, userId);
                break;
            case member:
                gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_center_, userId);
                break;
            case agent:
                gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_member_, userId);
                if(gameUser == null) {
                    gameUser = (GameUserDO) RedisUtil.getHashValue(RedisConstants.cache_agent_, userId);
                }
                break;
            default:
                break;
        }
        return gameUser;
    }
}
