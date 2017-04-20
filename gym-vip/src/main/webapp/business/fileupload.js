/** *******************************附件上传********************************** */
function setUpload(swfUrl, uploaderUrl, fileuploadID, queueID, buttonText,
		hdFilesID, hdfileSizeID, queueSizeLimit, uploadLimit) {
	$('#' + fileuploadID).uploadify({
		'swf' : swfUrl,
		'uploader' : uploaderUrl,
		'multi' : true,
		'auto' : true,
		'fileTypeExts' : '*.*',
		'fileTypeDesc' : 'All Files',
		'queueID' : queueID,
		'queueSizeLimit' : queueSizeLimit,
		'uploadLimit' : uploadLimit,
		'buttonText' : buttonText,
		'removeCompleted' : false,
		'itemTemplate' : '<div id="${fileID}" class="uploadify-queue-item">\
    <div class="cancel">\
        <a href="javascript:ovCancelUpload( \'${fileID}\',\'${numfileSize}\',\''
				+ fileuploadID
				+ '\',\''
				+ hdFilesID
				+ '\',\''
				+ hdfileSizeID
				+ '\')">删除</a>\
    </div>\
    <span class="fileName">${fileName} (${fileSize})</span><span class="data"></span>\
</div>',
		'onUploadSuccess' : function(file, data, response) {
			var ret = eval('(' + data + ')');
			if (ret.success) {
				$('#' + hdFilesID).val(
						$('#' + hdFilesID).val() + "*"
								+ file.id + "|" + ret.message
								+ "|" + file.name);
				var totalSize = parseInt($('#' + hdfileSizeID).val())
						+ parseInt(file.size);
				$('#' + hdfileSizeID).val(totalSize);

			} else {
				$.messager.alert('错误', "上传失败！");
			}
		},
		'onUploadStart' : function(file) {
			var totalSize = parseInt($('#' + hdfileSizeID)
					.val())
					+ parseInt(file.size);
			if (totalSize > 8 * 1024 * 1024) {
				alert("您上传的文件总大小超过了最大限制8M");
				$('#' + fileuploadID).uploadify("cancel",
						file.id);
			}
		}
	});
}

function ovCancelUpload(fileID, fileLen, fileuploadID, hdFilesID, hdfileSizeID) {
	$('#' + fileuploadID).uploadify("cancel", fileID);
	// 附件总大小减去取消的文件的大小
	var totalSize = parseInt($('#' + hdfileSizeID).val()) - parseInt(fileLen);
	if (totalSize < 0) {
		totalSize = 0;
	}
	$('#' + hdfileSizeID).val(totalSize);

	// 去掉上传附件字符串中该文件对应的部分
	var allFiles = $('#' + hdFilesID).val();
	var newFiles = "";
	var allFilesArr = allFiles.split('*');
	for (var i = 0; i < allFilesArr.length; i++) {
		if (allFilesArr[i].indexOf(fileID) < 0 && allFilesArr[i] != "") {
			newFiles += "*" + allFilesArr[i];
		}
	}

	$('#' + hdFilesID).val(newFiles);
}

/** *******************************附件删除 begin******************************** */
// 删除附件
function removeFile(baseUrl, blobGuid, fileSize) {
	$.messager.confirm('确认', '确定删除此附件吗？', function(r) {
		if (r) {
			$.post(baseUrl + "/upload/delete", {
				"blobGuid" : blobGuid
			}, function(data) {
				var ret = eval('(' + data + ')');
				if (ret.success) {
					// 将{36}总共38位的guid改为去掉大括号的36位的guid
					var object = "#" + blobGuid.replace("{", "").replace("}", "");
					$(object).remove();
					var totalSize = parseInt($('#hdFilesSize').val())
							- parseInt(fileSize);
					if (totalSize < 0) {
						totalSize = 0;
					}
					$('#hdFilesSize').val(totalSize);
				} else {
					$.messager.alert('错误', "删除失败！");
				}
			});
		}
	});
}
/** *******************************附件删除 end********************************** */
