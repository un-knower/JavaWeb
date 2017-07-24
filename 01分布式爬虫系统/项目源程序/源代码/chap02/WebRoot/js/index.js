layui.use(
		[ 'form', 'layedit', 'laydate' ], function()
		{
			var form = layui.form();
			var layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
			var $ = layui.jquery;
			var i = 1, j = 1;
			$('#add').click(
					function()
					{
						$('#table').append(
								"  <tr id=" + i + ">"
								+ " <td class='layui-input-block' colspan='2'>"
								+ "<input type='text'  name='accept" + i
								+ "  id=='accept" + i
								+ " lay-verify='required' "
								+ "class='layui-input' placeholder='第"
								+ (i + 1) + "个过滤条件'>" + " </td>" + " </tr>");
						++i;
					});

			$('#del').click(function()
					{
				i--;
				$('#' + i).remove();
				if (i <= 0)
				{
					i = 1;
				}
					});

			$('#add2').click(
					function()
					{
						$('#table2').append(
								"  <tr id=" + (j * 100) + ">"
								+ " <td class='layui-input-block' colspan='2'>"
								+ "<input type='text'  name='filtrator"
								+ (j * 100) + "  id=='filtrator" + (j * 100)
								+ " lay-verify='required' "
								+ "class='layui-input' placeholder='第"
								+ (j + 1) + "个'>" + " </td>" + " </tr>");
						++j;
					});

			$('#del2').click(function()
					{
				j--;
				$('#' + (j * 100)).remove();
				if (j <= 0)
				{
					j = 1;
				}
					});


			$('#subnow').on('click', function()
					{
				var url = document.getElementById("url").value;
				var filtrator = document.getElementById("filtrator0").value;
				var accept = []; 
				var  refuse = []; 
				if (url == "")
				{
					layer.msg('URL不能为空',
							{
						icon : 2
							});
					return false;
				} else if (filtrator == "")
				{
					layer.msg('过滤器不能为空',
							{
						icon : 2
							});
					return false;
				} else
				{
//					var index = layer.load(0,
//							{
//									shade : false
//							}); // 0代表加载的风格，支持0-2
//					for(var a=0;a<i;a++)
//					{
//						accept[a]=document.getElementsById("accept"+a).value;
//					layer.alert(accept[a]);
//					}
//					for(var b=0;b<i;b++)
//					{
//						refuse[b]=document.getElementsById("filtrator"+(b*100)).value;
//					}
////					layer.alert(refuse+accept);
//					$.ajax(
//							{
//								type : "POST",
//								url : "runnews",
//								data:{"url":url,"accept":accept,"refuse":refuse},
//								traditional :true, 
//								async : false,
//								contentType : "application/json; charset=utf-8",
//								scriptCharset : 'UTF-8',
//								error : function(XMLHttpRequest, textStatus, errorThrown)
//								{
//										layer.alert("请求失败请检查连接是否正确");
//								},
//								success : function()
//								{
//								}
//							});
//					return false;
				}
		});
			$('#WatchData').on('click', function()
					{
				layer.open(
						{
							moveOut : true,// 允许拖到窗口外面
							anim : 4,// 出场动画
							title : '已爬数据',
							maxmin : true,
							id : 0,// 弹窗唯一
							type : 2,// 弹窗类型
							skin : 'layui-layer-lan',
							content : 'FindData',
							shade : 0,
							area :
								[ '1000px', '550px' ]
						});
				return false;
					});
		});
