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

        for (Producto producto : productos.getProducto()) {
            ProductoEntity productoEntity = new ProductoEntity();
            productoEntity.setProducto(producto);
            descuento = producto.getDescuento();
            resta = (producto.getPrecio().multiply(descuento)).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            precioFinal = (producto.getPrecio().subtract(resta));
            productoEntity.setPrecioFinal(precioFinal);
            coste = producto.getCostes().getCostesAlmacenaje().add(producto.getCostes().getCostesEnvio());
            productoEntity.setCost(coste);
            beneficio = precioFinal.subtract(coste);
            productoEntity.setProfit(beneficio);

            listado.add(productoEntity);
        }

        return listado;
    }

    public void exportSummary(String path, String fileXml) throws JAXBException, IOException {
        //TODO: Implementar

        List<ProductoEntity> listado=readFile(fileXml);
        productosDAO.exportSummary(path);
        File xmlFile=new File(fileXml);

        SummaryEntity summaryEntity=new SummaryEntity();
        String[] fileName=fileXml.split("/");
        BigDecimal sum=BigDecimal.valueOf(0);
        int numProductos=0;

        String[] partesExtension=fileName[4].split("\\.");
        String[] partesTotal=partesExtension[0].split("_");

        for(ProductoEntity productoEntity: listado){
            sum=sum.add(productoEntity.getProfit());
            numProductos++;

        }
        summaryEntity.setName(partesTotal[1]);
        summaryEntity.setFileName(partesExtension[0]);
        summaryEntity.setNumberOfProducts(numProductos);
        summaryEntity.setTotalProfit(sum);
        summaryEntity.setFileAbsolutePath(xmlFile.getAbsolutePath());
        summaryEntity.setFileSize(xmlFile.length());

        File txt=new File(path + "result_"+partesExtension[0]+".txt");
        PrintWriter writer=new PrintWriter(txt);
        txt.createNewFile();
        writer.print(summaryEntity.toPrint());
        writer.close();
    }

    public void exportExcel(String path, String fileXml) throws JAXBException, IOException, ParseException {
        //TODO: Implementar
    }
}
