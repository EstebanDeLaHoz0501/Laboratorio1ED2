Laboratorio 1 — Árbol AVL
Estructura de Datos II · Universidad del Norte

Integrantes

  Esteban De La Hoz
  Luna Santos Gomez
  Gabriel Felipe Lizcano Gonzalez

Descripción
  Implementación de un árbol AVL que se autobalancea, construido a 
  partir del dataset de cursos de Udemy (dataset_courses_with_reviews.csv). 
  El árbol usa el nivel de satisfacción del curso como métrica de comparación.

Requisitos

  Apache NetBeans 15 - 25
  Graphviz 9

Proceso de Instalación de Graphviz 9.0

  ## Requisitos de Instalación: Graphviz (v9.0)
  
  Para el correcto funcionamiento del módulo de visualización de árboles AVL, es necesario contar
  con la herramienta **Graphviz** instalada y configurada en el sistema.
  
  ### 1. Descarga del instalador
  1. Acceda al sitio oficial de [Graphviz Download](https://graphviz.org/download/).
     link directo:
       https://translate.google.com/website?sl=en&tl=es&hl=es&client=srp&u=https://gitlab.com/api/v4/projects/4207231/packages/generic/graphviz-releases/9.0.0/windows_10_cmake_Release_graphviz-install-9.0.0-win64.exe
  3.  Localice la sección de **Windows** y descargue el instalador ejecutable (`.exe` o `.msi`) correspondiente a la versión **9.0.0** (arquitectura de 64 bits recomendada).
  
  ### 2. Instalación y Configuración del PATH
  Durante la ejecución del instalador, siga estos pasos críticos 
  para asegurar la integración con el entorno de ejecución de Java:
  
  * **Paso fundamental:** En la ventana de opciones de configuración, seleccione la opción:
      > **"Add Graphviz to the system PATH for all users"**
  * Esta acción permite que la aplicación invoque el comando `dot` desde cualquier
    directorio, facilitando la generación automática de imágenes.
  
  ### 3. Verificación del entorno
  Una vez finalizada la instalación, valide que el sistema reconoce la herramienta:
  1. Abra una terminal de comandos (CMD o PowerShell).
  2. Ejecute el siguiente comando:
     
  **bash**
  dot -V
     
  4. El sistema debe retornar la confirmación: `dot - graphviz version 9.0.0`.
  
  ### 4. Configuración Manual (Alternativa)
  En caso de que el comando no sea reconocido tras la instalación, añada la ruta de los binarios manualmente:
  1. Diríjase a: *Propiedades del sistema > Variables de entorno*.
  2. En **Variables del sistema**, edite la variable `Path`.
  3. Añada una nueva entrada con la ruta de instalación (por defecto): 
     `C:\Program Files\Graphviz\bin`
  4. Reinicie el IDE (NetBeans) para aplicar los cambios.
  ---


Estructura del proyecto:

  src/
  ├── Core/
  │   ├── Curso.java
  │   ├── CursoManager.java
  │   ├── Nodo.java
  │   └── dataset_courses_with_reviews.csv
  ├── Core.Arbol/
  │   ├── AVL.java
  │   ├── Graphviz.java
  │   ├── OperacionesArbol.java
  │   └── Rotaciones.java
  ├── Core.controllers/
  │   └── Controladores.java
  ├── Core.controllers.utils/
  │   ├── Response.java
  │   └── Status.java
  ├── Interfaz/
  │   └── interfaz.java
  └── Main/
      └── main.java












    
