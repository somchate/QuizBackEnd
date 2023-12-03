package th.mi.tdc.quiz.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import th.mi.tdc.quiz.entity.Nst;
import th.mi.tdc.quiz.repository.NstRepository;
import th.mi.tdc.quiz.services.ReportService;
import th.mi.tdc.quiz.services.NstService;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.*;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
//@RequestMapping( produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping({"/api"})
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private NstRepository nstRepository;

    @Autowired
    private NstService nstService;

//    public ReportController(ReportService reportService) {
//        super();
//        this.reportService = reportService;
//    }


    //    @CrossOrigin(origins = "https://gdcc.tdc.mi.th", maxAge = 3600)
    @PostMapping("/v1/report")
//    public void (HttpServletResponse response,@PathVariable String format) throws JRException, IOException {
//           reportService.exportRequestVerifyRpt(response,format);

    public void report(HttpServletResponse response, @RequestBody Map<String, Object> payload) throws JRException, IOException {

        // target jasper filename e.g. sample.jasper
        String template = (String) payload.get("template");

        // check template file exists or not
        URL resource = this.getClass().getResource("/reports/" + template);
        if (resource == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "template: " + template + " does not exists.");
        }

        // output pdf filename
        String fileName = (String) payload.get("fileName");

        String username = (String) payload.get("username");

        // using parameters in json as jasper report parameters from request
        Map<String, Object> params = (Map) payload.get("parameters");

        // using data in json as jasper report data source from request
//        Collection<Map<String, ?>> rows = (Collection) payload.get("data");
//         List<Nst> requestVerify = nstRepository.findAll();
        List<Nst> nstQuiz = nstService.getByUserName(username);

        // test
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(nstQuiz);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createBy", "SpringBoot");
        parameters.put("requestData", new JRBeanCollectionDataSource(nstQuiz));

        // dynamic loading different jasper report by request
        InputStream jasperStream = resource.openStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        // fill parameters and data source to jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=" + fileName + ".pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
}