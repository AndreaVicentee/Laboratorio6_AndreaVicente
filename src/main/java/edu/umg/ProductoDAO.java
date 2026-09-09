package edu.umg;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private final MongoCollection<Document> collection;

    public ProductoDAO() {
        MongoDatabase database = ConexionMongo.getDatabase();
        this.collection = database.getCollection("productos");
    }

    public void insertar(Producto p) {
        Document doc = new Document("codigo", p.getCodigo())
                .append("nombre", p.getNombre())
                .append("categoria", p.getCategoria())
                .append("precio", p.getPrecio())
                .append("stock", p.getStock());
        collection.insertOne(doc);
        System.out.println("Producto insertado exitosamente: " + p.getNombre());
    }

    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        for (Document doc : collection.find()) {
            Producto p = new Producto(
                    doc.getString("codigo"),
                    doc.getString("nombre"),
                    doc.getString("categoria"),
                    doc.get("precio") != null ? doc.getDouble("precio") : 0.0,
                    doc.get("stock") != null ? doc.getInteger("stock") : 0
            );
            lista.add(p);
        }
        return lista;
    }

    public void actualizarPrecioYStock(String codigo, double nuevoPrecio, int nuevoStock) {
        collection.updateOne(
                Filters.eq("codigo", codigo),
                Updates.combine(
                        Updates.set("precio", nuevoPrecio),
                        Updates.set("stock", nuevoStock)
                )
        );
        System.out.println("Producto " + codigo + " actualizado.");
    }

    public void eliminar(String codigo) {
        collection.deleteOne(Filters.eq("codigo", codigo));
        System.out.println("Producto " + codigo + " eliminado.");
    }
}