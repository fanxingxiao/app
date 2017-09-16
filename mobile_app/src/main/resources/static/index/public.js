var baseUrl = "http://m.china.com.cn/";
var basePicUrl = "http://images8.m.china.com.cn/mchina/img72/";
var host = window.location.host;
if(host!=null && host!="")baseUrl="http://"+host+"/"
function trim(str){

		var SubStr='';
		SubStr=str;
		while (SubStr.length>0) {
			if (SubStr.charAt(0)==" "){
				SubStr=SubStr.slice(1);
			}else{ 
				break;
			}
		}
		while (SubStr.length>0) {
			if (SubStr.charAt(SubStr.length-1)==" "){
				SubStr=SubStr.substr(0,SubStr.length-1);
			}else{
				break;
			}
		}
		return SubStr;
	}
function checkSum(str){
   var bln=true;
   if(str=="")
   {
     bln=false;
     return bln;
   }
   
   var stra=new String(str);
   for(var i=0;i<stra.length;i++)
   {
      var a=stra.substr(i,1);
      if(!(a=="0" || a=="1" || a=="2" || a=="3" || a=="4" || a=="5" || a=="6" || a=="7" || a=="8" || a=="9"))
      {
         bln=false;
         break;
      } 
   } 

   return bln;

}
function checkNum(num){
var str=trim(num);
if(checkSum(str)){
return str;
}

return 0;
}



function GetLocalIPAddress(){
    var obj = null;
    var rslt = "";
    try
    {
        obj = new ActiveXObject("rcbdyctl.Setting");
        rslt = obj.GetIPAddress;
        obj = null;
    }
    catch(e)
    {
        // ????
    }
   
    return rslt;
} 






// 需要一个java数据结构中的map类型，扩展js实现map
// =============================begin map 定义================================
function Map()
{
   this.elements = new Array();

   /**
	 * 获取MAP元素个数
	 */
this.size = function() {
   return this.elements.length;
}

/**
 * 判断MAP是否为空 
 */
this.isEmpty = function() {
   return (this.elements.length < 1);
}

   /**
	 * 删除MAP所有元素
	 */
this.clear = function() {
   this.elements = new Array();
}

   /**
	 * 向MAP中增加元素（key, value)
	 * 
	 * @param {Object}
	 *            _key
	 * @param {Object}
	 *            _value
	 */
   this.put = function(_key, _value) {
	  
	if(!this.containsKey(_key) && _value!=""){
	
		this.elements.push({key:_key, value:_value});
		}
   }

   /**
	 * 删除指定KEY的元素，成功返回True，失败返回False
	 * 
	 * @param {Object}
	 *            _key
	 */
this.remove = function(_key) {
   var bln = false;
   try {  
    for (i = 0; i < this.elements.length; i++) {
     if (this.elements[i].key == _key){
      this.elements.splice(i, 1);
      return true;
     }
    }
   }catch(e){
    bln = false;   
   }
   return bln;
}

/**
 * 获取指定KEY的元素值VALUE，失败返回NULL
 * 
 * @param {Object}
 *            _key
 */
this.get = function(_key) {
   try{  
   
    for (i = 0; i < this.elements.length; i++) {
     if (this.elements[i].key == _key) {
		
		 return this.elements[i].value;
		 
		
      return v;
     }
    }
   }catch(e) {
	   
    return null;  
   }
    return null;  
}

/**
 * 获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
 * 
 * @param {Object}
 *            _index
 */
this.element = function(_index) {
   if (_index < 0 || _index >= this.elements.length)
   {
    return null;
   }
   return this.elements[_index];
}

/**
 * 判断MAP中是否含有指定KEY的元素
 * 
 * @param {Object}
 *            _key
 */
this.containsKey = function(_key) {
   var bln = false;
   try {
    for (i = 0; i < this.elements.length; i++) {
     if (this.elements[i].key == _key){
      bln = true;
     }
    }
   }catch(e) {
    bln = false;   
   }
   return bln;
}
   
/**
 * 判断MAP中是否含有指定VALUE的元素
 * 
 * @param {Object}
 *            _value
 */
this.containsValue = function(_value) {
   var bln = false;
   try {
    for (i = 0; i < this.elements.length; i++) {
     if (this.elements[i].value == _value){
      bln = true;
     }
    }
   }catch(e) {
    bln = false;   
   }
   return bln;
}

/**
 * 获取MAP中所有VALUE的数组（ARRAY）
 */
this.values = function() {
   var arr = new Array();
   for (i = 0; i < this.elements.length; i++) {
    arr.push(this.elements[i].value);
   }
   return arr;
}

/**
 * 获取MAP中所有KEY的数组（ARRAY）
 */
this.keys = function() {
   var arr = new Array();
   for (i = 0; i < this.elements.length; i++) {
    arr.push(this.elements[i].key);
   }
   return arr;
} 
}




