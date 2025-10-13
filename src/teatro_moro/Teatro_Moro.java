/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatro_moro;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author JavCalabrano
 */
public class Teatro_Moro {
    /**
     * @param args the command line arguments
     */
    //Variables estaticas - para modificaciones mayores
    
     
    static String teatroNombre = "Teatro Moro", inputString, nombre;
    static int valorGeneral=5000, valorPalco=20000, valorVip=25000, valorPlateaAlta=15000, valorPlateaBaja=10000;    //valores
    static int nGeneral=50, nVip=10, nPlateaBaja=20, nPlateaAlta=20, nPalco=20;                    //numero de entradas disponibles
    
    static String nombreCliente[] = new String[120]; //arreglos para almacenar datos de ventas mas permanentes    
    static String usuario[] = new String[120];
    static String codigoAsiento[] = new String[120];
    static String sector[] = new String[120];
    static boolean comprado[] = new boolean[120];
    static int valor[] = new int[120];
    static int usuarioDescuento[] = new int[120];
    
    static List<String> ventas = new ArrayList<>();
    
    static boolean ciclo=false, encontrado=false;
    static int nro, menu, recaudacion=0; //eleccion del switch case
    static double total;
    
    static String codigo,  sectorTxt;
    static int inputInt, filaNum, cantidad,  pago, timerIndice;
    static double descuento, descuentoTotal, descuentoMenor=0.05, descuentoMujer=0.07, descuentoEstudiante=0.25, descuentoTEdad=0.30, vuelto;
    static char charFila;
    
    static Scanner inputTc = new Scanner(System.in);//Activar el teclado     
    static Timer[] timer = new Timer[10];
    
    //Sector para declarar las funciones y aplicarlas en el main
    
    
    
    public static void main(String[] args) {
            
        do{ //ciclo entero de la visita al Teatro
            encontrado=false; //reinicio busquedas 
            System.out.println("Bienvenido a "+teatroNombre+"\n"
                    + "\n"
                    + "¿Qué desea hacer hoy?"
                    + "\n");
            imprimirMenu();
            System.out.println("\nIngrese una opción valida"); // pide opcion del menu principal para continuar
            do { 
                
                inputString=inputTc.nextLine();   //entrada selección menú
                validacionNro();
                validarMenu();
            } while (!ciclo); // validaciones de ingreso de datos            
            switch(menu) {
                case 1 -> casoUno();
                case 2 -> casoDos();
                case 3 -> casoTres();
                case 4 -> casoCuatro();
                case 5 -> casoCinco();
                
                case 6 -> casoSeis();                
                case 7 -> menu=0;
                default -> errorValidacion();
            }
        } while (menu!=0);
    }
            
            
    static private void imprimirMenu () {
        System.out.println("1. Revisar asientos disponibles\n"
            + "2. Comprar entradas\n"
            + "3. Revisar / pagar reserva\n"                    
            + "4. Revise sus entradas\n"
            + "5. Devolución de entradas\n"
            + "\n"
            + "6.Registro de ventas\n"
            + "7. Terminar visita");  
    }
    
    static void errorValidacion () {
        System.out.println("Por favor ingrese una opción valida");  
    }       
    
    static private void mostrarAsientosDisponibles(){
        stockDisponible();
        mapaAsientos();
    }
    
    static private void stockDisponible(){
        System.out.println("====Entradas disponibles====\n" //Tabla de stock de entradas
            + "Tipo                   | Valor | Disponibles"
            + "\n1. VIP         (A)     | "+valorVip+" |      "+nVip  
            + "\n2. Palco       (B - C) | "+valorPalco+" |      "+nPalco
            + "\n3. Platea Alta (D - E) | "+valorPlateaAlta+" |      "+nPlateaAlta
            + "\n4. Platea Baja (F - G) | "+valorPlateaBaja+" |      "+nPlateaBaja
            + "\n5. General     (H - L) | "+valorGeneral+"  |      "+nGeneral); 
        System.out.println("");
    }    
        
