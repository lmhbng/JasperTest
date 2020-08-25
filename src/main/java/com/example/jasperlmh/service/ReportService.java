package com.example.jasperlmh.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.jasperlmh.entity.Car;
import com.example.jasperlmh.repository.CarRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {
@Autowired
CarRepository carRepo;
public String print(String format) throws JRException, IOException {
	// make a dir in this classpath;
	File filezz=new File("Report");
	if(!filezz.exists())
		filezz.mkdir();
	
	// Get a path//
	String ppa= new File(".").getCanonicalPath();
	SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmm");
	
	String date=df.format(new Date());
	
	
	String ff=ppa+"\\Report\\Car"+date+".html";
	String fff=ppa+"\\Report\\Car"+date+".pdf";
	//------------------//
	List<Car>car=carRepo.findAll();
	File file =ResourceUtils.getFile("classpath:Cars.jrxml");
	JasperReport report=JasperCompileManager.compileReport(file.getAbsolutePath());
	JRBeanCollectionDataSource source=new JRBeanCollectionDataSource(car);
	Map<String,Object> parameters=new HashMap<>();
	parameters.put("Created By", "LMH");
	JasperPrint jasperPrint=JasperFillManager.fillReport(report, parameters,source);
	if(format.equalsIgnoreCase("html")) {
		JasperExportManager.exportReportToHtmlFile(jasperPrint,ff);
	 return "Report Generated at"+ff;	
	}
	else if(format.equalsIgnoreCase("Pdf")) {
		JasperExportManager.exportReportToPdfFile(jasperPrint,fff);
		return "Report Generated at"+fff;
	}
	else return "Something wrong I can feel it";
}
}
