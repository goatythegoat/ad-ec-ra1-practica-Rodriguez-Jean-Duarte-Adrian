package org.educa.dao;

import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.List;

public interface ProductosDAO {
    List<ProductoEntity> readFile(File xml) throws JAXBException, SAXException;
}