    static private void mapaAsientos() {
        char fila='@';
        String estado;
        System.out.println("   ---- MAPA DE ASIENTOS ----\n" //impresion de asientos Disponibles/Ocupados
                        + "\n  1  2  3  4  5  6  7  8  9  10");
        for (int i=0 ; i<codigoAsiento.length ; i++) { //cantidad codigos maxima en combinaciones 
            if(i%10==0){ //valida 10 conteos: hace un salto de línea y avanza una fila
                System.out.println("");                
                fila++;
                System.out.print(fila);
            }                        
                estado = (comprado[i] ? "[X]" : codigoAsiento[i]!=null ? "[R]" : "[ ]"); //comparación de estado para impresión
                System.out.print(estado);            
            }
        System.out.println("");        
        }
    
    static public void validacionNro () {
        boolean paso=false;
        while (!paso){                        
            try{
                nro = Integer.parseInt(inputString); //convierte string a nro, si falla error
                paso=true;                
            }catch(NumberFormatException e) {                
                errorValidacion();
                paso=false;
                inputString=inputTc.nextLine(); 
                continue;                                
            }
        }
    }
    
    static public boolean inputStringVacio () {

        if (inputString.isEmpty()){
            return true;
        }
        return false;
    }
    
    static public void validarMenu (){
        if (nro<1 || nro > 7) {
            errorValidacion();
        }else{
            menu=nro;
            ciclo=true;
        }        
    }
    
    static public void casoUno () {
        mostrarAsientosDisponibles();
    }
    
    static public void casoDos () {
        int indice;         
        
        guardarNombre();
        mapaAsientos();
        System.out.println("");
        System.out.println("Escoja los asientos de su preferencia");
        System.out.println("Para terminar elección solo presione Enter"); 
        ciclo=false; // dclaracion para poner en marcha el bucle de reservas        
        while(!ciclo){
        verificarCodigoAsiento();   // pasa aqui y reserva en primer lugar el asiento en array codigo asiento                
        if (codigo.isEmpty()) {  //verifica ingreso vacio
            buscarCliente();           
            if (!encontrado){  //si escogio asientos antes sigue con ciclo de compra
                System.out.println("Volviendo menu principal");
                break;
                
            }else {
                ciclo=true;
            }                         
        }                       
        for (int j = 0 ; j < usuario.length; j++ ){
            if (nombre.equals(nombreCliente[j])){
                boolean paso = false;
                while(!paso) {
                    System.out.println("Ingrese nombre del usuario asiento "+codigoAsiento[j]);            
                    inputString=inputTc.nextLine();                  
                    if (inputString.isEmpty()){
                        continue;
                    }
                usuario[j]=inputString;                     
                paso=true;
                }
                asignacionDescuentoEspecial();  
                total = (j < 10 ? valorVip : j>19 && j < 30 ? valorPalco : j>29 && j < 50 ? valorPlateaAlta : j>49 && j < 70 ? valorPlateaBaja : valorGeneral);            
                sector[j] = (j < 10 ? "Vip" : j>19 && j < 30 ? "Palco" : j>29 && j < 50 ? "Platea Alta" : j>49 && j < 70 ? "Platea Baja" : "General");            
                valor[j]=(int)total;
                descuento=total*descuento;
                usuarioDescuento[j]=(int)descuento;                    
            }                
        } 
        
        } 
        if (encontrado){
            imprimirDatosCliente();
        procesoCompraReserva();
        }
        
        menu=-1;
       
    }
    
    static public void casoTres() {
        guardarNombre();
        buscarCliente();
        if(encontrado){            
            revisarAsiento();
            if (encontrado){
                if(comprado[nro]==true){
                    System.out.println("Sus entradas ya estan pagadas");                    
                }else{
                imprimirDatosCliente();
                System.out.println("¿Desea terminar la compra?");
                System.out.println("Si (Y) / NO (otra tecla)");
                }
                inputString=inputTc.nextLine().toUpperCase();
                if(inputString.equals("Y")){
                    procesoCompra();
                }else{
                    System.out.println("Volviendo al menu principal\n");
                }
            }else{
                if(comprado[nro]==true){
                    System.out.println("Sus entradas ya estan pagadas");
                }                
            }    
        }else {
            System.out.println("Cliente no encontrado");
        }
    }
    
