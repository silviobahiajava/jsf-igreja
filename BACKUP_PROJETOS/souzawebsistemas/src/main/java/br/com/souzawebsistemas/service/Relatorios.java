package br.com.souzawebsistemas.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Service
public class Relatorios {
	private  static String DIRETORIO="classpath:relatorios/";
	private  static String PREFIXO="relatorio_";
	private  static String SUFIXO=".jasper";
	@Autowired
	private  Connection con;
	private static Map<String, Object>params=new HashMap<String, Object>();
	
	public static void addParams(String key,Object value) {
		params.put(key, value);
	}
	//=====================
	public  byte[] geraRelatorio() {
		byte[] bytes = null;
		File file = null;
		try {
			String caminho=DIRETORIO+"MISSAOEFE_CLIENTE01.jasper";
			//String caminho="C:\\Users\\SouzaWeb Sistemas\\JaspersoftWorkspace\\Missaoefe\\MISSAOEFE_CLIENTE.jasper";
					
			///file = ResourceUtils.getFile((DIRETORIO).concat(PREFIXO).concat(nomeRelatorio).concat(SUFIXO));
			file = ResourceUtils.getFile(caminho);
			//JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, con);
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, con);
			bytes = JasperExportManager.exportReportToPdf(print);

		} catch (FileNotFoundException | JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bytes;
	}
	
	
	
	public  byte[] geraRelatorioGenerico(String nomeRelatorio) {
		byte[] bytes = null;
		File file = null;
		try {
			String caminho=DIRETORIO+nomeRelatorio+".jasper";
			//String caminho="C:\\Users\\SouzaWeb Sistemas\\JaspersoftWorkspace\\Missaoefe\\MISSAOEFE_CLIENTE.jasper";
					
			///file = ResourceUtils.getFile((DIRETORIO).concat(PREFIXO).concat(nomeRelatorio).concat(SUFIXO));
			file = ResourceUtils.getFile(caminho);
			//JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, con);
			JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, con);
			bytes = JasperExportManager.exportReportToPdf(print);

		} catch (FileNotFoundException | JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bytes;
	}
	
	
	

}
