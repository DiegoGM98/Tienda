package Tienda;

public class Main {
    public static void main(String[] args) {
        TiendaElectronica tienda = new TiendaElectronica();

        // Agregar productos iniciales
        tienda.agregarProducto(new Producto("Laptop", "Laptop de alta gama", 1200.00, 10, "Electrónica"));
        tienda.agregarProducto(new Producto("Smartphone", "Smartphone con pantalla de 5 pulgadas", 800.00, 20, "Electrónica"));

        tienda.iniciar();
    }
}


