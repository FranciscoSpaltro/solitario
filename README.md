# Importante (ETAPA 2)
La entrega forma parte de la rama MAIN

# Trabajo Práctico: modelo.Solitario
## (95.02) Algoritmos y Programación III

## Integrantes
| Alumno | Padrón | Correo | Usuario Github |
| ----------- | ----------- | ----------- | ----------- |
| Leandro Peña | 102282 | lpena@fi.uba.ar | lxxndrx07 |
| Francisco Spaltro | 102098 | fspaltro@fi.uba.ar | fran855 |

## Corrector
Diego Essaya

# Correcciones de la etapa 2
- [x] El atributo basura solo se usa en Klondike. Spider lo hereda pero no lo usa para nada
  > Se eliminó el atributo basura de Solitario y pasó a ser parte solo de Klondike
- [x] ¿Cuál es la razón por la que eligieron usar herencia para Spider y sus dos variantes? Pregunto porque no veo que usen polimorfismo (por ejemplo, alguna función que reciba un Spider). Si no hay polimorfismo, tal vez es mejor usar composición (por ejemplo, Strategy). Es una sugerencia, tal vez en la etapa 3 sacan provecho de esta herencia.
  > Se aplicó Strategy
- [ ] Hay bastante repetición de código en los diferentes constructores de Spider
  > FALTA. Son uno para fácil, otro para difícil y el doble de eso por las pruebas
- [ ] También veo un poco de código repetido entre Klondike y Spider. Por ejemplo, ambas implementaciones de moverPilaAPila terminan más o menos igual (la parte de anexarCartas)
  > FALTA
- [x] Veo también "números mágicos": 5, 8, 10, 13... que deberían ser constantes o valores que se obtienen a partir de algún cálculo
  > Se creó la interfaz Constantes para definir allí estos números
- [x] La carpeta target no debe ser commiteada en el repositorio, ya que contiene archivos compilados. Solo se debe incluir en el repositorio el código fuente y todo lo que sea indispensable para compilar y ejecutar el programa
  > Se creó un .gitignore que incluya /java/target
- [x] Idealmente cualquier archivo que se escribe en un test debería ser eliminado al finalizar el test
  > Se modificó el test para que elimine el archivo al final (solución transitoria)
- [ ] Mejor aun: pueden modificar el test para que no se escriba ningún archivo, como vimos en clase
  > IMPLEMENTAR