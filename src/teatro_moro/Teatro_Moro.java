/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatro_moro;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Javier Calabrano
 */
public class Teatro_Moro {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       /*Requisitos de actividad
            Operar venta
            Operar promoción
            Operar búsqueda
            Operar eliminación de entradas
            Aplicar descuentos
                10% estudiantes
                15% tercera edad
            Incluir menú de opciones
            Abordar los temas relacionados a variables
            Validar los resultados ingresados por el usuario
        Paso 1: Menu de opciones
            - Venta de entradas
                Solicitar la ubicación de la entrada: VIP, platea, General
                Aplicar descuento
                Mostrar el precio final y almacenar la entrada vendida
            - Promociones
                Mostrar promociones disponibles por mayor o similares
        voy a agregar un 10% por 3 entradas, 20% 5 entradas
            - Búsqueda de entradas
                Busqueda por asiento, número, tipo, descuento, como opciones
            - Eliminación de entradas
                Elimina una entrada especifica mediante su número
            Estructuras condicionales
                Para determinar descuentos
                Ciclos para realizar busquedas y recorres lista de entradas vendidas
        Paso 2: Variables
            4 variables locales (datos temporales, como descuento aplicado)
            4 variables de instancia (datos persistentes, como las entradas que ya estan vendidas)
            3 variables estaticas (datos globales, como el precio de las entradas por sector)
        */
        //Declaracion de variables:
        //Variables estaticas
        int valorGeneral=5000, valorVip=25000, valorPlatea=10000; //valores
        int nGeneral=60, nVip=20, nPlatea=40;//entradas disponibles
        String nombreCliente[] = new String[120]; //arreglo para nombres de clientes
        int nAsientos[] = new int[120];
        String tipoAsiento[] = new String[120];
        String codigoAsiento[] = new String[120];
        boolean comprado[] = new boolean[120];
        int valorPagado[] = new int[120];
        
        //variables de instancia
        Timer[] timer = new Timer[10];
        int menu=0; //eleccion del switch case
        int filaNum;
        int indiceArray;
        int pago, nEntradas = 0, sector=0, vEntrada, edad=0, nro = 0;//entradas de usuario
        String nombre, estado, codigo="aa", confirmacion = null, codigoNoDisponible ="aa";
        boolean paso=false, ciclo=false, encontrado=false;
        char charFila = 0;
        String fila;  //codigo asiento letra
        String columna; //codigo de asiento bnumero
        //variables locales       
        int promocion, valor = 0, cantidad = 0, indice[] = new int[170], lista = 0, timerLista, timerIndice = 0;
        
        double total, descuentoPromo=0, descuentoEdad=0, descuento, vuelto;
        String sectorText = null;
        
        Scanner tc=new Scanner(System.in);//Activar el teclado       
        
