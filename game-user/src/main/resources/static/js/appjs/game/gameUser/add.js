$().ready(function() {
	validateRule();
	var userType = $("#userType").val();
	if(userType == 1){
		$("input[name=handRate]").val(1);
		$("input[name=handRate]").attr("readonly","readonly");
		$("input[name=proportion]").val(1);
		$("input[name=proportion]").attr("readonly","readonly");
	}
});
var tabElement;
layui.use(['element','form'], function(){
	  var $ = layui.jquery
	  ,element = layui.element;
	  tabElement = element;
	  //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
	  var layid = location.hash.replace(/^#tab-rates=/, '');
	  element.tabChange('tab-rates', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
	  
	  //监听Tab切换，以改变地址hash值
	  element.on('tab(tab-rates)', function(){
	    location.hash = 'tab-rates='+ this.getAttribute('lay-id');
	  });
});
$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	var userStatus = $("input[name=userStatus]:checked").val();
	var intStatus = $("input[name=intStatus]:checked").val();
	tabElement.tabChange('tab-rates', "tab-handRate");
	var handRates = new Object();
	var rateSaveFlag = true;
	$("input[name=handRate]").each(function(){
		var handRate = $(this).val();
		if(handRate){
			handRates[$(this).attr("id").substring(8)] = handRate;
		}else{
			rateSaveFlag = $("#signupForm").validate().element($(this));
			return;
		}
	});
	if(!rateSaveFlag){
		return false;
	}
	tabElement.tabChange('tab-rates', "tab-proportion");
	var proportions = new Object();
	$("input[name=proportion]").each(function(){
		var proportion = $(this).val();
		if(proportion){
			proportions[$(this).attr("id").substring(10)] = proportion;
		}else{
			rateSaveFlag = $("#signupForm").validate().element($(this));
			return;
		}
	});
	if(!rateSaveFlag){
		return false;
	}
	$.ajax({
		cache : true,
		type : "POST",
		url : "/game/gameUser/save",
		data : {
			userType : $("#userType").val(),
			parentUserType : $("#parentUserType").val(),
			parentId : $("#parentId").val(),
			username : $("#username").val(),
			name : $("#name").val(),
			userStatus :userStatus,
			intStatus : intStatus,
			handRate : handRates,
			proportion : proportions
		},
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			username : {
				required : true
			},
			name : {
				required : true
			},
			userStatus : {
				required : true
			},
			intStatus : {
				required : true
			},
			handRate : {
				required : true
			},
			proportion : {
				required : true
			}
		},
		messages : {
			username : {
				required : icon + "请输入" + label
			},
			name : {
				required : icon + "请输入"+label+"名称"
			},
			userStatus : {
				required : icon + "请选择"+label+"状态"
			},
			intStatus : {
				required : icon + "请选择"+label+"积分状态"
			},
			handRate : {
				required : icon + "请输入手续费率"
			},
			proportion : {
				required : icon + "请输入返佣分成"
			}
		}
	})
}