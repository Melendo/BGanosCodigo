# Código B-Ganos

# 1 Descripción General
## 1.1 Perspectiva de producto
### 1.1.1 Contexto De Implantación
BGanos es una plataforma integral de gestión transaccional de jardines botánicos recreacionales. Al
ser una distribución local, no requiere de acceso a internet para su uso, únicamente de un entorno
compatible con java.

La capa de recursos que sostiene al sistema está implementada de manera relacional y albergada
localmente, lo que nos permite gestionar la información asociada a productos, empleados de caja,
ventas, entre otros.

El usuario dispondrá de una interfaz gráca para interactuar con el sistema de manera sencilla.
Otros aspectos de interés acerca del producto son:

•Interfaz del sistema → La aplicación emplea una base de datos relacional externa, donde
se almacenan todos los datos en su contexto.

• Interfaz del usuario → La aplicación de escritorio utiliza las interfaces
proporcionadas por la biblioteca gráca Swing de Java.

• Interfaz hardware → No presenta ningún tipo de limitación hardware, por lo que la aplicación
podría ejecutarse en cualquier tipo de equipo informático.

• Interfaz software → Para ejecutar la aplicación se necesita un equipo que tenga instalada la
máquina virtual de Java (JVM).

• Interfaz de comunicación → La aplicación se conectará con una base de datos MySQL
externa.

• Memoria → Se utilizan solo los recursos imprescindibles, que serán bastante
reducidos.

• Requisitos de adaptación →No se precisa un proceso de instalación; únicamente es necesaria la
descarga.

## 1.2 Funciones del producto
BGanos, al tratarse de una aplicación de gestión, proporciona al usuario una serie de herramientas
para la gestión del invernadero como para la tienda asociada a este. A continuación se describirán las
funcionalidades de cada entidad de forma breve, separándolas por módulos.

### 1.2.1 Gestión del invernadero
● Factura

○ Abrir Factura: Abre una factura para añadir entradas al carrito.

○ Cerrar Factura: Procesa la factura, impidiendo que se modique.

○ Modicar Factura: Modica uno o varios atributos de la factura.

○ Mostrar Factura por ID: Muestra una factura en función de id.

○ Listar Facturas: Muestra todas las facturas.

○ Devolver Factura: Gestiona la devolución de la factura, devolviendo las entradas.

○ Añadir Entradas a Factura: Añade una o varias entradas a la factura.

○ Quitar Entradas de Factura: Elimina una o varias entradas de la factura.


● Entrada

○ Alta Entrada: Añade una entrada al sistema.

○ Baja Entrada: Elimina una entrada del sistema.

○ Modicar Entrada: Modica uno o varios atributos de la entrada.

○ Mostrar Entrada por Id: Muestra la entrada por su id.

○ Listar Entradas: Lista la información de todas las entradas.

○ Listar Entradas de Invernadero: Muestra todas las entradas asociadas a un
invernadero.


● Invernadero

○ Alta Invernadero: Añade un invernadero al sistema.

○ Baja Invernadero: Elimina un invernadero del sistema.

○ Modicar Invernadero: Modica uno o varios atributos del invernadero.

○ Listar Invernaderos: Lista todos los invernaderos de la BD.

○ Mostrar Invernadero por ID: Muestra el invernadero por su id.

○ Listar Invernaderos por Sistema de Riego: Lista invernaderos que utilizan un
Sistema de Riego

○ Vincular sistema de riego a Invernadero: Añade la relación entre un Sistema de
Riego y un Invernadero.

○ Desvincular sistema de riego de Invernadero: Elimina la relación entre un Sistema
de riego y un Invernadero.

○ Listar tres fechas con más Entradas vendidas de un Invernadero


● Sistema de riego

○ Alta Sistema de riego: Añade un sistema de riego al sistema.

○ Baja Sistema de riego: Elimina un sistema de riego del sistema.

○ Modicar Sistema de riego: Modica uno o varios atributos del sistema de riego.

○ Mostrar Sistema de Riego por ID: Muestra el sistema de riego por si ID

○ Listar Sistemas de Riego de Invernadero: Muestra los sistemas de riego que
implementa un invernadero.

○ Listar Sistemas de Riego por Fabricante: Muestra todos los sistemas de riego
fabricados por un fabricante.

○ Listar Sistemas de Riego: Muestra todos los sistemas de riego de la base de datos.


● Fabricante

○ Alta Fabricante: Añade un fabricante al sistema.

○ Baja Fabricante: Elimina un fabricante del sistema.

