package edu.umg;

import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Probando Conexion y CRUD en MongoDB Atlas ===");

        ProductoDAO dao = new ProductoDAO();

        Producto p1 = new Producto("P001", "Laptop HP", "Electronica", 4500.00, 10);
        dao.insertar(p1);

        System.out.println("\n--- Lista de Productos en la Base de Datos ---");
        List<Producto> productos = dao.obtenerTodos();
        for (Producto p : productos) {
            System.out.println(p);
        }

        dao.actualizarPrecioYStock("P001", 4200.00, 8);

        System.out.println("\n--- Lista despues de actualizar ---");
        for (Producto p : dao.obtenerTodos()) {
            System.out.println(p);
        }

        System.out.println("\n=== Prueba completada exitosamente ===");
    }
}