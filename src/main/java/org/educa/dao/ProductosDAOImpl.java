package org.educa.dao;

import generated.Producto;
import generated.Productos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.poi.ss.usermodel.Workbook;
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

    @Override
    public void exportExcel(String path, String fileXml) {
        Workbook workbook=new XSSFWorkbook();

        //OutputStream outputStream=new FileOutputStream(path);
    }

}
