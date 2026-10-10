package org.educa.service;

import generated.Costes;
import generated.Producto;
import generated.Productos;
import jakarta.xml.bind.JAXBException;
import org.apache.commons.math3.stat.descriptive.summary.Sum;
import org.educa.dao.ProductosDAO;
import org.educa.dao.ProductosDAOImpl;
import org.educa.entity.ProductoEntity;
import org.educa.entity.SummaryEntity;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private final ProductosDAO productosDAO = new ProductosDAOImpl();

    public List<ProductoEntity> readFile(String fileXml) throws JAXBException {
        //TODO: Implementar
        List<ProductoEntity> listado = new ArrayList<>();
        Productos productos = null;
        productos = productosDAO.readFile(new File(fileXml));
        BigDecimal precioFinal;
        BigDecimal coste;
        BigDecimal beneficio;
        BigDecimal descuento;
        BigDecimal resta;

        return listado;
    }

    public void exportSummary(String path, String fileXml) throws JAXBException, IOException {
        //TODO: Implementar
    }

    public void exportExcel(String path, String fileXml) throws JAXBException, IOException, ParseException {
        //TODO: Implementar
    }
}
