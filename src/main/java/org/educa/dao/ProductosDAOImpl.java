package org.educa.dao;

import generated.Producto;
import generated.Productos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.educa.entity.ProductoEntity;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.hssf.usermodel.HeaderFooter.file;

public class ProductosDAOImpl implements ProductosDAO {

    @Override
    public Productos readFile(File xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Productos.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (Productos) unmarshaller.unmarshal(xml);
    }

    @Override
    public void exportSummary(String path) throws IOException {
        File carpeta=new File(path);
        carpeta.mkdir();
    }

    private void setStyle(CellStyle cellStyle){
        cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);

        cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);

        cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);

        cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyle.setBorderRight(BorderStyle.THIN);

        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }

    @Override
    public void exportExcel(String path, String fileXml, List<ProductoEntity> productoEntities) throws IOException, JAXBException {
        String[] fileName=fileXml.split("/");
        String[] partesExtension=fileName[4].split("\\.");

        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("export");
        OutputStream outputStream=new FileOutputStream(path + "export_"+partesExtension[0]+".xlsx");

        Font blackFont= workbook.createFont();
        blackFont.setBold(true);

        CellStyle cellStyleLight= workbook.createCellStyle();
        cellStyleLight.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        setStyle(cellStyleLight);

        CellStyle cellStyleBoldLight= workbook.createCellStyle();
        setStyle(cellStyleBoldLight);
        cellStyleBoldLight.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyleBoldLight.setFont(blackFont);

        CellStyle cellStyleDark= workbook.createCellStyle();
        cellStyleDark.setFillForegroundColor(IndexedColors.BRIGHT_GREEN1.getIndex());
        setStyle(cellStyleDark);

        CellStyle cellStyleBoldDark= workbook.createCellStyle();
        cellStyleBoldDark.setFillForegroundColor(IndexedColors.BRIGHT_GREEN1.getIndex());
        setStyle(cellStyleBoldDark);
        cellStyleBoldDark.setFont(blackFont);

        Row rowHeader= sheet.createRow(0);
        String[] headers = {"Codigo", "NumSerie", "Precio", "Descuento", "Precio F.", "Costes envio",
                "Costes almacenaje", "Beneficio"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = rowHeader.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(cellStyleBoldLight);
        }


        int rowIndex = 1;
        for (ProductoEntity productoEntity : productoEntities) {
            Object[] columns={
                    productoEntity.getProducto().getCodigo(),
                    productoEntity.getProducto().getNumeroSerie(),
                    productoEntity.getProducto().getPrecio().doubleValue()+ "%",
                    productoEntity.getPrecioFinal().doubleValue(),
                    productoEntity.getProducto().getCostes().getCostesEnvio().doubleValue(),
                    productoEntity.getProducto().getCostes().getCostesAlmacenaje().doubleValue(),
                    productoEntity.getProfit().doubleValue(),
                    productoEntity.getProfit()
            };

            Row row = sheet.createRow(rowIndex++);
            for (int i = 0; i < columns.length; i++) {
                Cell cell=row.createCell(i);
                cell.setCellValue(columns[i].toString());
                if(rowIndex%2==0){
                    CellStyle cellStyle= i==0 ? cellStyleBoldDark : cellStyleDark;
                    cell.setCellStyle(cellStyle);
                }
                else {
                    CellStyle cellStyle= i==0 ? cellStyleBoldLight : cellStyleLight;
                    cell.setCellStyle(cellStyle);
                }
            }
        }
        //esta cosa me dejo un dia en estado de delirio, perdone profe tenia que ser honesto

        workbook.write(outputStream);
        workbook.close();
    }

}
