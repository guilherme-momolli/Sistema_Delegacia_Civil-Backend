//package br.gov.pr.pc.dp.sistema_delegacia_civil.validators;
//
//import br.gov.pr.pc.dp.sistema_delegacia_civil.model.InqueritoPolicial;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class InqueritoExcelExporter {
//
//    public static ByteArrayInputStream exportToExcel(List<InqueritoPolicial> inqueritos) throws IOException {
//        String[] columns = {
//                "ID", "Número Justiça", "Ordem IP", "Data", "Peca", "Situação",
//                "Relator", "Investigado", "Vítima", "Delito", "Instituição"
//        };
//
//        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            Sheet sheet = workbook.createSheet("Inquéritos");
//
//            // Cabeçalho
//            Row headerRow = sheet.createRow(0);
//            for (int i = 0; i < columns.length; i++) {
//                Cell cell = headerRow.createCell(i);
//                cell.setCellValue(columns[i]);
//            }
//
//            int rowIdx = 1;
//            for (InqueritoPolicial ip : inqueritos) {
//                Row row = sheet.createRow(rowIdx++);
//                row.createCell(0).setCellValue(ip.getId());
//                row.createCell(1).setCellValue(ip.getNumeroJustica());
//                row.createCell(2).setCellValue(ip.getOrdemIp());
//                row.createCell(3).setCellValue(ip.getData().toString());
//                row.createCell(4).setCellValue(ip.getPeca());
//                row.createCell(5).setCellValue(ip.getSituacaoInquerito().toString());
//                row.createCell(6).setCellValue(ip.getRelator());
//                row.createCell(7).setCellValue(ip.getInvestigado());
//                row.createCell(8).setCellValue(ip.getVitima());
//                row.createCell(9).setCellValue(ip.getNaturezaDoDelito());
//                row.createCell(10).setCellValue(ip.getInstituicao().getNomeFantasia());
//            }
//
//            workbook.write(out);
//            return new ByteArrayInputStream(out.toByteArray());
//        }
//    }
//}
