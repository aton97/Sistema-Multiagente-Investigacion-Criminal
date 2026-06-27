# 🕵️ Sistema Multiagente de Investigación Criminal con JADE

## 📌 Descripción

Este proyecto implementa un **Sistema Multiagente** utilizando la plataforma **JADE (Java Agent DEvelopment Framework)** para simular un proceso de investigación criminal. La solución demuestra cómo agentes inteligentes colaboran de forma distribuida mediante el uso de **Páginas Amarillas (Directory Facilitator - DF)** y **mensajes ACL (Agent Communication Language)** para resolver un caso de investigación.

El proyecto fue desarrollado como parte del curso **Innovación Disruptiva** de la **Maestría en Ingeniería de Sistemas e Informática**.

---

# 🚀 Cómo ejecutar el proyecto

## 📋 Requisitos

Antes de ejecutar el proyecto asegúrate de contar con:

* Java JDK 11 o superior
* JADE 4.5 (`jade.jar`)
* Visual Studio Code (o cualquier IDE compatible con Java)

La estructura del proyecto debe mantenerse de la siguiente forma:

```text
HomeWork
│
├── jade.jar
├── README.md
├── .gitignore
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

Ejecutar:

```bash
javac -d out_2 -cp ".;..\jade.jar" *.java
```

### Explicación del comando

| Parámetro       | Descripción                                                   |
| --------------- | ------------------------------------------------------------- |
| `javac`         | Compilador de Java                                            |
| `-d out_2`      | Guarda los archivos compilados (.class) en la carpeta `out_2` |
| `-cp`           | Define el ClassPath                                           |
| `.;..\jade.jar` | Incluye la biblioteca JADE ubicada un nivel arriba            |
| `*.java`        | Compila todos los archivos Java del proyecto                  |

---

# ▶ Paso 2. Ejecutar el sistema

Ejecutar el siguiente comando:

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(sangre,huella)"
```

### Explicación del comando

| Parámetro             | Descripción                                  |
| --------------------- | -------------------------------------------- |
| -----------           | -------------                                |
| `java`                | Ejecuta la Máquina Virtual de Java           |
| `-cp`                 | Define el ClassPath                          |
| `.;..\jade.jar;out_2` | Incluye JADE y las clases compiladas         |
| `jade.Boot`           | Inicia la plataforma JADE                    |
| `-gui`                | Abre la interfaz gráfica (RMA)               |
| `-agents`             | Crea automáticamente los agentes del sistema |

---

# 🧪 Casos de prueba

## Caso 1 – Evidencias: sangre y huella

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(sangre,huella)"
```

---

## Caso 2 – Evidencia: sangre

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(sangre)"
```

---

## Caso 3 – Evidencia: huella

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(huella)"
```

---

## Caso 4 – Evidencia: cabello

```bash
java -cp ".;..\jade.jar;out_2" jade.Boot -gui -agents "adn:LaboratorioADN;huellas:LaboratorioHuellas;perfil:PerfiladorCriminal;fiscal:Fiscal;escena:EscenaCrimen(cabello)"
```

---

# 📌 Resultado esperado

Al ejecutar correctamente el sistema se observará:

* Apertura de la interfaz gráfica **JADE RMA**.
* Registro automático de los agentes en la plataforma.
* Registro de servicios en el **Directory Facilitator (DF)**.
* Descubrimiento dinámico de servicios mediante las **Páginas Amarillas**.
* Comunicación entre agentes utilizando mensajes **ACL**.
* Procesamiento colaborativo de las evidencias.
* Generación del informe final por parte del agente **Fiscal**.

---

# 🎯 Objetivos

* Implementar una arquitectura basada en Sistemas Multiagente.
* Utilizar el **Directory Facilitator (DF)** para el descubrimiento dinámico de servicios.
* Implementar comunicación entre agentes mediante mensajes ACL.
* Simular un proceso colaborativo de investigación criminal.
* Demostrar la interacción entre agentes especializados.

---

# 🏗 Arquitectura del Sistema

```text
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
```

---

# 🤖 Agentes del Sistema

## 🔍 EscenaCrimen

Responsabilidades:

* Detectar evidencias.
* Buscar servicios utilizando el Directory Facilitator.
* Enviar evidencias a los laboratorios especializados.
* Recibir resultados del análisis.
* Enviar la información consolidada al Perfilador Criminal.

---

## 🧬 LaboratorioADN

**Servicio registrado:** `analisis-adn`

Funciones:

* Analizar muestras biológicas.
* Procesar evidencias de sangre y cabello.
* Generar resultados del análisis genético.

---

## 🖐 LaboratorioHuellas

**Servicio registrado:** `analisis-huellas`

Funciones:

* Analizar huellas dactilares.
* Determinar coincidencias con registros criminales.

---

## 🕵 PerfiladorCriminal

**Servicio registrado:** `perfil-criminal`

Funciones:

* Integrar la información recibida de los laboratorios.
* Elaborar un perfil del sospechoso.
* Determinar el nivel de sospecha.

---

## ⚖ Fiscal

**Servicio registrado:** `informe-fiscal`

Funciones:

* Recibir el perfil criminal.
* Emitir el informe final del caso.
* Presentar la conclusión de la investigación.

---

# 🔄 Flujo de Funcionamiento

1. El agente **EscenaCrimen** detecta una o más evidencias.
2. Consulta el **Directory Facilitator (DF)** para localizar los agentes especializados.
3. Envía las evidencias al laboratorio correspondiente.
4. Los laboratorios realizan el análisis y responden mediante mensajes ACL.
5. El **Perfilador Criminal** consolida los resultados.
6. El **Fiscal** genera el informe final del caso.

---

# 💬 Comunicación entre Agentes

Los agentes utilizan **ACL Messages** para intercambiar información y colaborar durante la investigación.

Cada agente puede:

* Registrarse en el Directory Facilitator.
* Descubrir servicios ofrecidos por otros agentes.
* Enviar mensajes ACL.
* Recibir respuestas.
* Ejecutar comportamientos autónomos y colaborativos.

---

# 🛠 Tecnologías Utilizadas

* Java 11
* JADE 4.5
* Directory Facilitator (DF)
* ACL Messages
* Visual Studio Code
* Git
* GitHub

---

# 📸 Evidencias

El funcionamiento del sistema fue validado mediante:

* Consola de ejecución de JADE.
* JADE Remote Agent Management (RMA).
* Registro de agentes en el Directory Facilitator.
* Comunicación entre agentes mediante mensajes ACL.
* Casos de prueba con diferentes evidencias.

---

# 👨‍💻 Autor

**Fabricio Prado, Sevastian Salcedo y Marco Rivera**

Maestría en Ingeniería de Sistemas e Informática

Curso: Innovación Disruptiva

Universidad Nacional Mayor de San Marcos

---

# 📄 Licencia

Este proyecto fue desarrollado con fines académicos y de investigación.