○ Modicar Fabricante: Modica uno o varios atributos de un fabricante.

○ Listar Fabricantes: Lista los datos de todos los fabricantes registrados en el sistema.

○ Mostrar Fabricante por ID: Muestra la información de un fabricante por su id.

○ Listar Fabricantes por Tipo: Muestra una lista de los fabricantes según su tipo
(Local o Extranjero).

○ Lista Información de Fabricantes de Sistemas de Riego de un invernadero


● Planta

○ Alta Planta: Añade una planta al sistema.

○ Baja Planta: Elimina una planta del sistema.

○ Modicar Planta: Modica uno o varios atributos de la planta.

○ Listar Plantas: Lista todas las plantas de la BD.

○ Mostrar Planta por ID: Muestra la planta por su id.

○ Listar Plantas por tipo: Muestra la planta en función a su tipo(Frutal o No Frutal).

○ Listar Plantas de Invernadero: Lista todas las plantas asociadas a un invernadero.


### 1.2.2 Gestión de la tienda del invernadero

● Proveedor

○ Alta Proveedor: Añade un proveedor al sistema.

○ Baja Proveedor: Elimina un proveedor del sistema.

○ Modicar Proveedor: Modica uno o varios atributos del proveedor.

○ Listar Proveedores: Muestra la información del proveedor.

○ Mostrar Proveedor por ID: Muestra el proveedor por su id.

○ Listar Proveedores por Marca: Lista todos los proveedores asociados a una marca.

○ Vincular Marca a Proveedor: Añade la relación de marca y proveedor

○ Desvincular Marca de Proveedor Elimina la relación de marca y proveedor

● Marca

○ Alta Marca: Añade una marca al sistema.

○ Baja Marca: Elimina una marca del sistema.

○ Modicar Marca: Modica uno o varios atributos de la marca.

○ Listar Marcas: Muestra una lista de todas las marcas.

○ Mostrar Marca por ID: Muestra la marca por su id.

○ Listar Marcas por Proveedor: Lista todos las marcas asociadas a un proveedor.

● Turno

○ Alta Turno: Añade un nuevo turno al sistema.

○ Baja Turno: Da de baja un turno del sistema.

○ Modicar Turno: Modica un turno existente.

○ Mostrar Turno por ID: Muestra la información de un turno usando su
identicador.

○ Listar Turnos: Lista todos los turnos en la base de datos.

○ Obtener nómina de un turno: Obtiene la suma de todas las nóminas de un turno.

● Venta

○ Alta Venta: Añade una venta al sistema.

○ Baja Venta: Da de baja una venta del sistema.

○ Modicar Venta: Modica una venta existente.

○ Mostrar Venta por ID: Muestra la información de una venta usando su id.

○ Listar Ventas: Muestra todas las ventas en la base de datos.

○ Listar Ventas por empleado de caja: Lista todas las ventas hechas por un empleado.

○ Añadir producto: Añada un producto a una venta.

○ Quitar producto: Quita un producto de una venta.

○ Devolver Venta: Gestiona la devolución de una venta, devolviendo los productos a
la tienda.

○ Procesar Venta: Se retiran los productos del inventario de la tienda y se procesa el
cobro al cliente.

○ Listar Ventas: Muestra todas las ventas en la base de datos.

○ Obtener Ventas por turno: Obtiene un listado de todas las ventas realizadas en un
turno especíco.

● Producto

○ Alta Producto: Añade un producto al sistema.

○ Baja Producto: Da de baja un producto del sistema.

○ Modicar Producto: Modica un producto existente.

○ Listar Productos: Lista todos los productos de la BD.

○ Mostrar Producto por ID: Muestra la información de una venta usando su id.

○ Listar Productos por Venta: Muestra todos los productos de una venta.

○ Listar Productos por tipo: Muestra todos los productos de un tipo.

○ Listar Productos por Marca: Muestra todos los productos de una marca.

● Empleado de caja

○ Alta Empleado de caja: Añade un empleado de caja al sistema.

○ Baja Empleado de caja: Elimina un empleado de caja al sistema.

○ Modicar Empleado de caja: Modica un empleado de caja existente.

○ Listar Empleado de caja por nombre: Muestra la información de un empleado de
caja buscado por su nombre.

○ Listar Empleado de caja por ID: Muestra la información de un empleado de caja
buscado por su identicador.

○ Listar Empleados por turno: Muestra una lista con todos los empleados de un
turno.

○ Listar Empleados de caja: Muestra todos los empleados de caja en la base de datos.
