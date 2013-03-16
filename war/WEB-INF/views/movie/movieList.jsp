<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:forEach items="${movieList}" var="m">
	<div class="m-mlist">
	<div class="mlistimg">
		<div class="w-img js-709963">
			<a target="_blank" href="${user.url}" title="${user.screenName}">
			<img class="js-709963" src="${user.profileImageUrl}"></a>
			<div class="w-img w-img-1" style="display: none;">
				<a target="_blank" href="#"><img class="js-709963"></a>
			</div>
			<div class="a-w-sel">
				<div style="margin-top: -68px;" class="w-sel js-709963">
					<div class="selc">
						<div class="selcc">
							<div class="seli">
								<a target="_blank" href="#" class="sela selicn sel1">发私信</a>
							</div>
							<div class="seli" style="display: none;">
								<a target="_blank" href="#" class="sela selicn selask">提问题</a>
							</div>
							<div class="seli">
								<a href="#" class="sela selicn sel3">关注</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="mlistcnt">
		<div class="isay">
			<div class="isayt">
				<a target="_blank" title="查看全文 - 02/28 15:04" href="#" class="isayc js-709963">打开新页</a>
				<span style="display: none;" class="isayc js-709963"></span>
			</div>
			<div class="isaym">
				<div class="w-who">
					<a target="_blank" class="js-709963" href="#">${m.nickName}</a>
					<span><b>${m.title}</b></span>
				</div>
				<div class="js-709963">
					<div class="m-icnt">
						<div class="cnt">
							<div class="img" style="width: 164px; height: auto;">
								<div class="imgc">
									<a hidefocus="true" href="#">
										<img style="width: 164px;" class="js-671567" src="${m.pictures[0]}">
										<c:if test="${fn:length(m.pictures)>1}">
											<span class="total">
												<span class="totalbg"></span>
												<span class="totalnum">${fn:length(m.pictures)}</span>
											</span> 
										</c:if>
									</a>
								</div>
								<ol style="display: none;">
								<c:forEach items="${m.pictures}" var="p">
									<li>
										<div class="imgc">
											<a hidefocus="true" href="#"><img width="500" large="${p}" src="${p}"></a>
										</div>
										<div class="more more-2">
											<a onclick="loft.x.stopEvent(event);loft.m.g.mp755187.photoshow(0);return false;" class="w-zoom">查看大图</a>
										</div>
										<p></p>
									</li>
								</c:forEach>
								</ol>
								<div style="display: none;" class="zoom">
									<div class="zoombg"></div>
									<a href="#" class="zoomi js-671567">查看完整图片</a>
								</div>
								<div style="display: none;" class="more more-2">
									<a class="w-zoom js-671567">查看大图</a>
								</div>
							</div>
							<div class="txt js-671567">
								${m.content}
							</div>
						</div>
					</div>
				</div>
				<div class="w-opt">
					<div class="opta js-709963">
						<c:forEach items="${m.tags}" var="t">
						<span class="opti"> <a href="#" class="w-jing" title="${t}"><span>${t}</span></a></span>
						</c:forEach>
					</div>
					<div class="optb">
						<c:if test="${m.userId == user.id}">
							<span class="opti"><a class="js-709963" href="#">编辑</a></span>
							<span class="opti"><a hidefocus="true" class="js-709963" href="#">删除</a></span>
						</c:if>
						<span class="opti"><a hidefocus="true" class="js-709963" href="#">热度(0)</a> <span class="opticrt"></span></span>
						<span class="opti"><a hidefocus="true" class="js-709963" href="#">评论(0)</a><span class="opticrt"></span></span>
						<span class="opti"><a target="_blank" class="js-709963" href="${m.downUrl}">下载</a></span>
						<span class="opti"><a href="#" hidefocus="true" class="js-709963">推荐</a></span>
						<span class="opti"><a href="#" hidefocus="true" class="w-icn w-icn-0b js-709963" title="喜欢">喜欢</a></span>
					</div>
				</div>
			</div>
			<div class="a-isaym2">
				<div style="margin-top: -81px;" class="isaym2 isaym2-open js-709963"></div>
			</div>
			<div class="a-isaym2">
				<div style="margin-top: -81px;" class="isaym2 isaym2-open js-709963"></div>
			</div>
			<div class="isayb"></div>
		</div>
	</div>
</div>
</c:forEach>