fadecontent = function(parentId ,fadeOutId, fadeInId, content) {
	$("#" + fadeOutId).fadeOut(300, function() {
		$("#" + fadeInId).fadeIn();
		if (content != "")
			$("#" + fadeInId)[0].innerHTML = content;
		var c = document.getElementById("content");
		if(c!=null){
			loadEditor("framearea");
		}
		var newHeight = $("#" + fadeInId).height();
		$("#" + parentId).animate({
			height : newHeight
		});

	});
}

function getOs(){
    var OsObject = "";
   if(navigator.userAgent.indexOf("MSIE")>0) {
        return "MSIE";
   }
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){
        return "Firefox";
   }
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) {
        return "Safari";
   } 
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){
        return "Camino";
   }
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){
        return "Gecko";
   }
  
} 

function loadFragData(id,type){
	var url = baseUrl+"manager/fragload.do";
	$.post(url, { "id": id,"type":type },loadFragDataResult);
}
function loadFragDataResult(html,state){
if(state=="success"){
document.getElementById("content").innerHTML=html
}else{
alert(state);
}
}

function changeSeq(obj,id){
	var url = baseUrl+"manager/fragadd.do";
	$.post(url, { "id": id,"type":2,"docId":obj.value,"seq":obj.selectedIndex },changeSeqResult);
}
function changeSeqResult(html,state){
eval("json="+html);
if(state=="success" && json.result=="success"){
	loadFragData(json.id,3);
}else if(state=="success" && json.result=="add_err_1"){
	alert("数据已选");
}else{
	alert("失败！！");
}
}

function delSelected(id,docId){
	var url = baseUrl+"manager/fragadd.do";
	$.post(url, { "id": id,"type":3,"docId":docId},changeSeqResult);
}

function addSelected(id,docId){
	var url = baseUrl+"manager/fragadd.do";
	$.post(url, { "id": id,"type":1,"docId":docId},changeSeqResult);
}
function pubFrag(id){
	var url = baseUrl+"/manager/fragsave.do";
	$.post(url, {"id": id},pubFragResult);
}


function pubFragResult(html,state){
eval("json="+html);
	if(state=="success" && json.result=="success"){
		alert("发布成功");
	}else{
		alert("至少有一条记录！！");
	}
}

function loadSearchDoc(relationId,fragmentId){
	var url = baseUrl+"manager/docsearch.do";
	if(relationId!=null){
			$.post(url, {"search.relationId":relationId,"search.fragmentId":fragmentId},loadSearchDocResult);
		}else{
			$.post(url, {"search.relationId":fragmentId},loadSearchDocResult);
		}
}
function loadSearchDocResult(html,state){
	document.getElementById("searchBox").innerHTML=html;
}

function toSearchPage(page){
	document.getElementById("currentPage").value=page;
	$('#pageSplitForm').ajaxSubmit(toSearchPagecResult); 
	document.getElementById("searchBox").innerHTML='<div class="contral">数据读取中</div>';
}

function toSearchPagecResult(html,state){
	document.getElementById("searchBox").innerHTML=html;
}

function loadRoleMenu(roleId){
	var url = baseUrl+"manager/roleAssist.do?type=m&roleId="+roleId;
	$.post(url, {},loadRoleMenuResult);
}
function loadRoleMenuResult(html,state){
	document.getElementById("menu").innerHTML=html;
}

function loadRoleNode(roleId,parentId){
	
	var url = baseUrl+"manager/roleAssist.do?type=n&parentId="+parentId+"&roleId="+roleId;
	$.post(url, {},loadRoleNodeResult);
}
function loadRoleNodeResult(html,state){
	document.getElementById("node").innerHTML=html;
}



function copyNodeTlp(id){
	var url = baseUrl+"manager/nodeass.do";
	$.post(url, {"id":id,"type":1},copyNodeTlpResult);
}

function copyNodeTlpResult(html,state){
	location.reload();
}

