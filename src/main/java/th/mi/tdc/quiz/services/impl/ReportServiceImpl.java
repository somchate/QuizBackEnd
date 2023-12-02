package th.mi.tdc.quiz.services.impl;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.server.ResponseStatusException;
import th.mi.tdc.quiz.entity.Nst;
import th.mi.tdc.quiz.repository.NstRepository;
import th.mi.tdc.quiz.services.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private NstRepository nstRepository;

    public ReportServiceImpl(NstRepository nstRepository) {
        this.nstRepository = nstRepository;
    }

    @Override
    public ResponseEntity<byte[]> exportRequestVerifyRpt(HttpServletResponse response, String reportFormat) throws JRException, FileNotFoundException {
        String path = "C:\\Users\\somch\\Desktop\\report";
//        String rptName = "requestRpt";
          String sampleRpt = "sample-th";
        URL resource = this.getClass().getResource("/reports/" + sampleRpt + ".jrxml");
        if (resource == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "template: "+ sampleRpt + ".jrxml" + " does not exists.");
        }

        List<Nst> nst = nstRepository.findAll();
        File file= ResourceUtils.getFile( "classpath:" + "reports\\"+ sampleRpt + ".jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(nst);
        Map<String,Object> parameters= new HashMap<>();
        parameters.put("createBy", "Java Techie");

//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
//        if(reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\"+rptName+".html");
//        }
//        if(reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint,path + "\\"+rptName+".pdf");
//           }

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=" + sampleRpt + ".pdf");

        JasperPrint jasperPrint =
                JasperFillManager.fillReport
                        (
                                JasperCompileManager.compileReport(
                                        ResourceUtils.getFile("classpath:requestRpt.jrxml")
                                                .getAbsolutePath()) // path of the jasper report
                                , parameters // dynamic parameters
                                , dataSource
                        );

        byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION,"inline;filename="+ sampleRpt +".pdf");
        return   ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
}
