package com.meeting.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.meeting.model.People;
import com.meeting.service.UserService;
import com.meeting.util.ResultUtil;
import com.meeting.util.UploadFileUtil;

@Controller
public class UploadFileController {
	
	@Autowired
	private UserService userService;
	
	// 上传文件
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public ResultUtil uploadFile(MultipartFile file, HttpSession session) throws IOException {
		// 本地使用,上传位置
		String rootPath = "E:/code/jee/Meeting/src/main/webapp/static/uploads";
		// String rootPath ="/www/uploads/";
		// 文件的完整名称,如spring.jpeg
		String filename = file.getOriginalFilename();
		// 文件名,如spring
		String name = filename.substring(0, filename.indexOf("."));
		// 文件后缀,如.jpeg
		String suffix = filename.substring(filename.lastIndexOf("."));
		// 创建年月文件夹
		Calendar date = Calendar.getInstance();
		File dateDirs = new File(date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1));
		// 目标文件
		File descFile = new File(rootPath + File.separator + dateDirs + File.separator + filename);
		int i = 1;
		// 若文件存在重命名
		String newFilename = filename;
		while (descFile.exists()) {
			newFilename = name + "(" + i + ")" + suffix;
			String parentPath = descFile.getParent();
			descFile = new File(parentPath + File.separator + dateDirs + File.separator + newFilename);
			i++;
		}
		// 判断目标文件所在的目录是否存在
		if (!descFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			descFile.getParentFile().mkdirs();
		}
		// 将内存中的数据写入磁盘
		file.transferTo(descFile);
		// 完整的url
		String fileUrl = "/Meeting/static/uploads/" + dateDirs + "/" + newFilename;
		People people = (People)session.getAttribute("user");
		people.setImgPath(fileUrl);
		userService.upload(people);
		ResultUtil resultVO = new ResultUtil();
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		UploadFileUtil uploadFileVO = new UploadFileUtil();
		uploadFileVO.setTitle(filename);
		uploadFileVO.setUrl(fileUrl);
		resultVO.setData(uploadFileVO);
		return resultVO;
	}
}