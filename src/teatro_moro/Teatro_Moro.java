/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatro_moro;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author danie
 */
public class Teatro_Moro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* REQUISITOS:
        Debe desplegar un menú: comprar entrada, Salir
        Solicitar la ubicación del asiento (secciones A, B, C) con un estructura de validación
        Debe mostrar un plano con los asintos disponibles
        Solicitar el ingreso de la edad para aplicar descuentos
            Estudiantes 10%
            Tercera edad 15%
        Calcular el precio con un ciclo, se recomienda while o do while, aplicando descuentos
        Mostrar resumen de la transacción
            Ubicación del asiento
            Precio base de la entrada
            Descuento aplicado
            Precio final a pagar
        Premitir multiples compras, preguntando si desea comprar más o salir
        Manejo de errores de entradas inválidas
        */
        
        //Inicio; menu de selección
        //opcion de menu para entrar al bucle
        int nro, ciclo;
        double valor,valorDescuento,valorDescuentoTotal, valorTotal, valorReal, menu, nuevoAsiento, edad, pago, vuelto;
        //Sectores de teatro
        boolean a1=false, a2=false, a3=false, a4=false, a5=false, a6=false, a7=false, a8=false;
        boolean b1=false, b2=false, b3=false, b4=false, b5=false, b6=false, b7=false, b8=false;
        boolean c1=false, c2=false, c3=false, c4=false, c5=false, c6=false, c7=false, c8=false;
        boolean d1=false, d2=false, d3=false, d4=false, d5=false, d6=false, d7=false, d8=false;
        boolean e1=false, e2=false, e3=false, e4=false, e5=false, e6=false, e7=false, e8=false;
        boolean f1=false, f2=false, f3=false, f4=false, f5=false, f6=false, f7=false, f8=false;
        String asiento = "aa"; 
        String elegida="aa";
        String resumenAsientos="";
        menu=0; valor=0; valorTotal=0; valorDescuento=0; valorDescuentoTotal=0; valorReal=0; pago=0; vuelto=0; ciclo=0;
        boolean opcion, sillaOk;
        opcion=false; sillaOk=false; 
        char letra;
        String nroTxt;
        String letraS;
        String nroS;
        Scanner tc=new Scanner(System.in);//Activar el teclado
        while(ciclo==0){
            opcion=false;
            sillaOk=false;
        System.out.println("Bienvenido al Teatro Moro\n"
            + "Elija una opción disponble\n"
            + "1. Comprar entradas\n"
            + "2. Salir");
        ciclo=1;
        while(opcion==false){ //validacion de menu numerico
            try{
                System.out.println("\nIngrese una opción valida");
                menu=tc.nextInt();
                opcion=true;
                while(menu!=1 && menu!=2){ //validación de menu correcto
                    System.out.println("Por favor ingrese una opción valida\n"
                        + "1. Para comprar entradas\n"
                        + "2. Para terminar su visita\n");
                    menu=tc.nextInt();
                }
            }catch(InputMismatchException e) {                
                System.out.println("Error, por favor ingrese una opción valida\n"
                        + "1. Para comprar entradas\n"
                        + "2. Para terminar su visita\n");                
                tc.next();
                opcion=false;
            }   
        }
        if(menu==1){ //Proceso de compra
            System.out.println("       MAPA DE ASIENTOS\n" //mapa de asientos
                    + "=============================\n"
                    + "        Plataforma\n"
                    + "      _______________\n"
                    + "   1  2  3  4  5  6  7  8 \n"
                    +"A "+(a1 ? "[X]":"[ ]")
                    +(a2 ? "[X]":"[ ]")+""
                    +(a3 ? "[X]":"[ ]")+""
                    +(a4 ? "[X]":"[ ]")+""
                    +(a5 ? "[X]":"[ ]")+""
                    +(a6 ? "[X]":"[ ]")+""
                    +(a7 ? "[X]":"[ ]")+""
                    +(a8 ? "[X]":"[ ]")+"\n"
                    +"B "+(b1 ? "[X]":"[ ]")
                    +(b2 ? "[X]":"[ ]")+""
                    +(b3 ? "[X]":"[ ]")+""
                    +(b4 ? "[X]":"[ ]")+""
                    +(b5 ? "[X]":"[ ]")+""
                    +(b6 ? "[X]":"[ ]")+""
                    +(b7 ? "[X]":"[ ]")+""
                    +(b8 ? "[X]":"[ ]")+"\n"
                     +"C "+(c1 ? "[X]":"[ ]")
                    +(c2 ? "[X]":"[ ]")+""
                    +(c3 ? "[X]":"[ ]")+""
                    +(c4 ? "[X]":"[ ]")+""
                    +(c5 ? "[X]":"[ ]")+""
                    +(c6 ? "[X]":"[ ]")+""
                    +(c7 ? "[X]":"[ ]")+""
                    +(c8 ? "[X]":"[ ]")+"\n"
                     +"D "+(d1 ? "[X]":"[ ]")
                    +(d2 ? "[X]":"[ ]")+""
                    +(d3 ? "[X]":"[ ]")+""
                    +(d4 ? "[X]":"[ ]")+""
                    +(d5 ? "[X]":"[ ]")+""
                    +(d6 ? "[X]":"[ ]")+""
                    +(d7 ? "[X]":"[ ]")+""
                    +(d8 ? "[X]":"[ ]")+"\n"
                     +"E "+(e1 ? "[X]":"[ ]")
                    +(e2 ? "[X]":"[ ]")+""
                    +(e3 ? "[X]":"[ ]")+""
                    +(e4 ? "[X]":"[ ]")+""
                    +(e5 ? "[X]":"[ ]")+""
                    +(e6 ? "[X]":"[ ]")+""
                    +(e7 ? "[X]":"[ ]")+""
                    +(e8 ? "[X]":"[ ]")+"\n"
                     +"F "+(f1 ? "[X]":"[ ]")
                    +(f2 ? "[X]":"[ ]")+""
                    +(f3 ? "[X]":"[ ]")+""
                    +(f4 ? "[X]":"[ ]")+""
                    +(f5 ? "[X]":"[ ]")+""
                    +(f6 ? "[X]":"[ ]")+""
                    +(f7 ? "[X]":"[ ]")+""
                    +(f8 ? "[X]":"[ ]")+"\n"); //fin mapa asientos
            System.out.println("[X] = No disponible");
            System.out.println("[ ] = Disponible\n");
            System.out.println("Valores:\n"
                    + "A-B: $30000\n"
                    + "C-D: $20000\n"
                    + "E-F: $10000\n");            
            while(sillaOk==false){
                System.out.println("¿Qué asiento desea escoger?");
            System.out.println("(ejemplo: a1, d4, f6)");
                asiento=tc.next().toUpperCase();                
                letra=asiento.charAt(0);
                nroTxt=asiento.substring(1);
            if(asiento.length()!=2){
                System.out.println("Por favor ingrese una opción valida");
                continue;
            }
            try{
                nro=Integer.parseInt(nroTxt);
            }catch(NumberFormatException e){
                System.out.println("El numero es invalido");
                continue;
            }
            if(letra<'A' || letra>'F'|| nro<1 || nro>8){
                System.out.println("Asientos fuera de rango");
                continue;
            }
            if(letra=='A'){
                if(nro==1){
                    if(a1==false){
                        a1=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}                 
                if(nro==2){
                    if(a2==false){
                        a2=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}          
                if(nro==3){
                    if(a3==false){
                        a3=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}          
                if(nro==4){
                    if(a4==false){
                        a4=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}          
                if(nro==5){
                    if(a5==false){
                        a5=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}          
                if(nro==6){
                    if(a6==false){
                        a6=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}          
                if(nro==7){
                    if(a7==false){
                        a7=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}          
                if(nro==8){
                    if(a8==false){
                        a8=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}                          
            }
            if(letra=='b'){
                if(nro==1){
                    if(b1==false){
                        b1=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}          
                if(nro==2){
                    if(b2==false){
                        b2=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==3){
                    if(b3==false){
                        b3=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==4){
                    if(b4==false){
                        b4=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==5){
                    if(b5==false){
                        b5=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==6){
                    if(b6==false){
                        b6=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==7){
                    if(b7==false){
                        b7=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==8){
                    if(b8==false){
                        b8=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}                    
            }if(letra=='C'){
                if(nro==1){
                    if(c1==false){
                        c1=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==2){
                    if(c2==false){
                        c2=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==3){
                    if(c3==false){
                        c3=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==4){
                    if(c4==false){
                        c4=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==5){
                    if(c5==false){
                        c5=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==6){
                    if(c6==false){
                        c6=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==7){
                    if(c7==false){
                        c7=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==8){
                    if(c8==false){
                        c8=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}                  
            }if(letra=='D'){
                if(nro==1){
                    if(d1==false){
                        d1=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==2){
                    if(d2==false){
                        d2=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==3){
                    if(d3==false){
                        d3=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==4){
                    if(d4==false){
                        d4=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==5){
                    if(d5==false){
                        d5=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==6){
                    if(d6==false){
                        d6=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==7){
                    if(d7==false){
                        d7=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==8){
                    if(d8==false){
                        d8=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}                                 
            }if(letra=='E'){
                    if(nro==1){                          
                        if(e1==false){
                        e1=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==2){
                    if(e2==false){
                        e2=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==3){
                    if(e3==false){
                        e3=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==4){
                    if(e4==false){
                        e4=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==5){
                    if(e5==false){
                        e5=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==6){
                    if(e6==false){
                        e6=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==7){
                    if(e7==false){
                        e7=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==8){
                    if(e8==false){
                        e8=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}                                 
            }if(letra=='F'){
                if(nro==1){
                    if(f1==false){
                        f1=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==2){
                    if(f2==false){
                        f2=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==3){
                    if(f3==false){
                        f3=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==4){
                    if(f4==false){
                       f4=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==5){
                    if(f5==false){
                        f5=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==6){
                    if(f6==false){
                        f6=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==7){
                    if(f7==false){
                        f7=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}    
                if(nro==8){
                    if(f8==false){
                        f8=true;
                    }else{
                        System.out.println("El asiento ya a sido reservado antes");
                        continue;
                    }}                                
            }//fin de seleccion de una silla, hacia abajo repetir procesos
            letraS=String.valueOf(letra);
            nroS=String.valueOf(nro);
            elegida=letraS+nro;
                System.out.println("Has reservado el asiento "+elegida);//asigancion de valor, dentro del bucle para que incremente
                if(letra=='A' ||letra=='B'){ 
                    valor=30000;
                    valorReal=valorReal+valor;
                    System.out.println("Ingrese la edad de quien ocupará en asiento");
                    edad=tc.nextInt();
                    if(edad<18 && edad>0){
                        valorDescuento=(valor/100)*10;
                        valor=valor-valorDescuento;
                        valorDescuentoTotal=valorDescuentoTotal+valorDescuento;
                    }
                    if(edad>65 && edad<120){
                        valorDescuento=(valor/100)*15;
                        valor=valor-valorDescuento;
                        valorDescuentoTotal=valorDescuentoTotal+valorDescuento;
                    }
                }
                if(letra=='C' ||letra=='D'){ 
                    valor=20000;
                    valorReal=valorReal+valor;
                    System.out.println("Ingrese la edad de quien ocupará en asiento");
                    edad=tc.nextInt();
                    if(edad<18 && edad>0){
                        valorDescuento=(valor/100)*10;
                        valor=valor-valorDescuento;
                        valorDescuentoTotal=valorDescuentoTotal+valorDescuento;
                    }
                    if(edad>65 && edad<120){
                        valorDescuento=(valor/100)*15;
                        valor=valor-valorDescuento;
                        valorDescuentoTotal=valorDescuentoTotal+valorDescuento;
                    }
                }
                if(letra=='E' ||letra=='F'){ 
                    valor=10000;
                    valorReal=valorReal+valor;
                    System.out.println("Ingrese la edad de quien ocupará en asiento");
                    edad=tc.nextInt();
                    if(edad<18 && edad>0){
                        valorDescuento=(valor/100)*10;
                        valor=valor-valorDescuento;
                        valorDescuentoTotal=valorDescuentoTotal+valorDescuento;
                    }
                    if(edad>65 && edad<120){
                        valorDescuento=(valor/100)*15;
                        valor=valor-valorDescuento;
                        valorDescuentoTotal=valorDescuentoTotal+valorDescuento;
                    }
                }
                resumenAsientos=resumenAsientos+" "+elegida+",";
                valorTotal=valorTotal+valor;
                valorDescuento=0;
                valor=0;
                System.out.println("¿Desea reservar otro asiento?");
                System.out.println("Ingrese '1' para seguir comprando | Presione '2' para terminar su compra");
            nuevoAsiento=tc.nextInt();             
            if(nuevoAsiento==2){
                System.out.println("Preparando carro de compra");
                sillaOk=true;            
            }                    
            }   //termino de while de compra
            while(valorTotal!=0){//inicio de carro de compras
                System.out.println("Los asientos incluidos en su compra son: "+resumenAsientos);
                System.out.println("Sus asientos tienen un costo de $"+valorReal);
                System.out.println("El descuento aplicado es de $"+valorDescuentoTotal);
                System.out.println("El total a pagar es de $"+valorTotal);
                System.out.println("");
                System.out.println("Ingrese el total de su boleta");
                pago=tc.nextInt();
                if (pago<valorTotal) {
                    System.out.println("Ingrese el total por favor");
                    pago=tc.nextInt();
                    continue;
                } else if(pago>valorTotal){
                    vuelto=pago-valorTotal;
                    System.out.println("Su vuelto es de $"+vuelto);
                    System.out.println("Muchas gracias por su compra");
                    System.out.println("Volviendo a la pagina principal\n");                    
                }else{
                    System.out.println("Muchas gracias por su compra");
                    System.out.println("Volviendo a la pagina principal\n");
                }
                pago=0;
                vuelto=0;
                resumenAsientos="";
                valorReal=0;
                valorDescuentoTotal=0;
                valorTotal=0;
            }
        }
        
        ciclo=0;
        if(menu==2){ //Tremino del programa
            System.out.println("Muchas gracias por su visita");
            ciclo=1;
            //Agregar variable de compra o solo visita
        }
        }
           
           

    }
    }


