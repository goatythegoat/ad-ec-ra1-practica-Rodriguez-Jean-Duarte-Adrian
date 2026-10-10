package org.educa.dao;

import generated.Productos;
import jakarta.xml.bind.JAXBException;
import org.educa.entity.ProductoEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ProductosDAO {
    /**
     * Reads an XML file and deserializes it using JAXB
     * @param xml XML file path
     * @return deserialized object containing the file information
     * @throws JAXBException if an error occurs during the XML unmarshalling
     */
    Productos readFile(File xml) throws JAXBException;
    /**
     * Create the necessary directory for the export
     * @param path path of the directory to be created
     * @throws IOException if an error occurs while creating the directory
     */
    void exportSummary(String path) throws IOException;
    /**
     * Export the product list to an Excel file
     * @param path folder path
     * @param fileXml XML file path
     * @param productoEntities list of products
     * @throws IOException if an error occurs while writing in the file
     * @throws JAXBException if an error occurs while processing the data
     */
    void exportExcel (String path, String fileXml, List<ProductoEntity> productoEntities) throws IOException, JAXBException;
}
