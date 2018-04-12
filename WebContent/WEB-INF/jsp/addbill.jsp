<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" type="text/css" href="style/billstyle.css"/>
		<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
		<script type="text/javascript">
		$(function() {
			init();

		})
		function init() {
			$.ajax({
				url : "GetProviderName",
				type : "get",
				success : function(ary) {
					var select = $("#proName");
					var option1 = $("<option value='0'>" + "--请选择--"
							+ "</option>");
					select.append(option1);
					for (var i = 0; i < ary.length; i++) {
						var temp = ary[i];
						var option = $("<option  value='"+temp.id+"'>"
								+ temp.proName + "</option>")
						select.append(option);
					}
				}
			})
		}
		function addbill(){
			var radio = $("input[name=isPayment]");
			var isPayment;
			for(var i=0;i<2;i++){
				if(radio.eq(i)[0].checked==true){
					isPayment = radio.eq(i).val();
				}
			} 
			$.ajax({
				url:"AddBillServlet",
				type:"post",
				data:{'billCode':$("#billCode").val(),
					'productName':$("#productName").val(),
					'productDesc':$("#productDesc").val(),
					'productUnit':$("#productUnit").val(),
					'productCount':$("#productCount").val(),
					'totalPrice':$("#totalPrice").val(),
					'isPayment':isPayment,
					'providerId':$("#proName").val()
				},
				dataType:'json',
				success:function(str){
					var ret=parseInt(str);
					if(ret>0){
						alert("添加成功");
					}else{
						alert("添加失败");
					}
				}
			})
		}
		</script>
	</head>
	<body>
		<div id="addbox">
			<div id="logo1">
				<p>订单添加页面</p>
			</div>
			<div id="ce">
			订单编码:<input type="text" id="billCode" /><br />
			产品名称:<input type="text" id="productName" /><br />
			产品分类:<input type="text" id="productDesc" /><br />
			产品单位:<input type="text" id="productUnit" /><br />
			产品数量:<input type="text" id="productCount" /><br />
			产品价格<input type="text" id="totalPrice" /><br />
			是否支付:<input type="radio" name="isPayment" value="1"/>是
				   <input type="radio" name="isPayment" value="2"/>否<br />
			供应商名称:<select id="proName">
	</select><br />
	<input type="button"  value="添加订单"  id="btnn" onclick="addbill()"/>
	</div>
	</div>
	</body>
</html>