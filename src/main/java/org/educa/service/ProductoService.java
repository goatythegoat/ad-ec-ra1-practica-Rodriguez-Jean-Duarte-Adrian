package org.educa.service;

import jakarta.xml.bind.JAXBException;
import org.educa.dao.ProductosDAO;
import org.educa.dao.ProductosDAOImpl;
import org.educa.entity.ProductoEntity;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class ProductoService {

    private final ProductosDAO productosDAO=new ProductosDAOImpl();
    public List<ProductoEntity> readFile(String fileXml) throws JAXBException {
        //TODO: Implementar

        try {
            List<ProductoEntity> listado=(List<ProductoEntity>) productosDAO.readFile(new File(fileXml));
            return listado;
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportSummary(String path, String fileXml) throws JAXBException, IOException {
        //TODO: Implementar

    }

    public void exportExcel(String path, String fileXml) throws JAXBException, IOException, ParseException {
        //TODO: Implementar
    }
}