function vrImgHandle(that,width,height){
 	calImg();
	function calImg (){
		if (that.height == 0){
			setTimeout(calImg, 50);
			return;
		}
		if(width != that.width){
			var pos = Math.floor((that.width - width)/2);
			that.style.marginLeft = (-pos) + "px";
		}
		if(height != that.height){
			var pos = Math.floor((that.height - height)/2);
			that.style.marginTop = (-pos) + "px";
		}
		that.style.visibility = "visible";
	}
}
function imgErr(obj, width, height, defaultimg){
	obj.onerror = function(){};
	if (defaultimg){
		obj.src=defaultimg;
		obj.style.visibility = "visible";
		obj.style.marginLeft = "0px";
		obj.style.marginTop = "0px";
		obj.style.width = width+"px";
		obj.style.height = height+"px";
	}else{
		obj.style.display="none";
	}
}
function load3cover(){
	var srcs = $("#loadingLogo").val();
	if(srcs!=null){
		var arr = srcs.split(";");
		if(arr.length>=3){
			for(var i=0;i<3;i++){
				if(arr[i].indexOf("http")>=0){
					document.getElementById("llogo_"+i).src=arr[i];
				}
			}
		}
	}
}
 
var dcount = 0;
function uploadFile(articleId) {
	
	var tmp = document.getElementById("fileQueue").getElementsByTagName("div");
	dcount=(tmp.length-1)/4;
	$('#uploadify').uploadifyUpload();
}

function getExt(url){
	var f=url.split("/");
	var file_name=f[f.length-1]
var result =/\.[^\.]+/.exec(file_name);
return result;
}

function setViewPort(){
	var width = getUrlParam("width");
	var vp = document.getElementById("viewport");
	if(width!=null && width*1>0){
		if(vp!=null){
			var c="width="+width+"; initial-scale=1.0; maximum-scale=1.0; user-scalable=no;";
			vp.content = c;
		}
	}
}
 function getUrlParam(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
 }

function downApp(){
  var ua = navigator.userAgent;
  if(ua.match(/MicroMessenger|weibo|facebook|twitter/ig) !== null || ua.match(/QQ\/([\d\.]+)/g) !== null){
    //在应用内打开,qq,微信,facebook,twiter,微博
    window.open('http://app.china.com.cn','_blank');
  }else{ //在浏览器打开
    if(ua.match(/android|linux.*mobile/ig) !== null){//安卓
      var down_url = {version:"0.0.1",type:"android",appId:"chinaApp",language:"zh"};
      $.post("http://chapp.selcome.com/users/checkVersion",down_url,function(data){
        // console.log(data.info.downloadUrl);
        down_url = data.info.downloadUrl;
        window.location = down_url;
      });
    }else if(ua.match(/iphone|ipad|ipod|mac/ig) !== null){//ios
        window.location = "https://itunes.apple.com/us/app/zhong-guo-wang-xin-wen/id957292028?l=zh&amp;ls=1&amp;mt=8";
    }else{//其它设备
      window.location = "http://app.china.com.cn";
    }
  }
}


$(document).ready(function(){ 
  //导航条初始化，按钮添加事件
  if($("#nav").length>0 && !!window.Swiper){
    var num = /\?tag=\d+/ig.test(location.href);
    if(num){
      num = parseInt(location.href.match(/\?tag=\d+/ig)[0].replace("\?tag=",""));
    }else{
      num = 1;
    }
    $("#nav > .swiper-wrapper > .swiper-slide").eq(num-1).css({"font-weight":"bold","opacity":"1"}).addClass('lhfont');
    var nav = new Swiper('#nav',{
      initialSlide: num-1,
      slidesPerView: 'auto',
    });

    $("#nav-btn").click(function(e){
      if($("#nav-btn img").hasClass("rotateX180")){
        $("#nav-btn img").removeClass("rotateX180");
        $(".nav-tags").css("height","0px");
      }else{
        $("#nav-btn img").addClass("rotateX180");
        $(".nav-tags").css("height","3.2rem");
      }
    });
  }

  if($("#roller").length>0 && !!Swiper){
  	var roller = new Swiper('#roller',{
      autoplayDisableOnInteraction : false,  //用户操作swiper之后，不禁止autoplay
      autoplay: 3000,
      direction : 'vertical',
      loop: true,
      effect: 'flip',
      grabCursor: true,
      flip: {
        slideShadows : true,
        limitRotation : true,
      }
    });
  }

});