        //Menu de eleccion para cliente de Teatro Moro
        while (!ciclo){
            System.out.println("Bienvenido a Teatro Moro\n" //mensaje inicial
                    + "¿Qué desea hacer hoy?\n"
                    + "1. Comprar entradas\n"
                    + "2. Revisar / pagar reserva\n"
                    + "3. Promociones disponibles\n"
                    + "4. Revise sus entradas\n"
                    + "5. Devolución de entradas\n"
                    + "6. Terminar visita");       

           while(!paso){               //validacion de eleccion
                    try{
                        System.out.println("\nIngrese una opción valida");
                        menu=tc.nextInt();                                         
                    while(menu<1 || menu>5){ //validación de menu correcto
                        System.out.println("Por favor ingrese una opción valida\n"
                            + "1. Reservar y comprar entradas\n" //agregar reserva preguntar para confirmar pago
                            + "2. Revisar /pagar reserva" //confirmar pago
                            + "3. Promociones disponibles\n"
                            + "4. Revise sus entradas\n"// confirmar clientes que compraron
                            + "5. Devolución de entradas\n"
                            + "6. Terminar visita");
                        menu=tc.nextInt();                        
                }
                    paso=true;
            }catch(InputMismatchException e) {                
                System.out.println("Error, por favor ingrese una opción valida\n"
                        + "1. Reservar y comprar entradas\n" //agregar reserva preguntar para confirmar pago
                        + "2. Revisar /pagar reserva" //confirmar pago
                        + "3. Promociones disponibles\n"
                        + "4. Revise sus entradas\n"// confirmar clientes que compraron
                        + "5. Devolución de entradas\n"
                        + "6. Terminar visita");              
                tc.next();          
                paso=false;                
                }
            }
        paso=false;
        switch(menu){ //eleccion de caso
            case 1:       // caso de reserva y confirmacion de compra         
                System.out.println("====Entradas disponibles====\n" //Stock de entradas
                    + "Tipo       | Valor    | Disponibles"
                    + "\n1. VIP     | "+valorVip+" |      "+nVip
                    + "\n2. Platea  | "+valorPlatea+" |      "+nPlatea
                    + "\n3. General |  "+valorGeneral+" |      "+nGeneral);                    
                while(paso==false){//selector sector o tipo de entrada
                    try {
                        System.out.println("Ingrese sector de su preferencia"); 
                        sector=tc.nextInt();                            
                        while(sector<1 || sector>3){ //validación de menu correcto                                                       
                            System.out.println("Error, por favor ingrese una opción valida para tipo de entrada con:\n"
                                + "\n1. VIP"
                                + "\n2. Platea"
                                + "\n3. General");
                            sector=tc.nextInt();                                
                        }
                        paso=true;
                    }catch(InputMismatchException e) {
                        System.out.println("Error, por favor ingrese una opción valida para tipo de entrada con:\n"
                            + "\n1. VIP"
                            + "\n2. Platea"
                            + "\n3. General");
                        tc.nextLine();  
                        paso=false;
                    }                                                     
                }   
                System.out.println("Ingrese su nombre para generar su compra");
                tc.nextLine();
                nombre=tc.nextLine().toUpperCase();
                paso=false; // reinicio variable para proximo menu de validación  
                
                if(sector==1) { //impresion vip 
                    System.out.println("   ---- MAPA DE ASIENTOS ----\n"
                        + "\n   1  2  3  4  5  6  7  8  9  10");
                    System.out.print("A ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[i] ? "[X]" : codigoAsiento[i]!=null ? "[R]" : "[ ]");       
                        System.out.print(estado);
                    }
                    System.out.println("");
                    System.out.print("B ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[10+i] ? "[X]" : codigoAsiento[10+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                    System.out.println("\n");
                    System.out.println("Comprado[X] Reservado[R] Disponible[ ]");
                    System.out.println("");
                    System.out.println("¿Que asiento prefiere?");
                    confirmacion=tc.nextLine();
                    while (!ciclo){       //ciclo de ingreso de asientos                         
                        while (!paso){  // validacion de asientos vip  
                            if(confirmacion==null){
                                confirmacion=tc.nextLine();
                            }
                            codigo=confirmacion.toUpperCase();
                            charFila=codigo.charAt(0);
                            columna=codigo.substring(1);
                            if(codigo.length()!=2){
                            System.out.println("Por favor ingrese una opción valida");                
                            }
                            if (charFila<'A' || charFila>'B'){
                                System.out.println("Por favor ingrese una opción valida");
                                continue;
                            }                                    
                            try{
                                nro=Integer.parseInt(columna);                                
                            }catch(NumberFormatException e){
                                System.out.println("El numero es invalido");
                                continue;
                            }
                            encontrado=false;
                            for(int i = 0; i < nombreCliente.length; i++){                                
                                if(codigoAsiento[i]!=null && codigoAsiento[i].equals(codigo)){
                                    System.out.println("Asiento no disponible");
                                    confirmacion=tc.nextLine();
                                    encontrado=true;
                                    break;
                                }                                
                            }  
                            if(encontrado){
                                continue;
                            }
                        if (charFila=='A'){ //guardado de datos cliente
                            filaNum=-1;
                        }else{
                            filaNum=9;
                        }
                        indiceArray=filaNum+nro;
                        for ( int i = 0 ; i<20 ; i++){
                            if(i==indiceArray){
                                codigoAsiento[i]=codigo;
                                nombreCliente[i]=nombre;
                                break;
                            }
                        }
                        paso=true;
                        }
                        nEntradas++; //suma de entradas del cliente en uso                      
                        System.out.println("¿Desea escoger otro asiento?");
                        System.out.println("Presione Enter para continuar, cualquier otra tecla para nuevo asiento");                       
                        confirmacion=tc.nextLine();
                        if(confirmacion!=""){
                            ciclo=false;
                            paso=false;
                            continue;                      
                        }
                        ciclo=true;
                    }                    
                    valor=valorVip;
                    cantidad=nVip;
                    sectorText="Vip";                                         
                    
                }else if(sector==2){ //impresion platea
                    System.out.println("   ---- MAPA DE ASIENTOS ----\n"
                        + "\n   1  2  3  4  5  6  7  8  9  10");
                    System.out.print("C ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[20+i] ? "[X]" : codigoAsiento[20+i]!=null ? "[R]" : "[ ]");       
                        System.out.print(estado);
                    }
                    System.out.println("");
                    System.out.print("D ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[30+i] ? "[X]" : codigoAsiento[30+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                    System.out.println("");
                    System.out.print("E ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[40+i] ? "[X]" : codigoAsiento[40+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                     System.out.println("");
                    System.out.print("F ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[50+i] ? "[X]" : codigoAsiento[50+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }                  
                    System.out.println("\n");
                    System.out.println("Comprado[X] Reservado[R] Disponible[ ]");
                    System.out.println("");
                    System.out.println("¿Que asiento prefiere?");   
                    confirmacion=tc.nextLine();
                    while (!ciclo){       //ciclo de ingreso de asientos                         
                        while (!paso){  // validacion de asientos vip  
                            if(confirmacion==null){
                                confirmacion=tc.nextLine();
                            }
                            codigo=confirmacion.toUpperCase();                            
                            charFila=codigo.charAt(0);
                            columna=codigo.substring(1);
                            if(codigo.length()!=2){
                            System.out.println("Por favor ingrese una opción valida");                
                            }
                            if (charFila<'C' || charFila>'F'){
                                System.out.println("Por favor ingrese una opción valida");
                                continue;
                            }                                    
                            try{
                                nro=Integer.parseInt(columna);                                
                            }catch(NumberFormatException e){
                                System.out.println("El numero es invalido");
                                continue;
                            }
                            encontrado=false;
                            for(int i = 20; i < nombreCliente.length; i++){                                
                                if(codigoAsiento[i]!=null && codigoAsiento[i].equals(codigo)){
                                    System.out.println("Asiento no disponible");
                                    confirmacion=tc.nextLine();
                                    encontrado=true;
                                    break;
                                }                                
                            }  
                            if(encontrado){
                                continue;
                            }
                        if (charFila=='C'){ //guardado de datos cliente
                            filaNum=19;
                        }else if(charFila=='D'){
                            filaNum=29;
                        }else if(charFila=='E'){
                            filaNum=39;
                        }else{
                            filaNum=49;
                        }
                        indiceArray=filaNum+nro;
                        for ( int i = 20 ; i<60 ; i++){
                            if(i==indiceArray){
                                codigoAsiento[i]=codigo;
                                nombreCliente[i]=nombre;
                                break;
                            }
                        }
                        paso=true;
                        }
                        nEntradas++; //suma de entradas del cliente en uso                      
                        System.out.println("¿Desea escoger otro asiento?");
                        System.out.println("Presione Enter para continuar, cualquier otra tecla para nuevo asiento");                       
                        confirmacion=tc.nextLine();
                        if(confirmacion!=""){
                            ciclo=false;
                            paso=false;
                            continue;                      
                        }
                        ciclo=true;
                    }                    
                    valor=valorPlatea;
                    cantidad=nPlatea;
                    sectorText="Platea";
                    
                }else{ //impresion sector general
                    System.out.println("   ---- MAPA DE ASIENTOS ----\n"
                        + "\n   1  2  3  4  5  6  7  8  9  10");
                    System.out.print("G ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[60+i] ? "[X]" : codigoAsiento[60+i]!=null ? "[R]" : "[ ]");       
                        System.out.print(estado);
                    }
                    System.out.println("");
                    System.out.print("H ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[70+i] ? "[X]" : codigoAsiento[70+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                     System.out.println("");
                    System.out.print("I ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[80+i] ? "[X]" : codigoAsiento[80+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                     System.out.println("");
                    System.out.print("J ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[90+i] ? "[X]" : codigoAsiento[90+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                    System.out.println("");
                    System.out.print("L ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[100+i] ? "[X]" : codigoAsiento[100+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                    System.out.println("");
                    System.out.print("M ");
                    for(int i = 0; i < 10; i++){
                        estado = (comprado[110+i] ? "[X]" : codigoAsiento[110+i]!=null ? "[R]" : "[ ]"); 
                        System.out.print(estado);
                    }
                    System.out.println("\n");
                    System.out.println("Comprado[X] Reservado[R] Disponible[ ]");
                    System.out.println("");
                    System.out.println("¿Que asiento prefiere?");
                    confirmacion=tc.nextLine();                    
                    while (!ciclo){       //ciclo de ingreso de asientos                         
                        while (!paso){  // validacion de asientos vip 
                            if(confirmacion==null){
                                confirmacion=tc.nextLine();
                            }
                            codigo=confirmacion.toUpperCase();
                            charFila=codigo.charAt(0);
                            columna=codigo.substring(1);
                            if(codigo.length()!=2){
                            System.out.println("Por favor ingrese una opción valida");                
                            }
                            if (charFila<'G' || charFila>'M'){
                                System.out.println("Por favor ingrese una opción valida");
                                continue;
                            }                                    
                            try{
                                nro=Integer.parseInt(columna);                                
                            }catch(NumberFormatException e){
                                System.out.println("El numero es invalido");
                                continue;
                            }
                            encontrado=false;
                            for(int i = 0; i < nombreCliente.length; i++){                                
                                if(codigoAsiento[i]!=null && codigoAsiento[i].equals(codigo)){
                                    System.out.println("Asiento no disponible");
                                    confirmacion=tc.nextLine();
                                    encontrado=true;
                                    break;
                                }                                
                            }  
                            if(encontrado){
                                continue;
                            }
                        if (charFila=='G'){ //guardado de datos cliente
                            filaNum=59;
                        }else if(charFila=='H'){
                            filaNum=69;
                        }else if(charFila=='I'){
                            filaNum=79;
                        }else if(charFila=='J'){
                            filaNum=89;
                        }else if(charFila=='L'){
                            filaNum=99;
                        }else{
                            filaNum=109;
                        }
                        indiceArray=filaNum+nro;
                        for ( int i = 60 ; i<120 ; i++){
                            if(i==indiceArray){
                                codigoAsiento[i]=codigo;
                                nombreCliente[i]=nombre;
                                break;
                            }
                        }
                        paso=true;
                        }
                        
                        //paso a terminar la compra o generar reserva
                        nEntradas++; //suma de entradas del cliente en uso                      
                        
                        System.out.println("Presione Enter para continuar, o ingrese nuevo asiento");                       
                        confirmacion=tc.nextLine();
                        if(confirmacion!=""){
                            ciclo=false;
                            paso=false;
                            continue;                      
                        }
                        ciclo=true;
                    }                    
                    valor=valorGeneral;
                    cantidad=nGeneral;
                    sectorText="General";
                    
                }
                cantidad=cantidad-nEntradas; //guardar datos para calculos                 
                paso=false;
                while (paso==false){
                    try{ //validacion de edad
                        System.out.println("Ingrese su edad para confirmar descuentos");
                        edad=tc.nextInt(); 
                        while(edad>110 || edad<1){
                            System.out.println("Cantidad no válida");
                            edad=tc.nextInt();
                        }
                        paso=true;
                    }catch(InputMismatchException e) {
                        System.out.println("Error, por favor ingrese una cantidad de años validos\n");
                        tc.nextLine();
                        paso=false;
                    }
                }   
                total=nEntradas*valor; //asignacion de descuentos
                if(nEntradas%3==0){
                    descuentoPromo=0.10;
                }else if(nEntradas%5==0){
                    descuentoPromo=0.20;
                }else if(edad<18 && edad>0){
                    descuentoEdad=0.10;
                }else if(edad>59){
                    descuentoEdad=0.15;
                }else{
                    descuento=0;
                }
                descuento=descuentoEdad+descuentoPromo;//calculo descuentos
                total=total-(total*descuento);//aplicacion de descuentos
                descuento=total*descuento;                
                System.out.println("El total de su boleta es: $"+(int)total);
                System.out.println("Los descuentos aplicados fueron de $"+(int)descuento);
                
                for (int i = 0; i < nombreCliente.length; i++) {
                    if(nombreCliente[i]==nombre){                        
                        indice[i]=i; 
                        lista=i;
                        timerLista=i;
                        nAsientos[i]=nEntradas;
                        tipoAsiento[i]=sectorText;
                        valorPagado[i]=(int)total;
                        break;
                    }
                }
                if(sector==1){
                        nVip=nVip-nEntradas;
                    }else if(sector==2){
                        nPlatea=nPlatea-nEntradas;
                    }else{
                        nGeneral=nGeneral-nEntradas;
                    }
                System.out.println("Para terminar su compra presione Enter");
                System.out.println("Para generar reserva ingrese cualquier otra tecla");
                tc.nextLine(); 
                confirmacion=tc.nextLine();
                if(confirmacion==""){
                    System.out.println("Ingrese el total de su boleta para efectuar el pago: $"+(int)total);                
                    pago=tc.nextInt();
                    while(pago!=(int)total){
                        if(pago<total) {
                        System.out.println("Ingrese el importe completo: $"+total);  
                        pago=tc.nextInt();
                        }else{
                            vuelto=pago-total;
                            System.out.println("Su vuelto es de $"+(int)vuelto);
                            break;
                        }
                    }                                   
                }else{
                    //generar timer de reserva ojala con array o identificador para el cliente que compra
                    //break es para no imprimir la compra
                    System.out.println("Se ha generado la reserva");
                    System.out.println("Estará disponible por 1:30 minutos");
                String nombreReserva = nombre;
                    for( int i = 0 ; i<10; i++){ // inicio el timer en array
                        if(timer[i]==null){
                            timerIndice = i;
                            timer[i]= new Timer(true);
                            break;
                        }
                    }
                     TimerTask reserva = new TimerTask(){
                        @Override
                        public void run() {                            
                            for (int i=0; i<120; i++){
                                if(nombreCliente[i]==nombreReserva){
                                    if(comprado[i]==false){
                                        nombreCliente[i]=null;
                                        tipoAsiento[i]=null;
                                        nAsientos[i]=0;
                                        valorPagado[i]=0;                                          
                                        codigoAsiento[i]=null;
                                        System.out.println("Se ha borrado la reserva");
                                    }
                                }    
                            }
                            this.cancel();                            
                        }
                        
                    };
                    timer[timerIndice].schedule(reserva, 90*1000);
                    timer[timerIndice]=null;
                            
                nEntradas=0;// lineas temporales para verificaciones
                total=0;
                descuento=0;
                ciclo=false;
                paso=false;
                menu=0;
                encontrado=false;
                break;                                         
                }
                    System.out.println("Resumen de su compra:"
                        + "\nNombre: "+nombreCliente[lista]
                        + "\nSector: "+tipoAsiento[lista]
                        + "\nCantidad: "+nAsientos[lista]
                        + "\nTotal: "+valorPagado[lista]);  
                    System.out.println("");
                for (int i = 0; i < nombreCliente.length; i++) {
                    
                    if(nombreCliente[i]==nombre){                        
                        comprado[i]=true;                       
                        indice[i]=0;                        
                    }
                }
                nEntradas=0;
                total=0;
                descuento=0;
                ciclo=false;
                paso=false;
                menu=0;
                encontrado=false;
                break;                                        
                   
            case 2: //pagar una reserva
                encontrado=false;                      
                System.out.println("Ingrese su nombre para revisar su reserva en caso de poseerla"); 
                tc.nextLine();
                nombre=tc.nextLine().toUpperCase();
                for(int i = 0 ; i < nombreCliente.length ; i++ ){
                    if(nombre.equals(nombreCliente[i])){
                        if(comprado[i]==true){
                            System.out.println("Sus asientos ya han sido pagados"); 
                            System.out.println("");
                            encontrado=true;
                            menu=0;
                            paso=false;
                            ciclo=false;
                            break;
                        }
                        lista = i;  
                        System.out.println("Resumen de su compra:"
                            + "\nNombre: "+nombreCliente[lista]
                            + "\nSector: "+tipoAsiento[lista]
                            + "\nCantidad: "+nAsientos[lista]);
                        System.out.print("Asientos: ");
                            for (int b = 0; b < 120; b++){
                                if(nombreCliente[b]==nombreCliente[lista]){
                                    codigo=codigoAsiento[b];
                                    System.out.print(codigo+", ");
                                }
                            }
                            
                            System.out.println("\nTotal: "+valorPagado[lista]);
                        System.out.println("");
                        encontrado=true;
                        break;
                    }
                }
                    if(encontrado==false){
                        System.out.println("No se han encontrado coincidencias\n");
                        break;
                }
                if (encontrado && comprado[lista]){
                    //vacio porque mostro ya el mensaje de asientos pagados
                }else if(encontrado){
                //ciclo de compra
                System.out.println("Para terminar su compra presione Enter");
                System.out.println("Para volver al menu ingrese cualquier otra tecla");                
                confirmacion=tc.nextLine();
                if(confirmacion==""){
                    System.out.println("Ingrese el total de su boleta para efectuar el pago: $"+valorPagado[lista]);                
                    pago=tc.nextInt();
                    while(pago!=valorPagado[lista]){
                        if(pago<valorPagado[lista]) {
                        System.out.println("Ingrese el importe completo: $"+valorPagado[lista]);  
                        pago=tc.nextInt();
                        }else{
                            vuelto=pago-valorPagado[lista];
                            System.out.println("Su vuelto es de $"+(int)vuelto);
                            break;
                        }
                    }
                    System.out.println("Resumen de su compra:"
                            + "\nNombre: "+nombreCliente[lista]
                            + "\nSector: "+tipoAsiento[lista]
                            + "\nCantidad: "+nAsientos[lista]);
                        System.out.print("Asientos: ");
                            for (int b = 0; b < 120; b++){
                                if(nombreCliente[lista]==codigoAsiento[lista]){
                                    codigo=codigoAsiento[lista];
                                    System.out.print(codigo+", ");
                                }
                            }                            
                            System.out.println("\nTotal: "+valorPagado[lista]);
                        System.out.println("");
                    if(sector==1){
                        nVip=nVip-nAsientos[lista];
                    }else if(sector==2){
                        nPlatea=nPlatea-nAsientos[lista];
                    }else{
                        nGeneral=nGeneral-nAsientos[lista];
                    }                  
                for (int i = 0; i < nombreCliente.length; i++) {                    
                    if(nombreCliente[i]==nombreCliente[lista]){                        
                        comprado[i]=true;                       
                        indice[i]=0;                        
                    }
                }
                }
                }             
                menu=0;
                paso=false;
                ciclo=false;
                break;
                    
            case 3:         
                System.out.println("---PROMOCIONES DEL MES---"
                        + "\n- Por 3 entradas un 10% de descuento"
                        + "\n- Por 5 entradas un 20% de descuento"
                        + "\n"
                        + "\nEstudiantes: 10% de descuento"
                        + "\nAdulto moyar: 15% de descuento"
                        + "\n");                   
                paso=false; 
                menu=0;
                break;
            case 4:  //revisar entradas compradas
                encontrado=false;                      
                System.out.println("Ingrese su nombre para revisar su reserva en caso de poseerla"); 
                tc.nextLine();
                nombre=tc.nextLine().toUpperCase();
                for(int i = 0 ; i < nombreCliente.length ; i++ ){
                    if(nombre.equals(nombreCliente[i]) && comprado[i]){                        
                        lista = i;  
                        System.out.println("Resumen de su compra:"
                            + "\nNombre: "+nombreCliente[lista]
                            + "\nSector: "+tipoAsiento[lista]
                            + "\nCantidad: "+nAsientos[lista]);
                        System.out.print("Asientos: ");
                            for (int b = 0; b < 120; b++){
                                if(nombreCliente[b]==(nombreCliente[lista])){
                                    codigo=codigoAsiento[b];
                                    System.out.print(codigo+", ");
                                }
                            }
                            
                            System.out.println("\nTotal: "+valorPagado[lista]);
                        System.out.println("");
                        encontrado=true;
                        menu=0;
                        break;
                    }
                }
                    if(encontrado==false){
                        System.out.println("No se han encontrado coincidencias\n");
                        break;
                }
            case 5: 
                encontrado=false;                      
                System.out.println("Ingrese su nombre para revisar su reserva en caso de poseerla"); 
                tc.nextLine();
                nombre=tc.nextLine().toUpperCase();
                for(int i = 0 ; i < nombreCliente.length ; i++ ){
                    if(nombre.equals(nombreCliente[i])){                        
                        lista = i;  
                        System.out.println("Resumen de su compra:"
                            + "\nNombre: "+nombreCliente[lista]
                            + "\nSector: "+tipoAsiento[lista]
                            + "\nCantidad: "+nAsientos[lista]);
                        System.out.print("Asientos: ");
                            for (int b = 0; b < 120; b++){
                                if(nombreCliente[b]==nombreCliente[lista]){
                                    codigo=codigoAsiento[b];
                                    System.out.print(codigo+", ");
                                }
                            }
                            
                            System.out.println("\nTotal: "+valorPagado[lista]);
                        System.out.println("");
                        encontrado=true;
                        break;
                    }
                }
                    if(encontrado==false){
                        System.out.println("No se han encontrado coincidencias\n");
                        break;
                }
                //eliminacion de entradas    
                if(encontrado){                       
                    System.out.println("");
                    System.out.println("¿Confirma la eliminación de entradas?");
                    System.out.println("Presione 1 para eliminar\n"
                        + "Presione 0 para cancelar");                      
                    confirmacion=tc.nextLine();
                    if(confirmacion.equals("1")){                        
                            if(tipoAsiento[lista].equals("Vip")){
                                nVip=nVip+nAsientos[lista];
                            }else if(tipoAsiento[lista].equals("Platea")){
                                nPlatea=nPlatea+nAsientos[lista];
                            }else{
                                nGeneral=nGeneral+nAsientos[lista];
                            }   
                            tipoAsiento[lista]=null;
                            nAsientos[lista]=0;
                            for (int b = 0; b < 120; b++){
                                if(nombreCliente[b]==nombreCliente[lista]){
                                    codigoAsiento[b]=null;  
                                    comprado[b]=false;
                                }
                            }
                            nombreCliente[lista]=null;
                            System.out.println("Entradas eliminadas");
                            System.out.println("La devolución se realizará por $"+valorPagado[lista]);
                            valorPagado[lista]=0;                            
                        }else if(confirmacion.equals("0")){
                            System.out.println("Volviendo al menu principal");
                        }else{
                            System.out.println("Entrada invalida");
                            continue;
                        }   
                    menu=0;
                    break;
                }                                                        
            default:
                System.out.println("Muchas gracias por su visita al Teatro Moro");
                ciclo=true;
                break;
            }   
            }
                
    }
}

