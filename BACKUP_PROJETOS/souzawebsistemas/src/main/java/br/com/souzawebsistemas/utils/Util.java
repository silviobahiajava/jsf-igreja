package br.com.souzawebsistemas.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.web.servlet.ModelAndView;

import br.com.souzawebsistemas.model.TesteData;

public class Util {

	public static Date converterStringParaDate(String dataStr) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
		Date dataFormatada=null;
		try {
			 dataFormatada = formato.parse(dataStr);
			return dataFormatada;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataFormatada; 
	}

		public static String converterDateParaString(Date dataObj) {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
			String dataStr=null;
			try {
				 dataStr = formato.format(dataObj);
				return dataStr;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return dataStr; 
	}
		//--------------------------------------------
		//convertendo data (ano,mes,dia) pra localdate
		public static LocalDate converterDataPraLocalDate(int ano,int mes,int dia) {
			LocalDate ld=null;
			switch (mes) {
			
			case 1:
				LocalDate.of(ano, Month.JANUARY, dia);
				break;
			case 2:
				LocalDate.of(ano,Month.FEBRUARY, dia);
			case 3:
				LocalDate.of(ano,Month.MARCH, dia);
			case 4:
				LocalDate.of(ano,Month.APRIL, dia);
			case 5:
				LocalDate.of(ano,Month.MAY, dia);
			case 6:
				LocalDate.of(ano,Month.JUNE, dia);
			case 7:
				LocalDate.of(ano,Month.JULY, dia);
			case 8:
				LocalDate.of(ano,Month.AUGUST, dia);	
			case 9:
				LocalDate.of(ano,Month.SEPTEMBER, dia);
			case 10:
				LocalDate.of(ano,Month.OCTOBER, dia);
			case 11:
				LocalDate.of(ano,Month.NOVEMBER, dia);
			case 12:
				LocalDate.of(ano,Month.DECEMBER, dia);	
			default:
				break;
			}
			
			return ld;
		}
		//convertendo localdate pra string
		public static String converterLocalDatePraString(LocalDate localdate) {
			DateTimeFormatter formatador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataStr=formatador.format(localdate);
			return dataStr;
		}
		//convertendo localdate em date do mysql
		public static java.sql.Date converterLocalDatePraDateSql(LocalDate localdate){
			java.sql.Date  dataSql=java.sql.Date.valueOf(localdate);
			return dataSql;
		}
		
		/*
		 * System.out.println("mostrando datas");
		ModelAndView md=new ModelAndView("datas");
		//convertendo data em localdate
		LocalDate dataTeste=LocalDate.of(2014,Month.JANUARY,25);
		//md.addObject("dataTeste", dataTeste);
		//transformando localdate em string(ano,mes,dia)
		DateTimeFormatter formatador=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataStr = formatador.format(dataTeste);
		md.addObject("dataStr", dataStr);
		
		//salvando no banco
		TesteData td=new TesteData();
		String nome="Luciana";
		Date dataSql=java.sql.Date.valueOf(dataTeste);//convertendo LocalDate em Date do Sql
		td.setNome(nome);
		td.setDataSql(dataSql);
		tr.save(td);
		TesteData pesquisa=tr.getById(1L);
		System.out.println("cadastro feito com sucesso\n");
		System.out.println("codigo 1 nome- "+pesquisa.getNome()+"\n");
		LocalDate dataPesquisada = pesquisa.getDataSql().toLocalDate();
		System.out.println("codigo 1 data "+dataPesquisada);
		
		//convertendo data do mysql em localDate
		//LocalDate localDate = Date.valueOf("2019-01-10").toLocalDate();
		
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * */
		public static String localDateToString(LocalDate ld) {
			Date date = java.sql.Date.valueOf(ld);
	        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	        String data = format.format(date);
	        return data;
		}
		 
}
