<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="style/smbmsstyle.css" />


</head>
<body>
	<div id="box">
		<div id="content">
			<div id="title">
				<p>超市订单管理系统-订单管理界面</p>
			</div>
			<div id="b1">
				产品名称:<input type="text" id="productName" />
			</div>
			<div id="b2">
				供应商名:<select id="proName">
				</select>
			</div>
			<div id="b3">
				<input type="button" id="btn" onclick="check()" />
			</div>
			<div id="up1">
				<input type="button" id="btn1" onclick="insert()" value="添加订单" />
			</div>
			<div id="b4">
				<table class="t1" id="table">
				</table>
			</div>
			<div id="pagediv2">
				<a href="#" onclick="jump(this)">首页</a> <a href="#"
					onclick="jump(this)">上一页</a> <a href="#" onclick="jump(this)">下一页</a>
				<a href="#" onclick="jump(this)">末 页</a> <input type="text"
					class="textnum" id="textnum" /> <a href="#"
					onclick="appointpage()">跳转到</a>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
	<script type="text/javascript">
		var totalPage = 1;
		var currentPage = 1;
		var productName;
		var id;
		var pageSize;
		var pageNum;
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
		function check() {
			id = $("select").val();
			productName = $("#productName").val();
			getpage(productName, id);
			getData(0, 3);

		}
		function getpage(productName, id) {
			$.ajax({
				url : "GetBillPageServlet?productName=" + productName + "&id="
						+ id,
				type : "get",
				success : function(str) {
					totalPage = parseInt(str);
				}
			})
		}
		function jump(text) {
			//alert(text.innerHTML);
			var btn = text.innerHTML;
			if (btn == "首页") {
				currentPage = 0;
			} else if (btn == "上一页") {
				if (currentPage > 1) {
					currentPage=currentPage-3;
				} else {
					currentPage = 0;
				}
			} else if (btn == "下一页") {
				if (currentPage < totalPage) {
					currentPage=currentPage+3;
				} else {
					currentPage = totalPage;
				}
			} else if (btn == "末    页") {
				currentPage = totalPage/3;
			}
			getData(currentPage, 3);
		}

		function appointpage() {
			var textnum = $(".textnum")[0];
			var num = textnum.value;

			if (num<0 || num>totalPage) {
				currentPage = 1;
			} else {
				currentPage = num;
			}
			getData(currentPage, 3);
		}
		function getData(pageNum, pageSize) {
			$.ajax({
				url : "GetBillAllOrLikeServlrt",
				type : "post",
				data : {
					'pageNum' : pageNum,
					'pageSize' : pageSize,
					'productName' : productName,
					'id' : id
				},
				dataType : 'json',
				success : function(ary) {
					var table = $("#table");
					table.text("");
					for (var i = 0; i < ary.length; i++) {
						var temp = ary[i];
						var temp = ary[i];
						var tr = $("<tr></tr>");
						var td1 = $("<td></td>");
						td1.text(temp["productCode"]);
						var td2 = $("<td></td>");
						td2.text(temp["productName"]);
						var td3 = $("<td></td>");
						td3.text(temp["productDesc"]);
						var td4 = $("<td></td>");
						td4.text(temp["productUnit"]);
						var td5 = $("<td></td>");
						td5.text(temp["productCount"]);
						var td6 = $("<td></td>");
						td6.text(temp["totalPrice"]);
						var td7 = $("<td></td>");
						if(temp["isPayment"]==1){
							td7.text("已支付");
						}else{
							td7.text("未支付");
						}
						var td10 = $("<td></td>");
						td10.text(temp["proName"]);
						(function(temp) {
							var td8 = $("<td></td>");
							var img2 = $("<img src='smbms/3.png'/>");
							img2.on('click', function() {
								location.href = "GetBillByIdServlet?id="
										+ temp.id;
							});
							td8.append(img2);
							var td9 = $("<td></td>");
							var img3 = $("<img src='smbms/5.png'/>");
							img3.on('click', function() {
								var selectRet = confirm("确定要删除吗?");
								if (selectRet == true) {
									$.ajax({
										url : "DeleteBillSeverlet?id="
												+ temp.id,
										type : "get",
										success : function(str) {
											var ret = parseInt(str);
											if (ret > 0) {
												alert('删除成功');
											} else {
												alert('删除失败')
											}
										}
									})
								}
							});
							td9.append(img3);
							tr.append(td1);
							tr.append(td2);
							tr.append(td3);
							tr.append(td4);
							tr.append(td5);
							tr.append(td6);
							tr.append(td7);
							tr.append(td10)
							tr.append(td8);
							tr.append(td9);
							table.append(tr);
						})(temp)

					}
				}
			})
		}
		function insert() {
			location.href = "addbill";
		}
	</script>
</body>
</html>