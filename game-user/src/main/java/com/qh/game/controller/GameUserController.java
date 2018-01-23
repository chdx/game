package com.qh.game.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qh.api.constenum.GameType;
import com.qh.api.constenum.UserType;
import com.qh.common.utils.PageUtils;
import com.qh.common.utils.Query;
import com.qh.common.utils.R;
import com.qh.common.utils.ShiroUtils;
import com.qh.game.GameConstants;
import com.qh.game.constenum.GameIntStatus;
import com.qh.game.constenum.GameUserStatus;
import com.qh.game.domain.GameUserDO;
import com.qh.game.service.GameUserService;

/**
 * 用户
 * 
 * @author chyzh
 * @email 3048427407@qq.com
 * @date 2018-01-18 16:58:32
 */
 
@Controller
@RequestMapping("/game/gameUser")
public class GameUserController {
	@Autowired
	private GameUserService gameUserService;
	
	@GetMapping()
	@RequiresPermissions("game:gameUser:gameUser")
	String GameUser(Model model){
		model.addAttribute("userType", ShiroUtils.getUser().getUserType());
		model.addAttribute("userTypes", UserType.desc());
		model.addAttribute("userStatus", GameUserStatus.desc());
		model.addAttribute("intStatus", GameIntStatus.desc());
		model.addAttribute("gameTypes", GameType.desc());
	    return "game/gameUser/gameUser";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("game:gameUser:gameUser")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GameUserDO> gameUserList = gameUserService.list(query);
		int total = gameUserService.count(query);
		PageUtils pageUtils = new PageUtils(gameUserList, total);
		return pageUtils;
	}
	
	@GetMapping("/add/{userType}/{userId}")
	@RequiresPermissions("game:gameUser:add")
	String add(@PathVariable("userType") Integer userType,@PathVariable("userId") Integer userId,Model model){
		if(GameConstants.default_game_user_id == userId) {
		    model.addAttribute("label", UserType.desc().get(UserType.platform.id()));
		    model.addAttribute("userType", UserType.platform.id());
		    model.addAttribute("parentUserType",UserType.platform.id());
		}else {
		    GameUserDO parent = gameUserService.get(userType,userId);
		    if(parent == null) {
		        model.addAttribute("msg", "上级信息为空");
		        return GameConstants.url_error_frame;
		    }
		    model.addAttribute("parentUserType",userType);
		    model.addAttribute("parent", parent);
		    model.addAttribute("label", UserType.getChildDesc(userType));
		    model.addAttribute("userType", UserType.getChild(userType).id());
		}
		model.addAttribute("userSts", GameUserStatus.desc());
		model.addAttribute("intSts", GameIntStatus.desc());
		model.addAttribute("gameTypes", GameType.desc());
	    return "game/gameUser/add";
	}

	@GetMapping("/edit/{userId}")
	@RequiresPermissions("game:gameUser:edit")
	String edit(@PathVariable("userId") Integer userId,Model model){
		GameUserDO gameUser = gameUserService.get(userId);
		model.addAttribute("gameUser", gameUser);
	    return "game/gameUser/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("game:gameUser:add")
	public R save(@RequestParam("parentUserType") Integer parentUserType, GameUserDO gameUser){
	    Integer userType = gameUser.getUserType();
        if (userType == null) {
            return R.error("用户类型为空");
        }
	    if(UserType.platform.id() == userType && !ShiroUtils.getSubjct().isPermitted("game:gameUser:addPlatf")) {
	        return R.error("权限不足");
	    }
		return gameUserService.save(parentUserType,gameUser);
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("game:gameUser:edit")
	public R update( GameUserDO gameUser){
		return gameUserService.update(gameUser);
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("game:gameUser:remove")
	public R remove( Integer userId){
		if(gameUserService.remove(userId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("game:gameUser:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] userIds){
		gameUserService.batchRemove(userIds);
		return R.ok();
	}
	
}