    static public void casoCuatro () {
        ingresarNombre();
        buscarCliente();
        imprimirDatosCliente();
    }
    
    static public void casoCinco() {
        ingresarNombre();        
        buscarCliente();
        if (encontrado){
            imprimirDatosCliente();
            System.out.println("¿Desea hacer un reembolso?");
            System.out.println("Si (Y) / NO (otra tecla)");
            inputString=inputTc.nextLine().toUpperCase();
            if (inputString.equals("Y")){                
                System.out.println("Se ha reembolsado: $"+(int)total);
                recaudacion-=total;
                cambioEstadoCompradofalse();
                borrarDatosCliente();                  
            }
        }else{
            System.out.println("Cliente no existe");
        }       
    }
        
    
    static public void casoSeis() {
        System.out.println("Binevenido a la zona de administración");
        System.out.println("¿Qué información desea revisar?\n"
                + "1. Registro completo de ventas\n"
                + "2. Recaudacion total");
        inputString=inputTc.nextLine().trim();
        if (inputString.equals("1")){
            System.out.println("Ventas registradas: \n");
            System.out.println(ventas);
        }else if(inputString.equals("2")){
            System.out.println("La recaudación total es de $"+recaudacion);                
        }else{
            errorValidacion();
        }        
    }   
        
    static public void guardarNombre() {
        ingresarNombre();              
        total=0;
    }                     
    
    static public void verificarCodigoAsiento() {
        boolean paso=false;                
        while (!paso) {            // busca que el codigo de asiento sea valido  
            inputString=inputTc.nextLine().trim();
            paso = inputStringVacio();
            if (paso==true){
                inputString=null;
                codigo="";
                break;
            }
            //convierto los datos para pasar a validacion
            codigo=inputString.toUpperCase();
            charFila=codigo.charAt(0);
            inputString=codigo.substring(1);
            
            if(codigo.length()!=2){ //valida el largo de los datos
            errorValidacion();
            continue;
            }
            if (charFila<'A' || charFila>'L'){ //valida secciones elegidas
                errorValidacion();
                continue;
            }   
            convertirCharFila();
            validacionNro();
            revisarAsiento();
            if(!encontrado){ //si asiento esta ocupado evita que se guarde el asiento
                guardarAsiento();
            }else{
                System.out.println("Asiento no disponible");
            }
            
        }
    }
                        
    static private void revisarAsiento() {
        encontrado=false;   // buscar si el asiento ya esta ocupado o reservado        
        for(int i = 0; i < codigoAsiento.length; i++){                                
            if(codigoAsiento[i]!=null && codigoAsiento[i].equals(codigo)){
                nro=i;
                encontrado=true;
                break;
            }                                
        }            
    }                 
    
    static public void convertirCharFila() {   // asigno valores por fila segun impresion de columnas (10)
        switch (charFila) {
            case 'A':
                filaNum=-1;  //comienza en -1 por la suma de fila++, se asigno caracter @ para comenzar, suma y resulta A
                break;
            case 'B':
                filaNum=9;
                break;
            case 'C':
                filaNum=19;
                break;
            case 'D':
                filaNum=29;
                break;
            case 'E':
                filaNum=39;
                break;
            case 'F':
                filaNum=49;
                break;
            case 'G':    
                filaNum=59;
                break;
            case 'H':
                filaNum=69;
                break;
            case 'I':
                filaNum=79;
                break;
            case 'J':
                filaNum=89;
                break;
            case 'K':
                filaNum=99;
                break;
            default:
                filaNum=109;
                break;
        }                           
    }  //convierte valor de caracter fila a valor numérico   
    
    static public void guardarAsiento() {
        inputInt=filaNum+nro;
        for ( int i = 0 ; i<120 ; i++){
            if(i==inputInt){
                nro=i;
                codigoAsiento[i]=codigo; //genera la reserva en array
                codigo=""; //reinicia variable
                nombreCliente[i]=nombre; //asigna nombre para la busqueda                       
                break;
            }
        }        
    }       
    
