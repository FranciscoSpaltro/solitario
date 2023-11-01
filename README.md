# Importante (ETAPA 2)
La entrega forma parte de la rama MAIN

# Trabajo Práctico: Solitario
## (95.02) Algoritmos y Programación III

## Integrantes
| Alumno | Padrón | Correo | Usuario Github |
| ----------- | ----------- | ----------- | ----------- |
| Leandro Peña | 102282 | lpena@fi.uba.ar | lxxndrx07 |
| Francisco Spaltro | 102098 | fspaltro@fi.uba.ar | fran855 |

## Corrector
Diego Essaya

# Pruebas a implementar
- Inicializar juego y verificar los tamaños: 7 pilas con 1, 2, ..., 7 cartas, 4 cimientos vacíos y 1 mazo con 24
- Mover una carta de una pila a otra (bien)
- Mover una carta de una pila a otra (mismo color)
- Mover una carta de una pila a otra (no secuencial)
- Mover una carta de una pila a un cimiento (bien)
- Mover una carta de una pila a un cimiento (no as con error)
- Mover una carta de una pila a un cimiento (un dos después de un as, bien)
- Mover un conjunto de cartas de una pila a otra (bien)
- Mover un conjunto de cartas de una pila a otra (no secuencial)
- Mover una carta de la basura a una pila
- Mover una carta de la basura a un cimiento
- Verificar que haya máximo 3 cartas visibles en la basura al agregar una luego de la 3ra





# Definir
- Clase abstracta PilaDeCartas: base para las pilas de cartas en el juego (Mazo, Basura, Cimiento y Pilas/Tableau). Tendría atributos de Stack de Carta y métodos cantidadCartas(), estaVacia(), extraer() [en principio, la última]

- Clase Mazo (hereda de PilaDeCartas):
Implementar: 
  - mezclar()

- Clase Basura (hereda de PilaDeCartas):
Implementar:
  - mostrarUltimasTres()
  - agregarCarta()

- Clase Cimiento (hereda de PilaDeCartas):
Implementar:
  - agregarCarta()

- Clase Pilas/Tableau (hereda de PilaDeCartas):
  - Hay que ver qué pasa con los dos arreglos en atributos
Implementar:
  - mostrarVisibles()
  - agregarCartas(int n)
  - cantidadCartasOcultas()
  - cantidadCartasVisibles()
Override:
- extraer()
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

### Atributos:
- palo [String]
- numero ~~[String]~~ [char]
- bocaArriba [boolean]

### Métodos:
- String verPalo()
- String verNumero()
- boolean estaBocaArriba()
- boolean darVuelta()


## 3. Mazo/Stock
- PALOS = ["picas", "diamantes", "treboles", "corazones"]
- VALORES = ['1', '2'', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K']

### Atributos:
- cartas [Stack de Carta]

### Métodos
- ~~void inicializarMazo()~~
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
