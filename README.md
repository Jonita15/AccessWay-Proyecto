Características Principales
Cálculo de Rutas Óptimas: Implementación del Algoritmo de Dijkstra para determinar el camino más corto entre dos puntos.
Visualización Dinámica: Renderizado de rutas en tiempo real sobre el plano utilizando la librería Graphics2D de Java.
Navegación Multinivel: Capacidad para conectar diferentes plantas (Planta Baja y Piso 1) mediante nodos de transición como escaleras y ascensores.
Interfaz de Bienvenida: Pantalla de inicio personalizada que facilita el acceso al sistema de navegación.

🛠️ Tecnologías y Herramientas
Lenguaje: Java (JDK 17+).
IDE: NetBeans 21.
Interfaz Gráfica: Java Swing & AWT.
Control de Versiones: Git & GitHub.

📂 Estructura del Proyecto
El código se organiza en el paquete logic, garantizando una separación clara entre la interfaz y la lógica del negocio:
WelcomeFrame.java: Punto de entrada con la pantalla de bienvenida.
MainFrame.java: Interfaz principal donde se gestiona el mapa y la selección de rutas.
DijkstraEngine.java: Motor lógico que procesa el algoritmo de búsqueda.
Grafo.java & Node.java: Estructuras de datos para la representación del plano.