    static public void asignacionDescuentoEspecial() { //descuentos ordenados de menos a mayor para elegir el mayor descuento      
        boolean paso=false;
        int edad = 0; 
        do {
            System.out.println("Ingrese su edad");
            inputString=inputTc.nextLine().toUpperCase();
            validacionNro();
            edad=nro;
            if (edad < 0 || edad > 110){
                errorValidacion();            
            }else{
                paso = true;
            }
        } while(!paso);
        if (edad > 0 && edad < 18){
            descuento=descuentoMenor;
        }        
        System.out.println("Si es mujer ingrese Y");
        inputString=inputTc.nextLine().toUpperCase();
        if (inputString.equals("Y")) {
            descuento=descuentoMujer;
        }
        System.out.println("Si es estudiante ingrese Y");
        inputString=inputTc.nextLine().toUpperCase();
        if (inputString.equals("Y")) {
            descuento=descuentoEstudiante;
        }
        if (edad >59 && edad < 110) {
            descuento=descuentoTEdad;
        }
    }            
    
    static public void procesoCompraReserva() {               
        System.out.println("Para terminar su compra presione Enter");
        System.out.println("Para generar reserva ingrese cualquier otra tecla");                      
        inputString=inputTc.nextLine();
        if(inputString.equals("")){
           procesoCompra();               
        }else{ //generador de reserva
           generarReserva();
        }
        ciclo=true;
        
    }        
    
    static public void ingresarNombre () {
        System.out.println("Por favor, ingrese su nombre");        
        nombre=inputTc.nextLine().toUpperCase();
    }
    
    static public void buscarCliente() {        
        encontrado=false;
        for (int i = 0 ; i<120 ; i++){
            if(nombre.equals(nombreCliente[i])) {                
                nro=i;
                encontrado=true;                          
            }
        }                
    }
    
    static private void generarReserva()  {
        System.out.println("Se ha generado la reserva a nombre de "+nombre);
                System.out.println("Estará disponible por 1:30 minutos");
                String nombreReserva = nombre;
                    for( int i = 0 ; i<10; i++){ // inicio el timer en array guarda hasta 10 reservas
                        if(timer[i]==null){
                            timerIndice = i;
                            timer[timerIndice]= new Timer(true);
                            break;
                        }
                    }
                TimerTask reserva = new TimerTask(){ 
                   @Override
                public void run() {      
                    
                    for (int i=0; i<120; i++){
                        if(nombreCliente[i]==nombreReserva){
                            if(comprado[i]==false){  //verifica que cliente alcanzo a comprar antes de eliminar la reserva                                
                                System.out.println("Se ha borrado la reserva de "+nombreCliente[i]);                    
                                usuario[i]=null;
                                valor[i]=0;                                          
                                codigoAsiento[i]=null;  
                                nombreCliente[i]=null;                    
                            }
                        }    
                    } 
                    this.cancel();                            
                }
                };
                timer[timerIndice].schedule(reserva, 90*1000);  //tiempo asignado de 1:30 minutos
                timer[timerIndice]=null;                                                
            }
    
    static public void borrarDatosCliente () {
        for (int i=0; i<120; i++){
            if(nombre.equals(nombreCliente[i])){
                if(comprado[i]==false){                                 
                    System.out.println("Se ha borrado la compra de "+nombreCliente[i]); 
                    System.out.println("");
                    usuario[i]=null;
                    valor[i]=0; 
                    usuarioDescuento[i]=0;
                    codigoAsiento[i]=null;  
                    nombreCliente[i]=null;                    
                }
            }    
        } 
    }
    
    static public void procesoCompra() {
        obtenerDatosCliente();
        verificarPago();
        imprimirDatosCliente();
        System.out.println("    MUCHAS GRACIAS POR SU\n"
                + "     VISITA Y PREFERENCIA");
        System.out.println("--------------------------------");    
        descuentoEntradasDisponibles();
        cambioEstadoCompradotrue();
        recaudacion+=total;
                
        ventas.add(obtenerDatosCliente());
        //agregar a registro de ventas de clientes
    }
    
