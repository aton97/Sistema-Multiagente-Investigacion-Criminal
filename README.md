🕵️ Sistema Multiagente de Investigación Criminal con JADE
📌 Descripción

Este proyecto implementa un Sistema Multiagente utilizando la plataforma JADE (Java Agent DEvelopment Framework) para simular el flujo de una investigación criminal. La solución demuestra cómo agentes inteligentes pueden colaborar de manera distribuida mediante el uso de Páginas Amarillas (Directory Facilitator - DF) y mensajes ACL (Agent Communication Language) para resolver un caso de investigación.

El proyecto fue desarrollado como parte del curso Innovación Disruptiva de la Maestría en Ingeniería de Sistemas e Informática.

🎯 Objetivos
Implementar una arquitectura basada en Sistemas Multiagente.
Utilizar el Directory Facilitator (DF) para el descubrimiento dinámico de servicios.
Implementar comunicación entre agentes mediante mensajes ACL.
Simular un proceso colaborativo de investigación criminal.
Demostrar la interacción entre agentes especializados.
🏗 Arquitectura del Sistema
                    Escena del Crimen
                            │
                            ▼
          Directory Facilitator (Páginas Amarillas)
                   /                     \
                  /                       \
                 ▼                         ▼
      Laboratorio ADN            Laboratorio Huellas
                  \                       /
                   \                     /
                    ▼                   ▼
                 Perfilador Criminal
                            │
                            ▼
                         Fiscal
🤖 Agentes del Sistema
🔍 EscenaCrimen

Responsabilidades:

Detectar evidencias.
Buscar servicios utilizando el Directory Facilitator.
Enviar evidencias a los laboratorios.
Recibir los resultados del análisis.
Enviar la información consolidada al Perfilador Criminal.
🧬 LaboratorioADN

Servicio registrado:

analisis-adn

Funciones:

Analizar muestras biológicas.
Procesar sangre y cabello.
Generar resultados del análisis genético.
🖐 LaboratorioHuellas

Servicio registrado:

analisis-huellas

Funciones:

Analizar huellas dactilares.
Determinar coincidencias con registros criminales.
🕵 PerfiladorCriminal

Servicio registrado:

perfil-criminal

Funciones:

Integrar la información proveniente de los laboratorios.
Elaborar un perfil del sospechoso.
Determinar el nivel de sospecha.
⚖ Fiscal

Servicio registrado:

informe-fiscal

Funciones:

Recibir el perfil criminal.
Emitir el informe final del caso.
Presentar la conclusión de la investigación.
🔄 Flujo de Funcionamiento
El agente EscenaCrimen detecta una o más evidencias.
Consulta el Directory Facilitator (DF) para localizar los agentes que ofrecen los servicios requeridos.
Envía las evidencias al laboratorio correspondiente.
Los laboratorios realizan el análisis y responden mediante mensajes ACL.
El Perfilador Criminal consolida toda la información recibida.
El Fiscal genera el informe final de la investigación.
💬 Comunicación entre Agentes

La comunicación se realiza mediante mensajes ACL (Agent Communication Language) proporcionados por JADE.

Cada agente puede:

Buscar servicios en el DF.
Enviar mensajes ACL.
Recibir respuestas.
Ejecutar comportamientos autónomos.
Colaborar con otros agentes para resolver el caso.
🛠 Tecnologías Utilizadas
Java 11
JADE 4.5
Directory Facilitator (DF)
ACL Messages
Eclipse Adoptium JDK 11
Visual Studio Code
📂 Estructura del Proyecto
HomeWork/
│
├── jade.jar
├── README.md
├── .gitignore
│
└── InvestigacionCriminal/
    ├── EscenaCrimen.java
    ├── LaboratorioADN.java
    ├── LaboratorioHuellas.java
    ├── PerfiladorCriminal.java
    ├── Fiscal.java
    ├── APDescription.txt
    ├── MTPs-Main-Container.txt
    └── out_2/

# 🚀 Cómo ejecutar el proyecto

## 📋 Requisitos

Antes de ejecutar el proyecto asegúrate de contar con:

* Java JDK 11 o superior
* JADE 4.5 (`jade.jar`)
* Visual Studio Code o cualquier IDE para Java

La estructura del proyecto debe mantenerse de la siguiente forma:

```text
HomeWork
│
├── jade.jar
│
└── InvestigacionCriminal
    ├── EscenaCrimen.java
    ├── LaboratorioADN.java
    ├── LaboratorioHuellas.java
    ├── PerfiladorCriminal.java
    ├── Fiscal.java
    ├── APDescription.txt
    ├── MTPs-Main-Container.txt
    └── out_2
```

---

# ⚙ Paso 1. Compilar el proyecto

Ubicarse dentro de la carpeta:

```text
InvestigacionCriminal
```

Ejecutar el siguiente comando:

```bash
javac -d out_2 -cp ".;..\jade.jar" *.java
```

### ¿Qué hace este comando?

| Parámetro       | Descripción                                        |
| --------------- | -------------------------------------------------- |
| `javac`         | Compilador de Java                                 |
| `-d out_2`      | Guarda los archivos `.class` en la carpeta `out_2` |
| `-cp`           | Define el ClassPath                                |
| `.;..\jade.jar` | Incluye la biblioteca JADE ubicada un nivel arriba |
| `*.java`        | Compila todos los archivos Java del proyecto       |

---

# ▶ Paso 2. Ejecutar el sistema

Una vez compilado correctamente, ejecutar:

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(sangre,huella)"
```

### ¿Qué hace este comando?

| Parámetro             | Descripción                                  |
| --------------------- | -------------------------------------------- |
| `java`                | Ejecuta la Máquina Virtual de Java           |
| `-cp`                 | Define el ClassPath                          |
| `.;..\jade.jar;out_2` | Incluye JADE y las clases compiladas         |
| `jade.Boot`           | Inicia la plataforma JADE                    |
| `-gui`                | Abre la interfaz gráfica (RMA)               |
| `-agents`             | Crea automáticamente los agentes del sistema |

---

# 🧪 Casos de prueba

## Caso 1: Análisis de sangre y huella

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(sangre,huella)"
```

---

## Caso 2: Solo análisis de sangre

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(sangre)"
```

---

## Caso 3: Solo análisis de huellas

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(huella)"
```

---

## Caso 4: Solo análisis de cabello

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(cabello)"
```

---

# 📌 Resultado esperado

Al ejecutar correctamente el proyecto se observará:

* Apertura de la interfaz gráfica **JADE RMA**.
* Registro automático de los agentes en la plataforma.
* Registro de servicios en el **Directory Facilitator (DF)**.
* Descubrimiento de servicios mediante las **Páginas Amarillas**.
* Comunicación entre agentes utilizando mensajes **ACL**.
* Procesamiento colaborativo de la evidencia.
* Generación del informe final por parte del agente **Fiscal**.

