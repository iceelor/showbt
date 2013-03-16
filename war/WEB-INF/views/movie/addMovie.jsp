<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/nej/src/define.js"></script>
<style type="text/css">
#myEditor .w-edt{width:100%;padding: 5px;}
</style>
</head>
<body>
<div id="postTextArea" class="postTextArea g-bd2"> 
	<div class="g-bdc">
	<form action="./addMovieDo" method="post" id="addMovieForm">
		<div class="g-mn">
			<div class="g-box3">
				<h2 class="w-fttl">共享电影</h2>
			</div>
			<div class="g-box2">
				<h3 class="w-ttl">
					<em>标题</em> <small>(不可不填)</small>
				</h3>
				<input type="text" class="w-inputxt w-inputxt-1" maxlength="50" name="title">
			</div>
			<div class="g-box2">
				<h3 class="w-ttl">
					<em>图片</em> <small>(多张可用逗号隔开)</small>
				</h3>
				<input type="text" class="w-inputxt w-inputxt-1" maxlength="480" name="picture">
			</div>
			<div class="g-box2">
				<h3 class="w-ttl"><em>内容</em></h3>
				<div class="editorWrap ztag">
					<div id="myEditor"></div>
					<script type="text/javascript">
						var f = function() {
							var _ = NEJ.P, _e = _('nej.e'), _u = _('nej.u'), _v = _('nej.v'), _tu = _('t.u'), _p = _('nej.ui');
							_editor = _p._$$CustomEditor._$allocate({
								parent : _e._$get('myEditor'),
								clazz : 'w-edt',
								focus : true,
								content : ''
							});
							_v._$addEvent('btn_public', 'click', getValue);
							function getValue(){
						    	_e._$get('content').value = _editor._$getContent();
						    	_e._$get('addMovieForm').submit();
						    }
						}
						window.NEJ_CONF = {
							root : '/nej/res/'
						};
						define([ '{lib}ui/editor/custom.js' ], f);
					</script>
					<input type="hidden" name="content" id="content" value="" />
				</div>
			</div>
			<div class="g-box2">
				<h3 class="w-ttl">
					<em>标签</em> <small>(多个可用逗号隔开)</small>
				</h3>
				<input type="text" class="w-inputxt w-inputxt-1" maxlength="50" name="tag">
			</div>
			<div class="g-box2">
				<h3 class="w-ttl">
					<em>下载地址</em>
				</h3>
				<input type="text" class="w-inputxt w-inputxt-1" maxlength="480" name="downUrl">
			</div>
			<div class="g-box2">
	            <div class="m-edtact">
	              <input type="button" value="取&#12288;消" class="w-bbtn delete ztag">
	              <input type="button" value="保存草稿" class="w-bbtn save ztag" style="display:none">
	              <input type="button" value="预&#12288;览" class="w-bbtn preview ztag">
	              <input type="button" value="发&#12288;布" class="w-bbtn w-bbtn-0 publish ztag" id="btn_public" onclick="btn_publish();">
	            </div>
           </div>
		</div>
		<div class="g-sd">
			<div class="g-box2">
				<div class="clearfix">
					<h3 class="publishTtl">发布到</h3>
				</div>
			</div>
		</div>
	</form>
	</div>
</div>
</body>
</html>