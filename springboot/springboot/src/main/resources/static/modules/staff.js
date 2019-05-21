/**

 @Name：取派员管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */


layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //执行一个 table 实例
  table.render({
    elem: '#LAY-staff-manage'
    ,limit: 10
    ,height: 'full-20'
    ,text: '对不起，加载出现异常！'
    ,url: '/staff/querylist' //数据接口
    ,title: '用户表'
    ,page: true //开启分页
    ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
    ,totalRow: true //开启合计行
    ,cols: [[ //表头
      {width: '5%',type: 'checkbox', fixed: 'left'}
      ,{field: 'id', title: 'ID', width:'10%'}
      ,{field: 'name', title: '姓名', width:'10%'}
      ,{field: 'telephone', title: '电话', width: '15%'}
      ,{field: 'haspda', title: '是否有PDA', width:'10%'}
      ,{field: 'deltag', title: '是否作废', width: '10%'}
      ,{field: 'station', title: '所属单位', width:'10%'} 
      ,{field: 'standard', title: '取派标准', width: '10%'}
      ,{width: '20%', align:'center', toolbar: '#table-staff-webuser'}
    ]]
  });
  //监听工具条
  table.on('tool(LAY-staff-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('真的删除行么', function(index){
      	  var ids=new Array()
		  ids.push(obj.data.id);
          $.ajax({
        	  type: "post",
        	  url: "/staff/delstaff",
        	  data:	{
        		  ids:ids
        	  },
        	  traditional: true,
        	  success:function(res) {
				layer.msg(res.msg);
			}
          });
          table.reload('LAY-staff-manage'); //数据刷新
        });
     
    } else if(obj.event === 'edit'){
      var tr = $(obj.tr);
      //layer.alert('编辑行：<br>'+ JSON.stringify(obj.data))
      layer.open({
        type: 2
        ,title: '编辑取派员'
        ,content: '/staff/modifypage?id='+obj.data.id
        ,maxmin: true
        ,area: ['500px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-staff-front-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            $.ajax({
            	   type: "POST",
            	   url: "/staff/modifystaff",
            	   data: field,
            	   success: function(res){
            	     layer.msg(res.msg);
            	   }
            	})
            table.reload('LAY-staff-manage'); //数据刷新
            layer.close(index); //关闭弹层
          });  
          
          submit.trigger('click');
        }
        ,success: function(layero, index){
          
        }
      });
    }
  });

  
  //监听头工具栏事件
  table.on('toolbar(LAY-staff-manage)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id)
    ,data = checkStatus.data; //获取选中的数据
    switch(obj.event){
      case 'add':
          layer.open({
              type: 2
              ,title: '添加取派员'
              ,content: '/staffform'
              ,maxmin: true
              ,area: ['600px', '450px']
              ,btn: ['确定', '取消']
              ,yes: function(index, layero){
                var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'LAY-staff-front-submit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);

                //监听提交
                iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                  var field = data.field; //获取提交的字段
                  
                  //提交 Ajax 成功后，静态更新表格中的数据
                  //$.ajax({});
                  $.ajax({
                	   type: "POST",
                	   url: "/staff/addstaff",
                	   data: field,
                	   success: function(res){
                	     layer.msg(res.msg);
                	   }
                	})
 
                  table.reload('LAY-staff-manage'); //数据刷新
                  layer.close(index); //关闭弹层
                });  
                
                submit.trigger('click');
              }
            }); 
      break;
      case 'update':
        if(data.length === 0){
          layer.msg('请选择一行');
        } else if(data.length > 1){
          layer.msg('只能同时编辑一个');
        } else {
          //layer.alert('编辑 [id]：'+ checkStatus.data[0].id);
          layer.open({
              type: 2
              ,title: '编辑取派员'
              ,content: '/staff/modifypage?id='+checkStatus.data[0].id
              ,maxmin: true
              ,area: ['600px', '450px']
              ,btn: ['确定', '取消']
              ,yes: function(index, layero){
                var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'LAY-staff-front-submit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);

                //监听提交
                iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                  var field = data.field; //获取提交的字段
                  
                  //提交 Ajax 成功后，静态更新表格中的数据
                  //$.ajax({});
                  $.ajax({
               	   type: "POST",
               	   url: "/staff/modifystaff",
               	   data: field,
               	   success: function(res){
               	     layer.msg(res.msg);
               	   }
               	})
                  table.reload('LAY-staff-manage'); //数据刷新
                  layer.close(index); //关闭弹层
                });  
                
                submit.trigger('click');
              }
              ,success: function(layero, index){
                
              }
            });
        }
      break;
      case 'delete':
        if(data.length === 0){
          layer.msg('请选择一行');
        } else {
          layer.confirm('确定要删除这'+data.length+'行？', function(index){
        	  var ids=new Array()
              var length=checkStatus.data.length;
              for (var i = 0; i<length; i++) {
    			ids.push(checkStatus.data[i].id);
              }
              $.ajax({
            	  type: "post",
            	  url: "/staff/delstaff",
            	  data:	{
            		  ids:ids
            	  },
            	  traditional: true,
            	  success:function(res) {
    				layer.msg(res.msg);
    			}
              });
              table.reload('LAY-staff-manage'); //数据刷新
          });
        }
      break;
    };
  });
  
  exports('staff', {})
});