package top.madianpeng.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.madianpeng.utils.IDUtils;

@Controller
public class ImgUpload {
	@RequestMapping("/imgupload")
	@ResponseBody
	public Map<String, String> updateitem(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{

		//保存图片到 
		String name = IDUtils.genImageName();
		//jpg
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		
		file.transferTo(new File("D:\\imgs\\" + name + "." + ext));
		Map<String, String> map = new HashMap<String, String> ();
		map.put("picid", name + "." + ext);
		return map;
		
	}
}
