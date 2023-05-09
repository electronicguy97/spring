<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	// uploadedList 클래스 내부의 span 태그를 클릭하면
	$(".uploadedList").on("click","span",function(event){
		var that=$(this); //현재 클릭한 태그
		$.ajax({
			url: "/spring04/upload/delete_file",
			type: "post",
			data: {file_name: $(this).attr("data-src")},
			dataType: "text",
			success: function(result){
				if(result=="deleted"){ // 삭제 성공
					//현재 span 태그의 parent 중에서 div 태그 제거 
					that.parent("div").remove();  
				}
			}
		});
	});
	//fileDrop 클래스에 드래그 시작, 드래그중이면 
	$(".fileDrop").on("dragenter dragover", function(event){
		event.preventDefault(); //태그의 기본 효과 막기 
	});
	// fileDrop 클래스에 드롭하면
	$(".fileDrop").on("drop", function(event){
		event.preventDefault(); //태그의 기본효과 막기
		// 드래그로 전달한 파일 배열 
		var files=event.originalEvent.dataTransfer.files;
		// 첫번째 파일
		var file=files[0];
		// 자바스크립트 코드로 폼 생성
		var form_data=new FormData();
		// 폼에 첨부파일 추가 
		form_data.append("file", file);
		$.ajax({
			type:"post",
			url: "/spring04/upload/ajax_upload",
			data: form_data,
			dataType: "text",
			processData: false,
			contentType: false,
			success: function(data){
				//동적으로 태그 생성 
				var str="<div>";
				str+= "<a href='/spring04/upload/display_file?file_name="+data+"'>"+getOriginalName(data)+"</a>";
				str+="<span data-src="+data+">[삭제]</span></div>";
				// uploadedList 클래스에 동적으로 생성한 태그 추가 
				$(".uploadedList").append(str);
			}
		});
	});
});
function getOriginalName(fileName){
	var idx=fileName.indexOf("_")+1; // uuid_파일이름 중에서 파일이름의 인덱스
	return fileName.substr(idx); // 파일이름만 리턴
}
</script>
<style>
.fileDrop { width: 100%; height: 200px; border: 1px dotted blue; }
</style>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<h2>ajax file upload</h2>
<div class="fileDrop"></div>	<!-- 파일 업로드 영역  -->
<div class="uploadedList"></div> <!-- 업로드 성공한 파일 리스트 영역 --> 
</body>
</html>







