var prefix = "/game/gameUser"
$(function() {
	load();
});

function load() {
	$('#exampleTable').bootstrapTable({
		method : 'get', // 服务器数据的请求方式 get or post
		url : prefix + "/list", // 服务器数据的加载地址
		iconSize : 'outline',
		toolbar : '#exampleToolbar',
		striped : true, // 设置为true会有隔行变色效果
		dataType : "json", // 服务器返回的数据类型
		pagination : true, // 设置为true会在底部显示分页条
		singleSelect : false, // 设置为true将禁止多选
		pageSize : 10, // 如果设置了分页，每页数据条数
		pageNumber : 1, // 如果设置了分布，首页页码
		showColumns : false, // 是否显示内容下拉框（选择显示的列）
		sidePagination : "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
		queryParams : function(params) {
			return {
				limit: params.limit,
				offset:params.offset
	           // name:$('#searchName').val(),
	           // username:$('#searchName').val()
			};
		},
		columns : [
				{
					checkbox : true
				},
												{
					field : 'username', 
					title : '用户名' 
				},
												{
					field : 'name', 
					title : '显示名称' 
				},
												{
					field : 'userType', 
					title : '用户类型' ,
					formatter : function(value, row, index) {
						return userTypes[value];
					}
				},
												{
					field : 'userStatus', 
					title : '用户状态' ,
					formatter : function(value, row, index) {
						return userStatus[value];
					}
				},
												{
					field : 'intStatus', 
					title : '积分状态' ,
					formatter : function(value, row, index) {
						return intStatus[value];
					}
				},
												{
					field : 'inviteCode', 
					title : '邀请码' 
				},
												{
					field : 'intNum', 
					title : '积分账号' 
				},
												{
					field : 'handRate', 
					title : '手续分成费率' 
				},
												{
					field : 'proportion', 
					title : '结算分成比例' 
				},
												{
					field : 'platformId', 
					title : '平台' 
				},
												{
					field : 'centerId', 
					title : '运营中心' 
				},
												{
					field : 'memberId', 
					title : '会员中心' 
				},
												{
					field : 'agentId', 
					title : '代理机构' 
				},
												{
					field : 'parentId', 
					title : '上级' 
				},
												{
					title : '操作',
					field : 'id',
					align : 'center',
					formatter : function(value, row, index) {
						var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="编辑" onclick="edit(\''
								+ row.userId
								+ '\')"><i class="fa fa-edit"></i></a> ';
						var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="删除"  mce_href="#" onclick="remove(\''
								+ row.userId
								+ '\')"><i class="fa fa-remove"></i></a> ';
						var p = '<a class="btn btn-primary btn-sm '+ s_add_h+ '" href="#" mce_href="#" title="添加下级" onclick="add(\''
								+ row.userType + '\',\'' + row.userId
								+ '\')"><i class="fa fa-plus"></i></a> ';
						return e + d + p;
					}
				} ]
	});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add(userType,userId) {
	layer.open({
		type : 2,
		title : '增加',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '880px', '550px' ],
		content : prefix + '/add/' + userType + '/' + userId // iframe的url
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '编辑',
		maxmin : true,
		shadeClose : false, // 点击遮罩关闭层
		area : [ '880px', '550px' ],
		content : prefix + '/edit/' + id // iframe的url
	});
}
function remove(id) {
	layer.confirm('确定要删除选中的记录？', {
		btn : [ '确定', '取消' ]
	}, function() {
		$.ajax({
			url : prefix+"/remove",
			type : "post",
			data : {
				'userId' : id
			},
			success : function(r) {
				if (r.code==0) {
					layer.msg(r.msg);
					reLoad();
				}else{
					layer.msg(r.msg);
				}
			}
		});
	})
}

function resetPwd(id) {
}
function batchRemove() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 返回所有选择的行，当没有选择的记录时，返回一个空数组
	if (rows.length == 0) {
		layer.msg("请选择要删除的数据");
		return;
	}
	layer.confirm("确认要删除选中的'" + rows.length + "'条数据吗?", {
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		var ids = new Array();
		// 遍历所有选择的行数据，取每条数据对应的ID
		$.each(rows, function(i, row) {
			ids[i] = row['userId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {

	});
}