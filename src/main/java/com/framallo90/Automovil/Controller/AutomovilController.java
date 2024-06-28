package com.framallo90.Automovil.Controller;
import com.framallo90.Automovil.API.ApiAutomovilService;
import com.framallo90.Automovil.Model.Entity.Automovil;
import com.framallo90.Automovil.Model.Repository.AutomovilRepository;
import com.framallo90.Automovil.View.AutomovilView;
import com.framallo90.Excepciones.InvalidIdNotFound;
import com.framallo90.consola.Consola;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
public class AutomovilController {
    private final AutomovilRepository automovilRepository;
    private final AutomovilView automovilView;
    private final ApiAutomovilService apiAutomovilService;

    /**
     * Constructor del controlador de automóviles.
     * @param automovilRepository Repositorio de automóviles donde se almacenan los automóviles.
     * @param automovilView Vista que maneja la interfaz de usuario relacionada con los automóviles.
     */
    public AutomovilController(AutomovilRepository automovilRepository, AutomovilView automovilView) {
        this.automovilRepository = automovilRepository;
        this.automovilView = automovilView;
        this.apiAutomovilService = new ApiAutomovilService();
    }


    /**
     * Método privado para seleccionar una marca utilizando la API de automóviles.
     * @return Entrada de mapa que representa la pareja ID de marca y nombre de marca seleccionada.
     */
    private Map.Entry<Integer, String> seleccionarMarca() {
        try {
            Map<Integer, String> marcas = apiAutomovilService.obtenerMarcas();
            System.out.println("- Seleccione una Marca:");
            for (Map.Entry<Integer, String> entry : marcas.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            int marcaId = Consola.ingresarXInteger("el ID de la Marca");
            if (!marcas.containsKey(marcaId)) {
                Consola.soutAlertString("ID de Marca Invalido. Intente nuevamente.");
                return seleccionarMarca();
            }
            String marcaNombre = marcas.get(marcaId);
            return Map.entry(marcaId, marcaNombre);
        } catch (IOException e) {
            Consola.soutAlertString("Error al obtener las Marcas: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método privado para seleccionar un modelo utilizando la API de automóviles.
     * @param marcaId ID de la marca para la cual se desea seleccionar el modelo.
     * @return Entrada de mapa que representa la pareja ID de modelo y nombre de modelo seleccionado.
     */
    private Map.Entry<Integer, String> seleccionarModelo(int marcaId) {
        try {
            Map<Integer, String> modelos = apiAutomovilService.obtenerModelos(marcaId);
            System.out.println("- Seleccione un Modelo:");
            for (Map.Entry<Integer, String> entry : modelos.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            int modeloId = Consola.ingresarXInteger("el ID del Modelo");
            if (!modelos.containsKey(modeloId)) {
                Consola.soutAlertString("ID del Modelo Invalido. Intente nuevamente.");
                return seleccionarModelo(marcaId);
            }
            String modeloNombre = modelos.get(modeloId);
            return Map.entry(modeloId, modeloNombre);
        } catch (IOException e) {

            Consola.soutAlertString("Error al obtener los Modelos: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método privado para seleccionar un año utilizando la API de automóviles.
     * @param marcaId ID de la marca del automóvil.
     * @param modeloId ID del modelo del automóvil.
     * @return ID del año seleccionado.
     */
    private String seleccionarAno(int marcaId, int modeloId) {
        try {
            Map<String, String> anos = apiAutomovilService.obtenerAnos(marcaId, modeloId);
            System.out.println("- Seleccione un Año:");
            for (Map.Entry<String, String> entry : anos.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            while (true) {
                String anoId = Consola.ingresarXStringSimple("el ID del Año");
                if (anos.containsKey(anoId)) {
                    return anoId;
                } else {
                    Consola.soutAlertString("Opción Inválida. Reintentar!.");
                }
            }
        } catch (IOException e) {
            Consola.soutAlertString("Error al obtener los Años: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método privado para obtener el precio de un automóvil utilizando la API de automóviles.
     * @param marcaId ID de la marca del automóvil.
     * @param modeloId ID del modelo del automóvil.
     * @param anoId ID del año del automóvil.
     * @return Precio del automóvil.
     */
    private Double obtenerPrecio(int marcaId, int modeloId, String anoId) {
        try {
            return apiAutomovilService.obtenerPrecio(marcaId, modeloId, anoId);
        } catch (IOException e) {
            Consola.soutAlertString("Error al obtener el Precio: " + e.getMessage());
            return null;
        }
    }

    /**
     * Método público para agregar un automóvil al stock utilizando la API de automóviles.
     */
    public void agregarAutomovilAlStock() {
        Map.Entry<Integer, String> marca = seleccionarMarca();
        if (marca == null) return;

        Map.Entry<Integer, String> modelo = seleccionarModelo(marca.getKey());
        if (modelo == null) return;

        String anoId = seleccionarAno(marca.getKey(), modelo.getKey());
        if (anoId == null) return;

        Double precio = obtenerPrecio(marca.getKey(), modelo.getKey(), anoId);
        if (precio == null) return;

        String patente = automovilView.ingresoPatente();
        int anio = Integer.parseInt(anoId.split("-")[0]);

        Automovil nuevo = new Automovil(marca.getValue(), modelo.getValue(), precio, patente, anio);
        automovilRepository.add(nuevo);
    }
    /**
     * Método público para borrar un automóvil del stock utilizando su ID.
     */
    public void borrarAutomovilEnStock() {
        try {
            automovilRepository.remove(Consola.ingresarXInteger("el ID del Vehiculo"));
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }

    /**
     * Elimina un automóvil del stock basado en su ID.
     *
     * @param id El ID del automóvil que se desea eliminar del stock.
     */
    public void borrarAutomovilEnStockPorId(Integer id) {
        try {
            automovilRepository.remove(id);
        } catch (InvalidIdNotFound e) {
            Consola.soutAlertString(e.getMessage());
        }
    }
    /**
     * Método público para mostrar todos los automóviles en el stock.
     */
    public void mostrarAutomovilesEnStock() {
        try {
            automovilView.mostrarAutomoviles(automovilRepository.getAutomovilList());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Método público que muestra el menú de administración de automóviles.
     */
    public void menuAutomovilAdmin() {
        int eleccion;

        while (true) {
            automovilView.printMenuAutomovilAdmin();
            eleccion = Consola.ingresarXInteger("una opcion del Menu Automovil");

            switch (eleccion) {
                case 0: // Salir
                    return;
                case 1: // Agregar
                    agregarAutomovilAlStock();
                    break;
                case 2: // Remover
                    mostrarAutomovilesEnStock();
                    borrarAutomovilEnStock();
                    break;
                case 3: // Mostrar detalle de automóvil por ID
                    mostrarAutomovilesEnStock();
                    try {
                        this.automovilRepository.find(Consola.ingresarXInteger("id del automovil"));
                    } catch (InvalidIdNotFound e) {
                        Consola.soutAlertString(e.getMessage());
                    }

                    break;
                case 4: // Ver lista filtrada
                    automovilView.buscarAutomovilesXFiltro(automovilRepository.getAutomovilList());

                    break;


                default: // Opción no reconocida
                    Consola.soutAlertString("El dato es Invalido!. Reintentar.");
                    break;
            }
        }
    }
    public void buscarAutomovilesXFiltro() {
        Predicate<Automovil>[] arrayCondiciones = new Predicate[5];
        arrayCondiciones[0] = null;arrayCondiciones[1] = null;
        arrayCondiciones[2] = null;arrayCondiciones[3] = null;
        arrayCondiciones[4] = null;

        String[] arrayTagsMostrar = new String[5];
        //tendremos un maximo de 5 filtros unicos

        int cont = 0;
        int opc = -1;
        mostrarAutomovilesEnStock();
        do{
            System.out.println("LISTA DE AUTOMOVILES " +
                    "\n1. Filtrar por marca" +
                    "\n2. Filtrar por modelo" +
                    "\n3. Filtrar por año" +
                    "\n4. Establecer precio máximo" +
                    "\n5. Establecer precio mínimo"
            );
            if(cont>0){System.out.println("6. Quitar filtro");}
            System.out.println("0. Volver");

            opc = Consola.ingresarXInteger("opción");

            //se agrega/cambia/saca uno de los filtros
            switch (opc){
                case 1:
                    String marca = automovilView.ingresoMarca();
                    arrayCondiciones[0] = m -> m.getMarca().toLowerCase().contains(marca.toLowerCase());
                    arrayTagsMostrar[0] = "Marca " + marca + " | ";
                    break;
                case 2:
                    String modelo = automovilView.ingresoModelo();
                    arrayCondiciones[1] = m -> m.getModelo().toLowerCase().contains(modelo.toLowerCase());
                    arrayTagsMostrar[1] = "Modelo " + modelo + " | ";
                    break;
                case 3:
                    Integer anio = automovilView.ingresoAnio();
                    arrayCondiciones[2] = (a -> a.getAnio().equals(anio));
                    arrayTagsMostrar[2] = "Anio " + anio.toString() + " | ";
                    break;
                case 4:
                    Double max = Consola.ingresarXdouble("máximo");
                    arrayCondiciones[3] = p-> p.getPrecio() <= max;
                    arrayTagsMostrar[3] = "Precio hasta $" + max + " | ";
                    break;
                case 5:
                    Double min = Consola.ingresarXdouble("mínimo");
                    arrayCondiciones[4] = p -> p.getPrecio() >= min;
                    arrayTagsMostrar[4] = "Precio desde $" + min + " | ";
                    break;
                case 6:
                    if(cont>0){
                        System.out.println(
                                "\n1 - Filtro marca" +
                                        "\n2 - Filtro modelo" +
                                        "\n3 - Filtro año" +
                                        "\n4 - Filtro precio máximo" +
                                        "\n5 - Filtro precio mínimo" +
                                        "\n0 - Atras"
                        );
                        Integer sacar = Consola.ingresarXInteger("opcion");
                        if(6 > sacar && sacar>0){
                            arrayCondiciones[sacar-1] = null;
                        }
                    }else{
                        System.out.println("Opción no reconocida");
                    }
                    break;
                case 0:
                    //se sale
                    break;
                default:
                    opc = -1;
                    System.out.println("Opción no reconocida");
                    break;
            }


            //verifico el array de filtros y si tengo los paso a una lista para usarlos
            List<Predicate<Automovil>> listaFiltros = new ArrayList<>();
            for(Predicate<Automovil> p:arrayCondiciones){
                if(p != null){
                    listaFiltros.add(p);
                }
            }

            cont = listaFiltros.size();

            if(opc != 0){
                if(cont >0){///si se usan filtros
                    ///muestra los filtros que se usan
                    System.out.println("Filtros:");
                    System.out.printf("\033[36m | ");
                    for (int i = 0;i<5;i++){
                        if(arrayCondiciones[i] != null){
                            System.out.printf(arrayTagsMostrar[i]);
                        }
                    }
                    System.out.println("\u001B[0m");



                    //procesa los datos y muestra si hay coincidencias
                    Integer coincidencias = 0;
                    if (cont == 1) {
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .peek(System.out::println)
                                .count();

                    } else if( cont == 2){
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .peek(System.out::println)
                                .count();
                    } else if(cont == 3) {
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .filter(listaFiltros.get(2))
                                .peek(System.out::println)
                                .count();

                    } else if(cont == 4) {
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(listaFiltros.get(0))
                                .filter(listaFiltros.get(1))
                                .filter(listaFiltros.get(2))
                                .filter(listaFiltros.get(3))
                                .peek(System.out::println)
                                .count();
                    } else if (cont == 5){
                        coincidencias = (int) this.automovilRepository.getAutomovilList().stream()
                                .filter(arrayCondiciones[0])
                                .filter(arrayCondiciones[1])
                                .filter(arrayCondiciones[2])
                                .filter(arrayCondiciones[3])
                                .filter(arrayCondiciones[4])
                                .peek(System.out::println)
                                .count();
                    }


                    ///avisa que no hay coincidencias
                    if(coincidencias == 0){
                        System.out.println("No hay automoviles que coincidan con los filtros");
                    }


                }else{
                    ///muestra la lista resultante sin filtros
                    if(this.automovilRepository.isEmpty()){
                        System.out.println("No hay automoviles");
                    }else{
                        this.automovilRepository.getAutomovilList().forEach(System.out::println);
                    }
                }
            }

        }while(opc != 0);
    }
    public Automovil find(Integer id) throws InvalidIdNotFound{
        return this.automovilRepository.find(id);
    }

    /**
     * Método público que muestra el menú de vendedor de automóviles.
     */
    public void menuAutomovilVendedor() {
        int eleccion;

        while (true) {
            automovilView.printMenuAutomovilVendedor();
            eleccion = Consola.ingresarXInteger("una opcion del Menu Automovil");

            switch (eleccion) {
                case 0: // Salir
                    return;
                case 1: // Mostrar detalle de automóvil por ID
                    mostrarAutomovilesEnStock();
                    try {
                        this.automovilRepository.find(Consola.ingresarXInteger("id del automovil"));
                    } catch (InvalidIdNotFound e) {
                        Consola.soutAlertString(e.getMessage());
                    }
                    break;
                case 2: // Ver stock de automóviles
                    mostrarAutomovilesEnStock();
                    break;
                case 3: // Ver lista filtrada
                    automovilView.buscarAutomovilesXFiltro(automovilRepository.getAutomovilList());
                    break;
                default: // Opción no reconocida
                    Consola.soutAlertString("El dato es Invalido!. Reintentar.");
                    break;
            }
        }
    }

    /**
     * Método público que verifica si el repositorio de automóviles está vacío.
     * @return true si el repositorio está vacío, false en caso contrario.
     */
    public boolean isRepoEmpty() {
        return this.automovilRepository.isEmpty();
    }
}