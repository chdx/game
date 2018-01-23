package com.qh.game.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qh.game.domain.GameCustDO;
import com.qh.game.service.GameCustService;
import com.qh.common.utils.PageUtils;
import com.qh.common.utils.Query;
import com.qh.common.utils.R;

/**
 * 客户
 * 
 * @author chyzh
 * @email 3048427407@qq.com
 * @date 2018-01-18 16:58:32
 */
 
@Controller
@RequestMapping("/game/gameCust")
public class GameCustController {
	@Autowired
	private GameCustService gameCustService;
	
	@GetMapping()
	@RequiresPermissions("game:gameCust:gameCust")
	String GameCust(){
	    return "game/gameCust/gameCust";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("game:gameCust:gameCust")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GameCustDO> gameCustList = gameCustService.list(query);
		int total = gameCustService.count(query);
		PageUtils pageUtils = new PageUtils(gameCustList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("game:gameCust:add")
	String add(){
	    return "game/gameCust/add";
	}

	@GetMapping("/edit/{userId}")
	@RequiresPermissions("game:gameCust:edit")
	String edit(@PathVariable("userId") Integer userId,Model model){
		GameCustDO gameCust = gameCustService.get(userId);
		model.addAttribute("gameCust", gameCust);
	    return "game/gameCust/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("game:gameCust:add")
	public R save( GameCustDO gameCust){
		if(gameCustService.save(gameCust)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("game:gameCust:edit")
	public R update( GameCustDO gameCust){
		gameCustService.update(gameCust);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("game:gameCust:remove")
	public R remove( Integer userId){
		if(gameCustService.remove(userId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("game:gameCust:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] userIds){
		gameCustService.batchRemove(userIds);
		return R.ok();
	}
	
}
