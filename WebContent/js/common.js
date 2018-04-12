/**
 * 
 */
function createXMLHttpRequest(){
var xhr;
	try{
		//非IE浏览器下
		xhr=new XMLHttpRequest();
	}catch(exception){
		//IE浏览器下创建
		try{
			//IE6以及以后版本
			xhr=new ActiveXObject("MSXML2.XMLHTTP");
		}catch(exception){
			//IE6以前版本
			xhr=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	return xhr;
}