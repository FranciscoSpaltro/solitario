# Trabajo Práctico: Solitario
## (95.02) Algoritmos y Programación III

## Integrantes
| Alumno | Padrón | Correo | Usuario Github |
| ----------- | ----------- | ----------- | ----------- |
| Leandro Peña | 102282 | lpena@fi.uba.ar | lxxndrx07 |
| Francisco Spaltro | 102098 | fspaltro@fi.uba.ar | fran855 |

## Corrector
Diego Essaya

---------------------------------
# Clases:
## 0. Solitario
### Atributos:
- variante
- Mazo mazo
- Lista de Cimiento cimientos
- Lista de Pila pilas
- Basura basura

### Métodos:
- void inicializar() [pide la variante]
- bool jugadorGano()
- bool jugadorPerdio()
- void reiniciar()

- void repartirCartas(Mazo)
- bool moverPilaAPila(pilaOrigen, pilaDestino, n)
- bool moverPilaACimiento(pila, cimiento)
- bool moverBasuraAPila(pila)
- bool moverBasuraACimiento(cimiento)

## 1. Klondike [Implementa a Solitario]


## 2. Carta

- private/public static final String[] PALOS = ["picas", "diamantes", "treboles", "corazones"]
- private/public static final String[] NUMEROS = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"]

### Atributos:
- palo [String]
- numero [String]
- bocaArriba [bool]

### Métodos:
- String verPalo()
- String verNumero()
- bool estaBocaArriba()
- void darVuelta()


## 3. Mazo/Stock
### Atributos:
- cartas [Stack de Carta]

### Métodos
- void inicializarMazo()
- void mezclar()		
- Carta extraerUltima()
- int cantidadCartas()
- bool estaVacia()

## 4. Basura
### Atributos:
- cartas [Stack de Cartas (solo se muestran 3 pero puede haber mas)]

### Métodos:
- Lista de Cartas mostrarUltimasTres() 
- Carta extraerUltima()
- int cantidadCartas()
- void agregarCarta(Carta)
- bool estaVacia()


## 5. Cimiento/Foundation
### Atributos:
- cartas [Stack de Cartas]

### Métodos:
- Carta mostrarUltima()
- void agregarCartas(Stack de Cartas)
- int cantidadCartas()
- bool estaVacia()

## 6. Pilas/Tableau:
### Atributos:
- cartasVisibles [Stack de Cartas]
- cartasOcultas [Stack de Cartas]

### Métodos:
- [Stack de Carta] mostrarVisibles()
- int cantidadCartas()
- int cantidadCartasOcultas() [podría ser privado]
- int cantidadCartasVisibles()
- Stack de Cartas extraerCartas(int n)
- void agregarCartas(Stack de Cartas)
- bool estaVacia()