# Trabajo Práctico: Solitario
## (95.02) Algoritmos y Programación III

## INFORMACIÓN ETAPA 3
> Consultar PDF "Etapa 3"

## BUGS
- [x] Al subir al cimiento una carta que no corresponde, da vuelta la anteúltima
    >En Klondike, había una negación demás en 

    ```if (!pila.estaVacia() && [!]pila.verUltima().estaBocaArriba())```
    >Solucionado
- [x] Sombreado de cartas falla [**PROBAR**]
    > Se editó VistaCarta y se agregó el atributo ultimaCartaSeleccionada. También se hizo que elimine los efectos al terminar de evaluar un movimiento (ControladorKlondike/Spider)
- [x] Al arrancar el juego y hacer clic en una carta (caso: As de picas) parece que sale un error de "La basura está vacía", probablemente relacionado con el bug anterior
    > Se detecta que al seleccionar una carta y crear un nuevo juego, no se resetea la selección. El problema era que en ControladorSolitario el new DatoMovimiento() se hacía en el atributo y no en el constructor
- [x] Si mueve la ventana, luego de hacer una acción la misma vuelve al centro
  > Eliminé el centrarVentana() de VistaSolitario
- [x] Falta botón OK en "Juego ganado"
  > Agregué el botón en ControladorSolitario
- [x] solitario.txt es binario, no corresponde el txt
  > Se modificó la extensión a .bin
- [ ] Lineas 26-27 de Main, no hace falta el try/catch (relanza la excepción)
   ``` 
  } catch (LoadException e) { // Excepción que lanza si no encuentra el archivo 
     throw e; 
  ```
- [ ]  Líneas 36-43 de Main hay DRY
    ``` 
  if(solitario.obtenerVariante() == Variante.KLONDIKE){ 
     var controladorKlondike = new ControladorKlondike(vistaSolitario, (Klondike) solitario); 
     controladorKlondike.actualizar(); 
     vistaSolitario.iniciar(); 
  } else { 
     var controladorSpider = new ControladorSpider(vistaSolitario, (Spider) solitario); 
     controladorSpider.actualizar(); 
     vistaSolitario.iniciar(); 
    ```
- [x] Línea 18 de Vista Alerta tal vez debería ser responsabilidad de ErrorAlMover (dado un ErrorAlMover error, invocar al método error.obtenerMensaje())
    ```
    private static String obtenerMensaje(ErrorAlMover error) { 
    ```
  > Ahora el mensaje lo devuelve la función static de ErrorAlMover
- [ ] Línea 56 de VistaCarta, ese if y el de abajo son pocos escalables cuando haya N solitarios. Uno de los requisitos es que al agregar un solitario no sea necesario modificar las clases "comunes"
    ```
    if (variante == Variante.KLONDIKE)
    ```
- [ ] Lineas 107-110 y otras de VistaSolitario -> DRY
    ```
     Label puntajeLabel = new Label("PUNTAJE: " + solitario.obtenerPuntos()); 
    puntajeLabel.setLayoutX(pane.getWidth() - 120);
    puntajeLabel.setLayoutY(5);
    puntajeLabel.setFont(new Font("Arial", 15));
    ```
- [ ] Linea 37 de ControladorKlondike, el if quedó muy grande. Parte de esta lógica correspondería al modelo. El try/catch es código repetido
    ```
     if (datosMovimiento.esBasura(datosMovimiento.obtenerListaOrigen())) {
  ```
- [x] Línea 42 de ControladorSolitario, todo lo que tenga que ver con el estilo visual le corresponde a la vista
    ```
   mensajeLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
  ```
  > Cree VistaGanador (faltaría ver si la acción del botón se define ahí o en un controlador)

## Integrantes
| Alumno | Padrón | Correo | Usuario Github |
| ----------- | ----------- | ----------- | ----------- |
| Leandro Peña | 102282 | lpena@fi.uba.ar | lxxndrx07 |
| Francisco Spaltro | 102098 | fspaltro@fi.uba.ar | fran855 |

## Corrector
Diego Essaya
