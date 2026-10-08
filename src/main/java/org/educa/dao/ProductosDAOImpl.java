package org.educa.dao;

import generated.Producto;
import generated.Productos;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.educa.entity.ProductoEntity;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAOImpl implements ProductosDAO {

    @Override
    public List<ProductoEntity> readFile(File xml) throws JAXBException, SAXException {
        JAXBContext jaxbContext= JAXBContext.newInstance(Productos.class);
        Unmarshaller unmarshaller= jaxbContext.createUnmarshaller();
        Productos productos=(Productos) unmarshaller.unmarshal(xml);
        List<ProductoEntity> listado=new ArrayList<>();

        for (Producto producto : productos.getProducto()){
            ProductoEntity productoEntity=new ProductoEntity();
            productoEntity.setProducto(producto);
            listado.add(productoEntity);
        }

        return listado;
    }
}
