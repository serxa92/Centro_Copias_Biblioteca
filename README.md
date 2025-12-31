# 🖨️ Centro de Copias de la Biblioteca (Java)

Proyecto desarrollado en **Java** para simular el funcionamiento de un centro de copias en una biblioteca utilizando **programación concurrente**.

El objetivo principal es practicar el uso de **hilos**, **exclusión mutua** y **coordinación entre hilos** mediante `synchronized`, `wait()` y `notifyAll()`.

---

## 📚 Contexto del problema

En la biblioteca existe un centro de copias utilizado por estudiantes:

- Hay **5 estudiantes** que quieren hacer copias.
- El centro dispone de **2 máquinas de copiado**.
- Cada máquina solo puede ser utilizada por **un estudiante a la vez**.
- Si no hay máquinas libres, los estudiantes deben **esperar**.

Cada estudiante repite continuamente el siguiente ciclo:

1. Estudia durante un tiempo aleatorio
2. Va al centro de copias
3. Solicita una máquina
4. Realiza las copias
5. Libera la máquina y vuelve a estudiar

---

## 🎯 Objetivo del proyecto

Simular correctamente este escenario en Java garantizando:

- Uso correcto de **hilos**
- **Exclusión mutua** en el acceso a las máquinas
- **Espera y notificación** entre hilos (`wait()` / `notifyAll()`)
- Ejecución controlada durante **20 segundos**
- Finalización limpia de todos los hilos
- Resumen final por consola

---

## 🧠 Conceptos utilizados

- `Thread` y `Runnable`
- Métodos `synchronized`
- Comunicación entre hilos con `wait()` y `notifyAll()`
- Control de recursos compartidos
- Interrupción y finalización de hilos (`interrupt()`, `join()`)

---

## ▶️ Cómo ejecutar el proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/serxa92/centro-copias-biblioteca.git
   ```





