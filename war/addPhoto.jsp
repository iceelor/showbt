<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<form class="w-form" action="actions/addPhoto.jsp" method="post">
  <table>
    <tr>
      <td class="c0">a1：</td>
      <td class="c1">
          <input type="text" name="a1"/>
      </td>
      <td class="c2">
        <span id="username-tip">用户名输入提示信息</span>
      </td>
    </tr>
    <tr>
      <td class="c0">a2：</td>
      <td class="c1">
          <input type="text" name="a2" class="text" />
      </td>
      <td class="c2">
        <span id="password-tip">密码输入提示信息</span>
      </td>
    </tr>
    <tr>
      <td class="c0">&nbsp;</td>
      <td class="c1" colspan="2">
        <input type="submit" value="提交" name="btn-ok"/>
      </td>
    </tr>
  </table>
</form>
</body>
</html>