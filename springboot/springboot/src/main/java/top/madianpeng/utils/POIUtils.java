package top.madianpeng.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * poi 工具类
 * 
 * @author Can
 *
 */
public class POIUtils {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("resource")
	public List<String[]> importExcel(MultipartFile file) throws IOException {
		InputStream inputStream = file.getInputStream();
		String filename = file.getOriginalFilename();
		Workbook workbook = null;
		List<String[]> list = new ArrayList<>();
		if (isExcel2007(filename)) {
			workbook = new XSSFWorkbook(inputStream);
		} else {
			workbook = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheetAt(0);
		// 获取sheet的行数
		int rows = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rows; i++) {
			// 过滤表头行
			if (i == 0) {
				continue;
			}
			// 获取当前行的数据
			Row row = sheet.getRow(i);
			String[] strings=new String[20];
			int index = 0;
			for (Cell cell : row) {
				strings[index] = cell.getStringCellValue();
				index++;
			}
			list.add(strings);
		}
		logger.info("导入文件解析成功！");
		return list;
	}

	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}
}
