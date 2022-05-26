package br.com.souzawebsistemas.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.HtmlResourceHandler;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.web.util.WebHtmlResourceHandler;

@Service
public class RelatorioService {
	private static final String JASPER_DIRETORIO = "classpath:relatorios/";
    private static final String JASPER_PREFIXO = "MISSAOEFE_CLIENTE";
    private static final String JASPER_SUFIXO = ".jasper";

    @Autowired
    private Connection connection;

    @Autowired
    ResourceLoader resourceLoader;

    private Map<String, Object> params = new HashMap<>();

   

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public byte[] exportarPDF(String code) {
        byte[] bytes = null;
        try {
            Resource resource = resourceLoader
                    .getResource(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
            InputStream stream = resource.getInputStream();

            JasperPrint print = JasperFillManager.fillReport(stream, params, connection);
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch ( JRException | IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public HtmlExporter exportarHTML(String code, HttpServletRequest request, HttpServletResponse response) {
        HtmlExporter htmlExporter = null;
        try {
            File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            htmlExporter = new HtmlExporter();
            htmlExporter.setExporterInput(new SimpleExporterInput(print));

            SimpleHtmlExporterOutput htmlExporterOutput =
                    new SimpleHtmlExporterOutput(response.getWriter());

            HtmlResourceHandler resourceHandler =
                    new WebHtmlResourceHandler(request.getContextPath() + "/image/servlet?image={0}");
            htmlExporterOutput.setImageHandler(resourceHandler);
            htmlExporter.setExporterOutput(htmlExporterOutput);

         //   request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, print);
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return htmlExporter;
    }
    
    
    //========================================================
    
    
    public byte[] gerarPDF(String nomeRelatorio) {
        byte[] bytes = null;
        try {
            Resource resource = resourceLoader
                    .getResource(JASPER_DIRETORIO.concat(nomeRelatorio).concat(JASPER_SUFIXO));
            InputStream stream = resource.getInputStream();

            JasperPrint print = JasperFillManager.fillReport(stream, params, connection);
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch ( JRException | IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
