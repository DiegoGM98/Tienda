package Tienda;

import java.util.ArrayList;
import java.util.Scanner;

public class TiendaElectronica {
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TiendaElectronica tienda = new TiendaElectronica();

        // Agregar productos iniciales
        tienda.agregarProducto(new Producto("Laptop", "Laptop de alta gama", 1200.00, 10, "Electrónica"));
        tienda.agregarProducto(new Producto("Smartphone", "Smartphone con pantalla de 5 pulgadas", 800.00, 20, "Electrónica"));

        tienda.iniciar();
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Mostrar todos los productos");
            System.out.println("2. Buscar producto por nombre o categoría");
            System.out.println("3. Agregar nuevo producto");
            System.out.println("4. Modificar información de un producto");
            System.out.println("5. Eliminar un producto");
            System.out.println("6. Realizar una compra");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    mostrarProductos();
                    break;
                case 2:
                    buscarProducto();
                    break;
                case 3:
                    agregarProductoDesdeConsola();
                    break;
                case 4:
                    modificarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 6:
                    realizarCompra();
                    break;
                case 7:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void mostrarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles");
        } else {
            System.out.println("Productos disponibles:");
            for (Producto producto : productos) {
                producto.mostrarInformacion();
            }
        }
    }

    private static void buscarProducto() {
        System.out.print("Ingrese el nombre o la categoría del producto a buscar: ");
        String criterioBusqueda = scanner.nextLine().toLowerCase();
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getNombre().toLowerCase().contains(criterioBusqueda) ||
                    producto.getCategoria().toLowerCase().contains(criterioBusqueda)) {
                producto.mostrarInformacion();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron productos con el criterio de búsqueda especificado");
        }
    }

    static void agregarProducto(Producto producto) {
        productos.add(producto);
        System.out.println("Producto agregado correctamente");
    }

    private static void agregarProductoDesdeConsola() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese el stock del producto: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer de entrada
        System.out.print("Ingrese la categoría del producto: ");
        String categoria = scanner.nextLine();

        Producto nuevoProducto = new Producto(nombre, descripcion, precio, stock, categoria);
        agregarProducto(nuevoProducto);
    }

    private static void modificarProducto() {
        mostrarProductos();
        System.out.print("Ingrese el nombre del producto a modificar: ");
        String nombreProducto = scanner.nextLine();
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                System.out.println("¿Qué desea modificar?");
                System.out.println("1. Descripción");
                System.out.println("2. Precio");
                System.out.println("3. Stock");
                System.out.println("4. Categoría");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        System.out.print("Ingrese la nueva descripción: ");
                        String nuevaDescripcion = scanner.nextLine();
                        producto.setDescripcion(nuevaDescripcion);
                        break;
                    case 2:
                        System.out.print("Ingrese el nuevo precio: ");
                        double nuevoPrecio = scanner.nextDouble();
                        producto.setPrecio(nuevoPrecio);
                        break;
                    case 3:
                        System.out.print("Ingrese el nuevo stock: ");
                        int nuevoStock = scanner.nextInt();
                        producto.setStock(nuevoStock);
                        break;
                    case 4:
                        System.out.print("Ingrese la nueva categoría: ");
                        String nuevaCategoria = scanner.nextLine();
                        producto.setCategoria(nuevaCategoria);
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
                encontrado = true;
                System.out.println("Producto modificado correctamente");
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró el producto especificado");
        }
    }

    private static void eliminarProducto() {
        mostrarProductos();
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String nombreProducto = scanner.nextLine();
        boolean eliminado = false;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                productos.remove(producto);
                eliminado = true;
                System.out.println("Producto eliminado correctamente");
                break;
            }
        }
        if (!eliminado) {
            System.out.println("No se encontró el producto especificado");
        }
    }

    private static void realizarCompra() {
        mostrarProductos();
        System.out.print("Ingrese el nombre del producto que desea comprar: ");
        String nombreProducto = scanner.nextLine();
        boolean encontrado = false;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
                if (producto.getStock() > 0) {
                    System.out.print("Ingrese la cantidad que desea comprar: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer de entrada

                    if (cantidad <= producto.getStock()) {
                        producto.setStock(producto.getStock() - cantidad);
                        System.out.println("Compra realizada correctamente");
                    } else {
                        System.out.println("No hay suficiente stock disponible para realizar esta compra");
                    }
                } else {
                    System.out.println("El producto está agotado");
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró el producto especificado");
        }
    }
}