<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>QQ音乐后台管理系统</title>
  </head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar-2">
        <div class="wu-toolbar-button">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cancel()" plain="true">取消</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-reload" onclick="reload()" plain="true">刷新</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-print" onclick="openAdd()" plain="true">打印</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-help" onclick="openEdit()" plain="true">帮助</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="remove()" plain="true">撤销</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-redo" onclick="cancel()" plain="true">重做</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-sum" onclick="reload()" plain="true">总计</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-back" onclick="reload()" plain="true">返回</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-tip" onclick="reload()" plain="true">提示</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-save" onclick="reload()" plain="true">保存</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-cut" onclick="reload()" plain="true">剪切</a>
        </div>
        <div class="wu-toolbar-search">
                <label>起始时间：</label><input class="easyui-datebox" style="width:110px">
                <label>结束时间：</label><input class="easyui-datebox" style="width:110px">
                <label>用户组：</label> 
                <select class="easyui-combobox" panelHeight="auto" style="width:110px">
                    <option value="0">选择用户组</option>
                    <option value="1">黄钻</option>
                    <option value="2">红钻</option>
                    <option value="3">蓝钻</option>
                </select>
                <label>关键词：</label><input class="wu-text" style="width:110px">
                <a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
           </div>
    </div>
    <!-- End of toolbar -->
    <table id="dg" class="easyui-datagrid" title="Basic DataGrid" style="height:100%;width:100%;"
			data-options="singleSelect:true,collapsible:true,url:'/temp/datagrid.json',method:'get',pagination:true,
				pageSize:10" toolbar="#wu-toolbar-2">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80,align:'center'">Item ID</th>
				<th data-options="field:'productid',width:100,align:'center'">Product</th>
				<th data-options="field:'listprice',width:80,align:'center'">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'center'">Unit Cost</th>
				<th data-options="field:'attr1',width:250,align:'center'">Attribute</th>
				<th data-options="field:'status',width:60,align:'center'">Status</th>
			</tr>
		</thead>
	</table>
<!--     <table id="wu-datagrid-2" class="easyui-datagrid" toolbar="#wu-toolbar-2"></table> -->
</div>
</body>
<script type="text/javascript">
	function pagerFilter(data){
		if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
			data = {
				total: data.length,
				rows: data
			}
		}
		var dg = $(this);
		var opts = dg.datagrid('options');
		var pager = dg.datagrid('getPager');
		pager.pagination({
			onSelectPage:function(pageNum, pageSize){
				opts.pageNumber = pageNum;
				opts.pageSize = pageSize;
				pager.pagination('refresh',{
					pageNumber:pageNum,
					pageSize:pageSize
				});
				dg.datagrid('loadData',data);
			}
		});
		if (!data.originalRows){
			data.originalRows = (data.rows);
		}
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = (data.originalRows.slice(start, end));
		return data;
	}
	
	$(function(){
		$('#dg').datagrid({loadFilter:pagerFilter,fitColumns:true});
	});
</script>
</html>