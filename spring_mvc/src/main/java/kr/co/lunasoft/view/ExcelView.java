package kr.co.lunasoft.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import kr.co.lunasoft.domain.Item;

public class ExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//출력할 데이터를 가져옵니다
		List<Item> list = (List<Item>)model.get("list");

		//하나의 시트를 생성
		Sheet sheet = workbook.createSheet();
		//시트 이름 정하기
		workbook.setSheetName(0, "ITEM");
		//시트에서 컬럼의 너비 정하기
		sheet.setColumnWidth(1, 256*20);

		//첫번째 셀 출력
		Row firstRow = sheet.createRow(0);
		Cell cell = firstRow.createCell(0);
		cell.setCellValue("상품명");

		cell = firstRow.createCell(1);
		cell.setCellValue("설명");

		cell = firstRow.createCell(2);
		cell.setCellValue("가격");

		//데이터 출력
		int idx = 1;
		for(Item item : list) {
			Row row = sheet.createRow(idx);

			cell = row.createCell(0);
			cell.setCellValue(item.getItemname());

			cell = row.createCell(1);
			cell.setCellValue(item.getDescription());

			cell = row.createCell(2);
			cell.setCellValue(item.getPrice());
			idx = idx + 1;
		}

	}

}
