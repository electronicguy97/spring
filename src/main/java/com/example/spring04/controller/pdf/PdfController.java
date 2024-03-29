package com.example.spring04.controller.pdf;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring04.service.pdf.PdfService;

@Controller
@RequestMapping("/pdf/*")
public class PdfController {
	@Inject
	PdfService pdfService;

	@RequestMapping("list.do")
	public ModelAndView list() {
		String result = pdfService.createPdf(); // pdf 문서 생성 
		return new ModelAndView("pdf/result", "message", result);
	}
}
