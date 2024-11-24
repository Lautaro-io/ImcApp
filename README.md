
# IMC Calculator App

Esta es una aplicación de Android desarrollada en Kotlin que calcula el Índice de Masa Corporal (IMC) en función de los datos de peso, altura, edad y género proporcionados por el usuario.

## Capturas de pantalla

### Pantalla Principal
En esta pantalla, el usuario puede ingresar su altura, peso, edad y seleccionar su género para calcular el IMC.

![image](https://github.com/user-attachments/assets/436e382d-f7f9-4cdf-8c1c-1ead2f65e811)

### Resultado
Después de presionar el botón "Calcular", el usuario verá el resultado de su IMC, junto con una descripción sobre su rango (bajo peso, normal, sobrepeso, obesidad, etc.).

![image](https://github.com/user-attachments/assets/abab92be-8cde-44f2-a1c2-015052b03a96)

## Funcionalidades

- Selección de género: masculino o femenino.
- Ajuste dinámico de altura usando un `SeekBar`.
- Incremento y decremento de peso y edad usando botones.
- Cálculo del IMC y presentación de resultados con descripción textual.
- Interfaz moderna y atractiva, con colores oscuros y elementos destacados.

## Tecnologías y Librerías

- **Lenguaje:** Kotlin
- **Framework:** Android SDK
- **Componentes usados:** `RangeSlider`, `TextView`, `Button`, `CardView`
- Diseño responsivo y adaptado para diferentes tamaños de pantalla.

## Cómo usar

1. Descarga o clona este repositorio.
2. Abre el proyecto en Android Studio.
3. Conecta tu dispositivo o usa un emulador de Android.
4. Ejecuta la aplicación y empieza a calcular el IMC.

## Cálculo del IMC

La fórmula utilizada para el cálculo del IMC es:

\[
IMC = \frac{\text{peso (kg)}}{\text{altura (m)}^2}
\]

### Rangos de IMC

- **Bajo peso:** Menos de 18.5
- **Peso normal:** 18.5 - 24.9
- **Sobrepeso:** 25.0 - 29.9
- **Obesidad:** 30.0 o más

## Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](LICENSE) para más información.

## Contribuciones

Si deseas contribuir a este proyecto, ¡eres bienvenido! Puedes enviar un `pull request` o abrir un `issue` para sugerir mejoras.

## Autor

Desarrollado por [Lautaro Ildarraz].

