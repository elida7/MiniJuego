# Minijuego - Atrapa los Objetos

Un minijuego interactivo desarrollado en Java con interfaz gráfica Swing, implementando la arquitectura MVC (Model-View-Controller).

## Descripción del Juego

"Atrapa los Objetos" es un juego donde el jugador controla una canasta que se mueve horizontalmente para atrapar objetos que caen desde arriba. El objetivo es atrapar los objetos verdes (buenos) para ganar puntos y evitar los objetos rojos (malos) que quitan vidas.

## Arquitectura MVC

El proyecto está estructurado siguiendo el patrón Model-View-Controller:

### Model (Modelo)
Contiene la lógica del juego y los datos:
- **`Jugador.java`**: Representa al jugador, su posición, puntuación y colisiones
- **`ObjetoCaida.java`**: Representa los objetos que caen (buenos o malos)
- **`Juego.java`**: Clase principal del modelo que gestiona el estado del juego, objetos, vidas y puntuación

### View (Vista)
Contiene la interfaz gráfica de usuario:
- **`VentanaJuego.java`**: Ventana principal con Swing que muestra el juego, controles y información

### Controller (Controlador)
Coordina la comunicación entre Model y View:
- **`JuegoController.java`**: Maneja eventos, actualiza el modelo y coordina el dibujado en la vista

**Elida Cubaque**
**CI: 27418291**