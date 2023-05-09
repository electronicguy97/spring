package com.example.spring04.service.pdf;

import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring04.model.shop.CartDAO;
import com.example.spring04.model.shop.CartDTO;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfServiceImpl implements PdfService {

	@Inject
	CartDAO cartDao; 
	
	@Override
	public String createPdf() {
		String result="";
		try {
			Document document=new Document(); //pdf 문서 객체
			PdfWriter.getInstance(document, new FileOutputStream("c:/work/sample.pdf")); //파일경로
			document.open(); //pdf 문서 오픈
			//폰트 설정
			BaseFont baseFont=BaseFont.createFont("c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font=new Font(baseFont, 12); //폰트 사이즈
			PdfPTable table=new PdfPTable(4); // 4개의 셀
			Chunk chunk=new Chunk("장바구니", font); 
			Paragraph ph=new Paragraph(chunk); // 문단
			ph.setAlignment(Element.ALIGN_CENTER); //가운데 정렬
			document.add(ph); // pdf 문서에 문단 추가
			document.add(Chunk.NEWLINE); // 줄바꿈
			document.add(Chunk.NEWLINE);
			PdfPCell cell1=new PdfPCell(new Phrase("상품명",font)); // 테이블의 셀의 내용
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1); //테이블에 셀 추가 
			
			PdfPCell cell2=new PdfPCell(new Phrase("단가",font));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell2);
			
			PdfPCell cell3=new PdfPCell(new Phrase("수량",font));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);
			
			PdfPCell cell4=new PdfPCell(new Phrase("금액",font));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell4);
			
			List<CartDTO> items=cartDao.list("kim"); //장바구니 리스트 
			for(int i=0; i<items.size(); i++) {
				CartDTO dto=items.get(i); 
				//셀에 내용 추가( 스트링만 가능함 )
				PdfPCell cellProductName=new PdfPCell(new Phrase(dto.getProduct_name(),font));
				table.addCell(cellProductName);
				
				PdfPCell cellPrice=new PdfPCell(new Phrase(dto.getPrice()+"",font));
				table.addCell(cellPrice);
				
				PdfPCell cellAmount=new PdfPCell(new Phrase(dto.getAmount()+"",font));
				table.addCell(cellAmount);
				
				PdfPCell cellMoney=new PdfPCell(new Phrase(dto.getMoney()+"",font));
				table.addCell(cellMoney);
			}
			document.add(table); // pdf 문서에 테이블 추가 
			document.close(); // pdf 문서 닫기, 저장 완료 
			result="pdf 파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result="pdf 파일 생성 실패...";
		}
		return result;
	}

}
