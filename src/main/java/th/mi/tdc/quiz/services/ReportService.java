package th.mi.tdc.quiz.services;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;

public interface ReportService {
    ResponseEntity<byte[]> exportRequestVerifyRpt(HttpServletResponse response, String reportFormat) throws JRException, FileNotFoundException;
}
