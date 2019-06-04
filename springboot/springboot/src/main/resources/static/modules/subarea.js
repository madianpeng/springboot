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
    elem: '#LAY-subarea-manage'
    ,limit: 10
    ,height: 'full-20'
    ,text: '对不起，加载出现异常！'
    ,url: '/subarea/querylist' //数据接口
    ,title: '用户表'
    ,page: true //开启分页
    ,toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
    ,totalRow: true //开启合计行
    ,cols: [[ //表头
      {width: '5%',type: 'checkbox', fixed: 'left'}
      ,{field: 'province', title: '省', width:'10%'}
      ,{field: 'city', title: '市', width: '10%'}
      ,{field: 'district', title: '区', width:'10%'}
      ,{field: 'addresskey', title: '关键字', width: '10%'}
      ,{field: 'startnum', title: '起始号', width:'7%'} 
      ,{field: 'endnum', title: '终止号', width: '7%'}
      ,{field: 'single', title: '单双号', width: '7%'}
      ,{field: 'position', title: '位置', width: '19%'}
      ,{width: '20%', align:'center', toolbar: '#table-subarea-webuser'}
    ]]
  });
  //监听工具条
  table.on('tool(LAY-subarea-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
        layer.confirm('真的删除行么', function(index){
      	  var ids=new Array()
		  ids.push(obj.data.id);
          $.ajax({
        	  type: "post",
        	  url: "/subarea/delsubarea",
        	  data:	{
        		  ids:ids
        	  },
        	  traditional: true,
        	  success:function(res) {
				layer.msg(res.msg);
			}
          });
          table.reload('LAY-subarea-manage'); //数据刷新
        });
     
    } else if(obj.event === 'edit'){
      var tr = $(obj.tr);
      //layer.alert('编辑行：<br>'+ JSON.stringify(obj.data))
      layer.open({
        type: 2
        ,title: '编辑取派员'
        ,content: '/subarea/modifypage?id='+obj.data.id
        ,maxmin: true
        ,area: ['500px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-subarea-front-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            $.ajax({
            	   type: "POST",
            	   url: "/subarea/modifysubarea",
            	   data: field,
            	   success: function(res){
            	     layer.msg(res.msg);
            	   }
            	})
            table.reload('LAY-subarea-manage'); //数据刷新
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
  table.on('toolbar(LAY-subarea-manage)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id)
    ,data = checkStatus.data; //获取选中的数据
    switch(obj.event){
      case 'add':
          layer.open({
              type: 2
              ,title: '添加分区'
              ,content: '/subareaform'
              ,maxmin: true
              ,area: ['700px', '550px']
              ,btn: ['确定', '取消']
              ,yes: function(index, layero){
                var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'LAY-subarea-front-submit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);

                //监听提交
                iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                  var field = data.field; //获取提交的字段
                  
                  //提交 Ajax 成功后，静态更新表格中的数据
                  //$.ajax({});
                  $.ajax({
                	   type: "POST",
                	   url: "/subarea/addsubarea",
                	   data: field,
                	   success: function(res){
                	     layer.msg(res.msg);
                	   }
                	})
 
                  table.reload('LAY-subarea-manage'); //数据刷新
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
              ,content: '/subarea/modifypage?id='+checkStatus.data[0].id
              ,maxmin: true
              ,area: ['600px', '450px']
              ,btn: ['确定', '取消']
              ,yes: function(index, layero){
                var iframeWindow = window['layui-layer-iframe'+ index]
                ,submitID = 'LAY-subarea-front-submit'
                ,submit = layero.find('iframe').contents().find('#'+ submitID);

                //监听提交
                iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
                  var field = data.field; //获取提交的字段
                  
                  //提交 Ajax 成功后，静态更新表格中的数据
                  //$.ajax({});
                  $.ajax({
               	   type: "POST",
               	   url: "/subarea/modifysubarea",
               	   data: field,
               	   success: function(res){
               	     layer.msg(res.msg);
               	   }
               	})
                  table.reload('LAY-subarea-manage'); //数据刷新
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
            	  url: "/subarea/delsubarea",
            	  data:	{
            		  ids:ids
            	  },
            	  traditional: true,
            	  success:function(res) {
    				layer.msg(res.msg);
    			}
              });
              table.reload('LAY-subarea-manage'); //数据刷新
          });
        }
      break;
    };
  });
  
  exports('subarea', {})
});