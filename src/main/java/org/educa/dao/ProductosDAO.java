package org.educa.dao;

import generated.Productos;
import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProductosDAO {
    Productos readFile(File xml) throws JAXBException;
    void exportSummary(String path) throws IOException;
    void exportExcel (String path, String fileXml, List<ProductoEntity> productoEntities) throws IOException, JAXBException;
}
