<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>秀鼻涕---欢迎你！</title>
<script src="./js/nej/src/define.js?pro=./"></script>
<style type="text/css">
	.w-form{text-align:left;}
	.w-form input{margin:0;padding:0;}
	.w-form td{padding:4px 0;border:none;}
	.w-form table{table-layout:fixed;width:100%;}
	.w-form .c0{width:90px;text-align:right;}
	.w-form .c1{width:200px;}
	.w-form .text{width:190px;border:1px solid #aaa;}
	.w-form .js-invalid{border-color:#f00;}
	.w-form .js-error{padding-left:10px;color:#f00;}
	.w-form .js-pass{padding-left:10px;color:#22B14C;}
</style>
<script type="text/javascript">
var f=function(){
    var _ = NEJ.P,
        _e = _('nej.e'),
        _v = _('nej.v'),
        _j = _('nej.j'),
        _p = _('nej.ut');

    //分配表单控件
    var _form=_p._$$WebForm._$allocate({
        form:document.forms[1],
        message:{
            pass:'ok'
        }
    });
    //提交表单事件
    var _doSubmit=function(){
        if(_form._$checkValidity()){
            var _data=_form._$data();
            _j._$request('../../api/login',{
                method:'POST',
                type:'json',
                data:'username='+_data.username+'&password='+_data.password,
                onload:function(_json){
                    switch(_json.code){
                        case -1:
                            _form._$showMsgError('password','密码错误!');
                            return;
                        case -2:
                            _form._$showMsgError('username','用户名不存在!');
                            return;
                    }
                    alert('提交成功！');
                }
            })
        }
    };
    //添加事件
    _v._$addEvent(_form._$get('btn-ok'),'click',_doSubmit);
};
define(['{lib}util/form/form.js','{lib}util/ajax/xdr.js'],f);
</script>
</head>
<body>
<form class="w-form" data-focus-mode="1">
  <p>NEJ表单填写</p>
  <table>
    <tr>
      <td class="c0">用户名：</td>
      <td class="c1">
          <input type="text"
                 name="username" 
                 class="text"
                 placeholder="用户名"
                 data-required="true"
                 data-message="请输入用户名"/>
      </td>
      <td class="c2">
        <span id="username-tip">用户名输入提示信息</span>
      </td>
    </tr>
    <tr>
      <td class="c0">密  码：</td>
      <td class="c1">
          <input type="password"
                 name="password" 
                 class="text"
                 placeholder="密码"
                 data-required="true"
                 data-message="请输入密码"/>
      </td>
      <td class="c2">
        <span id="password-tip">密码输入提示信息</span>
      </td>
    </tr>
    <tr>
      <td class="c0">&nbsp;</td>
      <td class="c1" colspan="2">
        <input type="button" value="提交" name="btn-ok"/>
      </td>
    </tr>
  </table>
</form>
<jsp:include page="foot.jsp" />
</body>
</html>