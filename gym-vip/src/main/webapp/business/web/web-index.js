// 板块列表
$(function() {
	$.post(glogbalData.contextPath + "/webindex/getcolumn", {}, function(data) {
		if (data && data.length) {
			var bklb_ul_html = "";

			for (var i = 0; i < data.length; i++) {
				bklb_ul_html += "<div class='mainbodytitlecontent1'>";
				bklb_ul_html += "<div class='mainbodytitlecontent11'>";
				bklb_ul_html += "<img src='" + glogbalData.contextPath + "/deco/img/web/mainbodyico.jpg' />";
				bklb_ul_html += "</div>";
				bklb_ul_html += "<div class='mainbodytitlecontent12'>";
				bklb_ul_html += "<a href='" + glogbalData.contextPath + "/webdocument/home_list?columnGuid=" + data[i].columnguid + "' target='_blank'>" + data[i].title + "</a>";
				bklb_ul_html += "<ul>";
				bklb_ul_html += "<li>帖数: " + data[i].documentNum + "</li>";
				bklb_ul_html += "<li></li>";
				bklb_ul_html += "</ul>";
				bklb_ul_html += "</div>";
				bklb_ul_html += "</div>";
			}

			$("#bklb_div").html(bklb_ul_html);
		}
	});
});

// 最新发帖列表
$(function() {
	$.post(glogbalData.contextPath + "/webindex/getdocument", {
		"num" : "10"
	}, function(data) {
		if (data && data.length) {
			var zxft_ul_html = "<tr><th class='title'>标题</th><th class='creator'>发帖人</th><th class='createtime'>发帖时间</th></tr>";

			for (var i = 0; i < data.length; i++) {
				// 偶数行
				if ((i + 1) % 2 == 0) {
					zxft_ul_html += "<tr class='zebra'>";
				}
				// 奇数行
				else {
					zxft_ul_html += "<tr>";
				}

				zxft_ul_html += "<td class='title'><a href='" + glogbalData.contextPath + "/webreply/replyview?guid=" + data[i].guid + "' target='_blank'> " + data[i].title + "</a></td>";
				zxft_ul_html += "<td class='zfftname creator'><div align='center'>" + data[i].creator + "</div></td>";
				zxft_ul_html += "<td class='zfftdate createtime'>" + data[i].createtime + "</td></tr>";
			}

			$("#zxft_list").html(zxft_ul_html);
		}
	});
});

// 获取最新回复列表
$(function() {
	$.post(glogbalData.contextPath + "/webindex/zxhftoplist", {
		"number" : "10"
	}, function(data) {
		if (data && data.length) {
			var zxhf_ul_html = "<tr><th class='title'>标题</th><th class='creator'>回复人</th><th class='createtime'>回复时间</th></tr>";

			for (var i = 0; i < data.length; i++) {
				var createtime = getDateUrl(data[i].createtime);

				// 偶数行
				if ((i + 1) % 2 == 0) {
					zxhf_ul_html += "<tr class='zebra'>";
				}
				// 奇数行
				else {
					zxhf_ul_html += "<tr>";
				}

				zxhf_ul_html += "<td class='title'><a href='" + glogbalData.contextPath + "/webreply/replyview?guid=" + data[i].docguid + "' target='_blank'> " + data[i].title + "</a></td>";
				zxhf_ul_html += "<td class='zfftname creator'><div align='center'>" + data[i].creator + "</div></td>";
				zxhf_ul_html += "<td class='zfftdate createtime'>" + createtime+ "</td></tr>";
			}

			$("#zxhf_list").html(zxhf_ul_html);
		}
	});
});
//打开购买明细统计页
function selectTotalBuy()
{
	openIFrameDialog("select");
}
//日期格式化
function getDateUrl(val, rec) {
	var value = dateFormatShow1(val, rec);

	return value;
}
//
function getzxft() {
	$("#zxft_list").show();
	$("#zxhf_list").hide();

	$("#CSS_zxhflb1").attr("class", "zxftbj22");
	$("#CSS_zxhflb2").attr("class", "zxftbj2");
}

function getzxhf() {
	$("#zxhf_list").show();
	$("#zxft_list").hide();

	$("#CSS_zxhflb1").attr("class", "zxftbj2");
	$("#CSS_zxhflb2").attr("class", "zxftbj22");
}