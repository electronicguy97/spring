package com.example.spring04.controller.chart;

import java.io.FileOutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring04.service.chart.JFreeChartService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("/jchart/*")
public class JFreeChartController {
	@Inject
	JFreeChartService chartService;
	
	@RequestMapping("chart1.do")
	public void createChart1(HttpServletResponse response) {
		try {
			JFreeChart chart=chartService.createChart(); //차트 생성
			// 차트를 png 이미지로 출력
			ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 900, 550);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("chart2.do")
	public ModelAndView createChart2(HttpServletResponse response) {
		String message="";
		try {
			JFreeChart chart=chartService.createChart(); //차트 생성
			Document document=new Document(); //pdf 문서 객체
			//pdf 파일 생성
			PdfWriter.getInstance(document, new FileOutputStream("c:/work/test.pdf"));
			document.open(); //pdf 문서 오픈
			// 차트를 png 이미지로 변환
			Image png=Image.getInstance(ChartUtils.encodeAsPNG(chart.createBufferedImage(500,500)));
			document.add(png); //pdf에 이미지 추가
			document.close(); //pdf 저장
			message="pdf 파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			message="pdf 파일 생성 실패";
		}
		return new ModelAndView("chart/jchart02", "message", message);
	}
	
}
