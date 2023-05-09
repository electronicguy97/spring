package com.example.spring04.service.chart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.inject.Inject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import com.example.spring04.model.shop.CartDAO;
import com.example.spring04.model.shop.CartDTO;


@Service
public class JFreeChartServiceImpl implements JFreeChartService {

	@Inject
	CartDAO cartDao;
	
	@Override
	public JFreeChart createChart() {
		List<CartDTO> list=cartDao.cart_money(); //장바구니 데이터
		DefaultPieDataset dataset=new DefaultPieDataset(); // 파이차트 데이터셋
		for(CartDTO dto : list) {
			dataset.setValue(dto.getProduct_name(), dto.getMoney()); //데이터 입력
		}
		JFreeChart chart=null;
		String title="장바구니 통계";
		try {
			chart=ChartFactory.createPieChart(title, dataset, true, true, false); //파이차트 객체
			chart.getTitle().setFont(new Font("돋움",Font.BOLD, 15)); //폰트 설정
			Font font=new Font("돋움",Font.PLAIN,12);
			Color color=new Color(0,0,0); //색상 설정
			//차트 테마
			StandardChartTheme chartTheme=(StandardChartTheme)StandardChartTheme.createJFreeTheme(); 
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);
			chartTheme.setAxisLabelPaint(color);
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			chartTheme.apply(chart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart; //차트 리턴 
	}

}
