package br.gov.pr.pc.dp.sistema_delegacia_civil.services;

import br.gov.pr.pc.dp.sistema_delegacia_civil.configurations.FileStorageConfig;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class JasperReportService {

    private final FileStorageConfig fileStorageConfig;

    public byte[] gerarRelatorio(Map<String, Object> params,
                                 JRBeanCollectionDataSource dataSource,
                                 String nomeRelatorio) throws Exception {
        String caminhoRelatorio = fileStorageConfig.getReportDir() + "/DOCS/" + nomeRelatorio + ".jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoRelatorio, params, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

