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
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(formId,dialogId,url){
		$('#'+formId).form('clear');
		$('#'+dialogId).dialog({
			closed: false,
			modal:true,
            title: "添加用户",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: addStartUp(formId,dialogId,url)
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#'+dialogId).dialog('close');                    
                }
            }]
        });
	}
	
	
	function addStartUp(formId,dialogId,url){
		return function (){
			add(formId,dialogId,url);
		}
	}
	
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(formId,dialogId,tableId,dataUrl,saveUrl){
		$('#'+formId).form('clear');
		var item = $('#'+tableId).datagrid('getSelected');
		//alert(item.productid);return;
		$.ajax({
			url:dataUrl,
			data:{id:item.id},
			success:function(data){
				if(!data){
					$('#'+dialogId).dialog('close');	
				}
				else{
					//绑定值
					$('#'+formId).form('load', data)
				}
			}	
		});
		
		$('#'+dialogId).dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: editStartUp(formId,dialogId,saveUrl)
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#'+dialogId).dialog('close');                    
                }
            }]
        });
	}
	
	
	function editStartUp(formId,dialogId,saveUrl){
		return function (){
			edit(formId,dialogId,saveUrl);
		}
	}
	
	
	/**
	* Name 添加记录
	*/
	function add(formId,dialogId,url){
		$('#'+formId).form('submit', {
			url:url,
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				return isValid;
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if(data.success){
					$.messager.alert('信息提示','提交成功！','info');
					$('#'+dialogId).dialog('close');
					location.reload();
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(formId,dialogId,url){
		$('#'+formId).form('submit', {
			url:saveUrl,
			onSubmit : function(param) {
				var isValid = $(this).form('validate');
				return isValid;
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if(data.success){
					$.messager.alert('信息提示','提交成功！','info');
					$('#'+dialogId).dialog('close');
					location.reload();
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(tableId,url){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var items = $('#'+tableId).datagrid('getSelections');
				var ids = [];
				$(items).each(function(){
					ids.push(this.id);	
				});
				//alert(ids);return;
				$.ajax({
					url:url,
					data:{id:ids.join(",")},
					success:function(data){
						if(data.success){
							$.messager.alert('信息提示','删除成功！','info');	
							location.reload();
						}
						else
						{
							$.messager.alert('信息提示','删除失败！','info');		
						}
					}	
				});
			}	
		});

	}

    function formatImg(val,row){
	    var url;
	    if(row.poster){
            url = row.poster;
        }else {
            url=row.bposter;
        }
	    if(!url){
            url = "/1.png"
        }
	   return "<img width='20px' height='20px' src="+url+" onclick='showImg(\""+url+"\")'/>"
    }

    function showImg(url){
        $("#bigImg").attr("src",url);
        $("#img-dialog").dialog('open');
    }




    function filechange(event,imgId) {
        var files = event.target.files, file;
        if (files && files.length > 0) {
            // 获取目前上传的文件
            file = files[0];// 文件大小校验的动作
            if (file.size > 1024 * 1024 ) {
                alert('图片大小不能超过 1MB!');
                return false;
            }
            // 获取 window 的 URL 工具
            var URL = window.URL || window.webkitURL;
            // 通过 file 生成目标 url
            var imgURL = URL.createObjectURL(file);
            //用attr将img的src属性改成获得的url
            $("#"+imgId).attr("src", imgURL);
            // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
            // URL.revokeObjectURL(imgURL);
        }
    }

    function formatType(value) {
		if(value=="song"){
           return "歌曲";
		}else if(value=="diss"){
           return "歌单";
		}else if (value=="mv"){
			return "mv";
		}else if(value=="column"){
            return "栏目";
		}else if(value="singer"){
			return "歌手";
		}
        return "";
    }

    function formatStatus(value,data,index){
        if(value==0){
            return "<span style='color:mediumblue'>启用</span>";
        }
        return "<span style='color:red'>禁用</span>";
    }

    function formatSubmit(value,data,index){

        if(data.prod_vid==data.test_vid){
            return "<span style='color:mediumblue'><img style='vertical-align: middle' src='"+ctxPath+"static/img/submit.png'/>已提交</span>";
        }
       var  vid = data.link_vid? data.link_vid:data.vid;
        return "<a style='color:red;text-decoration: underline;' href='javascript:void(0);' onclick='submitPro(\""+data.rid+"\",\""+vid+"\")'><img style='vertical-align: middle' src='"+ctxPath+"static/img/unsubmit.png'/>提交</a>";
    }

    function formatLinkSubmit(value,data,index){

        if(data.link_test_vid==data.link_prod_vid){
            return "<span style='color:mediumblue'><img style='vertical-align: middle' src='"+ctxPath+"static/img/submit.png'/>已提交</span>";
        }
        var  vid = data.link_vid? data.link_vid:data.vid;
        return "<a style='color:red;text-decoration: underline;' href='javascript:void(0);' onclick='submitPro(\""+data.rid+"\",\""+vid+"\")'><img style='vertical-align: middle' src='"+ctxPath+"static/img/unsubmit.png'/>提交</a>";
    }

    /**
     * 全局下线
     */
    function globalDeline(tableId) {
        var selections = $(tableId).datagrid("getChecked");
        if(selections.length==0){
            $.messager.alert("消息提示","请勾选要下线的行");
            return false;
        }
        var rids =[];
        for(var i=0;i<selections.length;i++){
            if(!selections[i].global_disable){
                rids.push(selections[i].rid);
            }
        }
        rids = rids.join(",");
        $.messager.confirm('确认下线','您确认要下线选中的行?',function(r){
            if (r){
                $.ajax({
                    url:"globalOnline?rids="+rids,
                    type:"post",
                    success:function(data){
                        if(data.success){
                            $("#program-table").datagrid("reload");
                            $("#cate-table").datagrid("reload");
                        }
                        $.messager.alert("消息提示",data.backmsg);
                    }
                });
            }
        })


    }

    /**
     * 批量提交到生产
     */
    function groupSubmit(tableId) {
        var selections = $(tableId).datagrid("getChecked");
        if(selections.length==0){
            $.messager.alert("消息提示","请勾选要操作的行");
            return false;
        }
        var versionIds =[];
        for(var i=0;i<selections.length;i++){
            if(selections[i].prod_vid!=selections[i].test_vid){
                versionIds.push(selections[i].vid);
            }
        }
        versionIds = versionIds.join(",");
        $.messager.confirm('批量提交','您确认要将选中的行提交到生产环境?',function(r){
            ajaxLoading();
            if (r){
                $.ajax({
                    url:"submitVer?vids="+versionIds,
                    type:"post",
                    success:function(data){
                        ajaxLoadEnd();
                        if(data.success){
                            $("#program-table").datagrid("reload");
                            $("#cate-table").datagrid("reload");
                        }
                        $.messager.alert("消息提示",data.backmsg);
                    }
                });
            }else{
                ajaxLoadEnd();
            }
        })

    }
    /**
     * 批量提交Link到生产
     */
    function groupLinkSubmit(tableId) {
        var selections = $(tableId).datagrid("getChecked");
        if(selections.length==0){
            $.messager.alert("消息提示","请勾选要操作的行");
            return false;
        }
        var versionIds =[];
        for(var i=0;i<selections.length;i++){
            if(selections[i].link_test_vid!=selections[i].link_prod_vid){
                versionIds.push(selections[i].link_vid);
            }
        }
        versionIds = versionIds.join(",");
        $.messager.confirm('分类资源提交','您确认要将选中的行提交到生产环境?',function(r){
            ajaxLoading();
            if (r){
                $.ajax({
                    url:"submitChildLink?vids="+versionIds,
                    type:"post",
                    success:function(data){
                        ajaxLoadEnd();
                        if(data.success){
                            $("#program-table").datagrid("reload");
                            $("#cate-table").datagrid("reload");
                        }
                        $.messager.alert("消息提示",data.backmsg);
                    }
                });
            }else{
                ajaxLoadEnd();
            }
        })

    }

    /**
     * 全局下线
     */
    function globalOnline(tableId) {
        var selections = $(tableId).datagrid("getChecked");
        if(selections.length==0){
            $.messager.alert("消息提示","请勾选要上线的行");
            return false;
        }
        var rids =[];
        for(var i=0;i<selections.length;i++){
            if(selections[i].global_disable){
                rids.push(selections[i].rid);
            }
        }
        rids = rids.join(",");
        $.messager.confirm('确认上线','您确认要上线选中的行?',function(r){
            if (r){
                $.ajax({
                    url:"globalOnline?rids="+rids,
                    type:"post",
                    success:function(data){
                        if(data.success){
                            $("#program-table").datagrid("reload");
                            $("#cate-table").datagrid("reload");
                        }
                        $.messager.alert("消息提示",data.backmsg);
                    }
                });
            }
        })


    }

    /**
     * 提交到生产环境
     * @param versionId 版本id
     * @param mediaId 媒体id
     */
    function submitPro(mediaId,versionId){
        $.messager.confirm('确认发布','您确认将<span style="color:red;">'+versionId+'</span>版本提交到正式环境吗?',function(r){
            if (r){
                $.ajax({
                    url:"submitVer",
                    type:"post",
                    data:{
                        vids:versionId,
                        mediaId:mediaId
                    },
                    success:function(data){
                        if(data.success){
                            $("#program-table").datagrid("reload");
                            $("#cate-table").datagrid("reload");
                            $.messager.alert("消息提示",data.backmsg);
                        }else{
                            $.messager.alert("消息提示",data.backmsg);
                        }
                    }
                });
            }
        })
    }
    /**
     * 版本下线
     */
    function deline(versionId){
        $.messager.confirm('确认下线','您确认要下线选中版本么?',function(r){
            $.ajax({
                url:"online",
                type:"post",
                data:{
                    versionId:versionId
                },
                success:function(data){
                    if(data.success){
                        $.messager.alert("消息提示","操作成功");
                        $("#program-table").datagrid("reload");
                        $("#cate-table").datagrid("reload");
                    }else{
                        $.messager.alert("消息提示",data.backmsg);
                    }
                }
            });
        });
    }

    /**
     * 版本下线
     */
    function online(versionId){
        $.messager.confirm('确认下线','您确认要上线选中版本么?',function(r){
            $.ajax({
                url:"online",
                type:"post",
                data:{
                    versionId:versionId
                },
                success:function(data){
                    if(data.success){
                        $.messager.alert("消息提示","操作成功");
                        $("#program-table").datagrid("reload");
                        $("#cate-table").datagrid("reload");
                    }else{
                        $.messager.alert("消息提示",data.backmsg);
                    }
                }
            });
        });
    }

    function searchProgram(){
        var queryParams = $("#program-table").datagrid('options').queryParams;
        queryParams.name = $('#sname').val();
        queryParams.singername = $('#singername').val();
        queryParams.submit = $('#ssubmit').val();
        queryParams.globalstatus = $('#sglobal').val();
        queryParams.free = $('#sfreeFlag').val();
        $("#program-table").datagrid("reload");
    }


    /**
     * 对象深拷贝
     * @param obj
     * @returns {*}
     */
    function deepCopy(obj) {
        // 只拷贝对象
        if (typeof obj !== 'object') return;
        // 根据obj的类型判断是新建一个数组还是一个对象
        var newObj = obj instanceof Array ? [] : {};
        for (var key in obj) {
            // 遍历obj,并且判断是obj的属性才拷贝
            if (obj.hasOwnProperty(key)) {
                // 判断属性值的类型，如果是对象递归调用深拷贝
                newObj[key] = typeof obj[key] === 'object' ? deepCopy(obj[key]) : obj[key];
            }
        }
        return newObj;
    }

    /**
     * 提交歌手及歌单下面的歌资源
     * @param mediaTable
     * @param songTable
     */
    function submitResouceToProduct(mediaTable,songTable){
        var selection = $(mediaTable).datagrid("getSelections")[0];
        $.messager.confirm('确认发布111','您确认要将列表媒资发布到正式环境?',function(r){
            ajaxLoading();
            if(r){
                $.ajax({
                    url:"submitAllChild",
                    type:"post",
                    data:{
                        prid:selection.rid
                    },
                    success:function(data){
                        ajaxLoadEnd();
                        if(data.success){
                            $.messager.alert("消息提示","操作成功");
                            $(songTable).datagrid("reload");
                        }else{
                            $.messager.alert("消息提示",data.backmsg);
                        }
                    }
                });
            }else{
                ajaxLoadEnd();
            }
        });
    }

    function formatRes(value){
      if(value=="song"){
          return "歌曲";
      }else if(value=="top"){
          return "榜单";
      }else if(value=="singer"){
          return "歌手"
      }else if(value=="diss"){
          return "歌单";
      }else{
          return value;
      }
    }

    function ajaxLoading(){
        top.$("<div class=\"datagrid-mask\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo(top.$("body"));
        top.$("<div class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候。。。").appendTo(top.$("body")).css({display:"block",left:(top.$(document.body).outerWidth(true) - 190) / 2,top:(top.$(window).height() - 45) / 2+3});
    }

    function ajaxLoadEnd(){
        top.$(".datagrid-mask").remove();
        top.$(".datagrid-mask-msg").remove();
    }