    static public String obtenerDatosCliente () {
        StringBuilder datos = new StringBuilder(); //constructor de String para guardar los datos
        cantidad=0;        
        total=0;
        descuento=0;
        codigo=null;        
        datos.append("Nombre: ").append(nombre).append("\n")
                .append("Nro Asientos: ");                
        contarAsientos();
        datos.append(cantidad).append("\n");
        datos.append("Usuarios: ");
        for(int  i = 0; i<120; i++) {       //ciclo que asigna valores para el calculo de boleta
            if(nombre.equals(nombreCliente[i])) {
                total=total+valor[i];
                descuento=descuento+usuarioDescuento[i];                                
                codigo=codigoAsiento[i];                
                datos.append(codigo).append(" (").append(usuario[i]).append(", ").append(sector[i]).append("), ");
            }
        }
        datos.append("\n");
        datos.append("Total: $").append((int)total).append("\n");        
        datos.append("Descuento aplicado: $").append((int)descuento).append("\n");
        total=total-descuento; //aplicacion del descuento
        datos.append("Costo total: $").append((int)total).append("\n")
                .append("-------------------------------\n\n");
        
        return datos.toString();
    }
    
    static public void imprimirDatosCliente () {
        System.out.println("----- Datos de su compra ----\n\n"+obtenerDatosCliente());
                                          
    }
    
    static public void contarAsientos () {
        for(int i = 0 ; i<nombreCliente.length ; i++) {   //ciclo que busca la cantidad de veces que se repite el nombre para contar sus entradas
            if(nombre.equals(nombreCliente[i])) {
               cantidad+=1;
            }       
        }
    }
    
    static public void verificarPago () {
        boolean paso=false;        
        System.out.println("Ingrese el total de su boleta para efectuar el pago: $"+(int)total);                
        validacionNro();
        pago=nro;                        
        while(!paso){
            if(pago==total){                
                paso=true;
                break;
            }else if(pago<total) {
                System.out.println("Ingrese el importe completo: $"+(int)total);  
                pago=inputTc.nextInt();
                continue;
            }else{
                vuelto=pago-total;
                System.out.println("Su vuelto es de $"+(int)vuelto);
                paso=true;                
                ciclo=false;                
                break;
            }
        }
    }
    
    static public void descuentoEntradasDisponibles () {        
        for(int  i = 0; i<120; i++) {
            if(nombre.equals(nombreCliente[i])) {
                if(i<10){
                    nVip=nVip-1;       
                }else if(i>9 && i<30){
                    nPalco=nPalco-1;
                }else if(i>29 && i<50){
                    nPlateaAlta=nPlateaAlta-1;
                }else if(i>49 && i<70){
                    nPlateaBaja=nPlateaBaja-1;
                }else{
                     nGeneral=nGeneral-1;
                }                
            }
        }
    }
    
    static public void cambioEstadoCompradotrue() {  //cambia de estado entradas compradas
        for(int i = 0 ; i<nombreCliente.length ; i++) {   
            if(nombre.equals(nombreCliente[i])) {
               comprado[i]=true;               
            }       
        }
    }
    
    static public void cambioEstadoCompradofalse() { //enccunetra coincidencias y las borra
        for(int i = 0 ; i<nombreCliente.length ; i++) {  
            if(nombre.equals(nombreCliente[i])) {
                if(i<10){
                    nVip=nVip+1;       
                }else if(i>9 && i<30){
                    nPalco=nPalco+1;
                }else if(i>29 && i<50){
                    nPlateaAlta=nPlateaAlta+1;
                }else if(i>49 && i<70){
                    nPlateaBaja=nPlateaBaja+1;
                }else{
                     nGeneral=nGeneral+1;
                }   
                comprado[i]=false;          
                eliminarElementoLista();
            }       
        }
    }
    
    static public void eliminarElementoLista () { //elimina elemento por igualdad, por desconocer el indice
        for (int i = 0; i<ventas.size(); i++){
            if(obtenerDatosCliente().equals(ventas.get(i))){
                ventas.remove(i);
            }
                
        }
    }

       
}

    



