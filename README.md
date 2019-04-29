# Predictor del clima

## Índice
- [Predictor del clima](#predictor-del-clima)
  - [Índice](#%C3%ADndice)
  - [Problema](#problema)
    - [Premisas](#premisas)
    - [Condiciones climáticas](#condiciones-clim%C3%A1ticas)
    - [Requisitos funcionales](#requisitos-funcionales)
    - [Bonus](#bonus)
  - [Solución](#soluci%C3%B3n)
    - [Características físicas del sistema](#caracter%C3%ADsticas-f%C3%ADsicas-del-sistema)
    - [Climas posibles](#climas-posibles)
    - [API](#api)
      - [Tecnologías utilizadas](#tecnolog%C3%ADas-utilizadas)
      - [Dependencias](#dependencias)
      - [Endpoints](#endpoints)
      - [SimulatorController](#simulatorcontroller)
      - [WPController](#wpcontroller)
    - [Datos asumidos](#datos-asumidos)
  - [Aclaraciones](#aclaraciones)
  - [Bibliografía](#bibliograf%C3%ADa)

## Problema

En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada
civilización vive en paz en su respectivo planeta.

### Premisas
* El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
horario. Su distancia con respecto al sol es de 500Km.
* El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
horario. Su distancia con respecto al sol es de 2000Km.
* El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
anti­horario, su distancia con respecto al sol es de 1000Km.
* Todas las órbitas son circulares. 

### Condiciones climáticas
* Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
sistema solar experimenta un período de sequía.
* Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están
alineados entre sí pero no están alineados con el sol.
* Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el momento en el que el sol se encuentra dentro del triángulo 
* El sistema solar experimenta un
período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en
su máximo.

### Requisitos funcionales
Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá?
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?

### Bonus
Para poder utilizar el sistema como un servicio a las otras civilizaciones, los Vulcanos requieren
tener una base de datos con las condiciones meteorológicas de todos los días y brindar una API
REST de consulta sobre las condiciones de un día en particular.

1. Generar un modelo de datos con las condiciones de todos los días hasta 10 años en adelante
utilizando un job para calcularlas.
2. Generar una API REST la cual devuelve en formato JSON la condición climática del día
consultado.
3. Hostear el modelo de datos y la API REST en un cloud computing libre (como APP Engine o
Cloudfoudry) y enviar la URL para consulta:

## Solución
Para un mejor entendimiento de los datos brindados se mostrarán en forma de tablas. A continuación las características físicas del sistema, velocidad angular, y distancia de cada planeta con respecto al sol. Si bien no tenemos explícitamente el dato del perído de cada planeta (tiempo en completar una vuelta alrededor del sol) se considera de utilidad mostrarlo.

### Características físicas del sistema
| Planeta | Velocidad Angular (grado/día)| Radio (Km) | Período (días) |
| --- | :---: | :---: | :---: |
| **Betasoide** | 3 | 2000 | 120 |
| **Vulcano** | -5 | 1000 | 360 | 
| **Ferengi** | 1 | 500 | 720 |

### Climas posibles

Tomando como bases las condiciones climaologícas presentadas en el problema se confecciona la siguiente tabla. Al analizar las posibles variantes, se decide asumir una condicón climática "Nublado" para el caso de que los planetas formen un triángulo con el sol fuera, ya que no queda explícito en los datos iniciales del problema.

| Disposición de los planetas | Clima | 
| --- | :--- |
| Sequía | todos los elementos alineados (planetas y sol) |
|Día Hermoso | planetas alineados entre ellos pero no con el sol | 
| Lluvia | planetas formando un triángulo con el sol dentro |
| Lluvia Intensa | planetas formando un triángulo con el sol dentro (perímetro máximo) |
| Nublado | planetas formando un triángulo con el sol fuera |

### API
Para dar solución a los requerimientos se diseñó una arquitectura monolítica teniendo en cuenta el tamaño y necesidad de procesamiento de la aplicación. Se utilizó una arquitectura MVC con una base de datos embevida (H2).

#### Tecnologías utilizadas
* [Spring-Boot 2.1.4.][spring]
* [Java 8.][java]
* [Maven 3.5.4.][maven]

#### Dependencias
* spring-boot-starter-cache
* spring-boot-starter-data-rest
* spring-boot-devtools
* spring-boot-starter-test
* org.springframework.cloud
* springfox-swagger2
* springfox-swagger-ui
* h2
  
#### Endpoints
Los endpoints fueron divididos en dos controladores, [SimulatorController][simulator-service] y [WPController][wp-service] (abreviatura de Weather Prediction). En el primero solo se encuentra el que desencadena la simulación, y en el segundo están los que actúan sobre el recurso WeatherPrediction.

#### SimulatorController
| Operación | Método HTTP | Ruta |
| :--- | :--- | :--- |
| Simula sistema solar y paso del tiempo (pasado por parámetros o en su defecto 10 años vulcanos) | POST | [/simulate][simulate] |
 
#### WPController
| Operación | Método HTTP | Ruta |
| :--- | :--- | :--- |
| Obtiene todos predicción del clima el día específicado en el request | GET | [/weather][weather] |
| Obtiene la cantidad de días de sequía | GET | [/drought-days][drought-days] |
| Obtiene cantidad de días de lluvia | GET | [/rainy-days][rainy-days] |
| Obtiene cantidad de días de lluvia intensa | GET | [hardRainy-days][hardRainy-days] |
| Obtiene cantidad de días con condiciones óptimas | GET | [/beautiful-days][beautiful-days] |
| Obtiene cantidad de días nublados | GET | [/cloudy-days][cloudy-days] |

### Datos asumidos
* Se tomará que un año es igual a 72 días, que es la duración del planeta Vulcano, ya que son los que consumirán la API según el enunciado del problema.
* Se representarán los planetas en coordenadas polares, aunque para realizar algunos de los cálculos se hará la conversión a cartesianas con una presición de dos lugares decimáles.
* El estado inicial del sistema solar será con todos los planetas ubicados sobre el eje polar.
* Se iniciará la simulación el día 0, y a continuación se procederá a simular 10 años (720 días) en adelante, por lo que al iniciar el programa veremos que tenemos 721 días simulados (el día actual + 720 días en edelante).

## Aclaraciones
En aras de agilizar el desarrollo, no se implementó ninguna seguridad para prevenir denegación de servicios y uso de fuerza bruta. No obstante se podría llegar a desarrollar fácilmente utilizando Spring Security. 

## Bibliografía
* https://www.baeldung.com/spring-boot-app-deploy-to-cloud-foundry
* https://www.gamedev.net/forums/topic/295943-is-this-a-better-point-in-triangle-test-2d/
* http://www.mathhands.com/104/hw/104c06s03ns.pdf
* http://www.math.com/tables/geometry/circles.htm
* https://www.gamedev.net/forums/topic/295943-is-this-a-better-point-in-triangle-test-2d/

[spring]:https://spring.io/projects/spring-boot
[maven]:https://maven.apache.org
[java]:https://www.java.com/es/download/faq/java8.xml
[simulator-service]:sdsds
[wp-service]:dsds
[simulate]:asdasd
[weather]:asdsa
[drought-days]:dsds
[rainy-days]:hjsd
[hardRainy-days]:jhsdjsd
[beautiful-days]:kajsdksad
[cloudy-days]:jashdj