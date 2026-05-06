# Ejercicio del Bloque 1 — Modelado del dominio de la pizzería

## Contexto

Acabamos de ver, durante el bloque 1, qué es un ORM, qué problema resuelve, y qué papel juega cuando la arquitectura del proyecto es DDD seria.

Antes de tocar Hibernate, antes de poner una sola anotación de JPA, vamos a hacer **lo más importante del curso**: modelar el dominio en código Java puro, sin saber siquiera que existe la palabra "persistencia".

Este ejercicio no se hace para "calentar". Se hace porque **el modelo de dominio que diseñéis aquí es el que vamos a persistir en los siguientes bloques**. Y porque cuando lleguemos a JPA, el choque entre lo que diseñasteis y lo que el motor relacional permite es exactamente la lección que os vais a llevar a casa.

---

## El negocio

Sois el equipo técnico de una pizzería que quiere digitalizar su catálogo. La pizzería tiene:

- Un **catálogo de pizzas**. Cada pizza tiene nombre, descripción, una URL de imagen, y un precio que **no se introduce a mano**: se calcula automáticamente como el coste total de los ingredientes más un margen del 20%.

- Un **catálogo de ingredientes**. Cada ingrediente tiene una descripción y un coste unitario (en euros). Los ingredientes se reutilizan: la mozzarella aparece en muchas pizzas distintas.

- Una funcionalidad de **comentarios**. Los clientes registrados pueden dejar un comentario en una pizza. Cada comentario tiene una fecha, un texto y un autor.

- Un registro de **usuarios**. Cada usuario tiene un nombre y un email.

---

## Las relaciones del modelo

Tal y como están definidas las cosas:

- Una pizza tiene **varios ingredientes**, y un mismo ingrediente puede estar en **varias pizzas**. Relación **muchos a muchos (N:M)**.

- Una pizza puede recibir **muchos comentarios**, y cada comentario pertenece a **una sola pizza**. Relación **uno a muchos (1:N)**.

- Cada comentario lo escribe **un usuario**, y los datos del usuario asociado al comentario aparecen junto a él. Relación **uno a uno (1:1)**, según la especificación inicial del cliente.

> Detrás de esa última relación se esconde una de las decisiones de modelado más interesantes del curso. La discutiremos en el aula.

---

## Lo que tenéis que entregar

Un paquete `domain` con cuatro clases Java puras (POJOs), **sin una sola anotación de JPA**, sin una sola dependencia de Hibernate, sin un solo `import jakarta.persistence.*`.

```
src/main/java/com/example/curso/domain/
    Pizza.java
    Ingrediente.java
    Comment.java
    User.java
```

El proyecto debe compilar sin tener Hibernate añadido como dependencia. Esto es deliberado. Es la prueba de que el dominio está **realmente desacoplado** de la persistencia.

---

## Reglas de negocio a respetar

Estas son las **invariantes** que vuestro dominio tiene que proteger. Una invariante es algo que **siempre** tiene que ser verdad mientras el objeto exista.

### Pizza

- Una pizza **siempre** tiene un nombre no vacío.
- Una pizza **siempre** tiene al menos un ingrediente. Una "pizza sin ingredientes" no es una pizza válida.
- El precio de la pizza **no se almacena**: se calcula. La fórmula es: `(suma de costes de los ingredientes) × 1.20`.
- La descripción y la URL pueden ser opcionales (`null` o vacíos).
- Una pizza tiene una **identidad** que la distingue de otras. Decidid vosotros si esa identidad es el nombre, un id propio, o algo distinto.

### Ingrediente

- Un ingrediente **siempre** tiene una descripción no vacía.
- Un ingrediente **siempre** tiene un coste mayor o igual a cero. No se permiten costes negativos.
- Decidid el tipo Java apropiado para el coste. Pista: los `double` no son una buena idea para dinero.

### Comment

- Un comentario **siempre** tiene una fecha (con hora). Si no se proporciona al crearlo, se asume "ahora".
- Un comentario **siempre** tiene una descripción no vacía.
- Un comentario **siempre** está asociado a un usuario.
- Decidid si el comentario "sabe" a qué pizza pertenece, o si esa relación se gestiona desde fuera. Pista: pensad en agregados.

### User

- Un usuario **siempre** tiene un nombre no vacío.
- Un usuario **siempre** tiene un email no vacío y con formato razonable (al menos contiene una arroba).
- Dos usuarios con el mismo email son el mismo usuario.

---

## Decisiones que tenéis que tomar (y justificar)

Estas son las preguntas que os van a salir al modelar y que **forman parte del ejercicio**. No hay una respuesta única; hay respuestas mejores y peores, y cada una tiene consecuencias.

### 1. Identidad de las entidades

¿Cómo identificáis unívocamente una pizza? ¿Por nombre (asumiendo que no hay dos pizzas con el mismo nombre)? ¿Por un id propio (`PizzaId` como value object)? ¿Por la combinación nombre + ingredientes?

Lo mismo para `Ingrediente`, `Comment`, `User`.

**Pista**: en este punto del curso, la identidad **no es el id de base de datos**. Aún no sabemos que existirá BD. Es la identidad **conceptual** del objeto.

### 2. Tipo del coste

¿`int`? ¿`long`? ¿`double`? ¿`BigDecimal`? Cada uno tiene implicaciones. Justificad la elección.

### 3. Mutabilidad

¿Vuestras entidades son **inmutables** (atributos `final`, sin setters, modificaciones devuelven un objeto nuevo) o **mutables** (con setters o métodos de modificación)? Defended la decisión.

### 4. Colecciones

Cuando una pizza tiene varios ingredientes, ¿`List<Ingrediente>`? ¿`Set<Ingrediente>`? ¿`Collection`? ¿El orden importa? ¿Pueden repetirse?

### 5. Agregados

En DDD, un **agregado** es un grupo de entidades que se tratan como una unidad. Tiene una **raíz** que es la única vía de acceso al resto. ¿`Pizza` es la raíz de un agregado que incluye sus `Ingredientes`? ¿O `Ingrediente` es entidad independiente y `Pizza` solo lo referencia?

¿Qué pasa con `Comment`? ¿Es parte del agregado `Pizza` (cada pizza "tiene" sus comentarios y se acceden a través de ella) o es un agregado independiente (los comentarios se gestionan aparte y solo guardan una referencia a qué pizza comentan)?

**Pista**: la decisión correcta no es obvia. Pensad en casos de uso reales: ¿se carga una pizza con sus 5.000 comentarios cada vez que se ve en el catálogo?

### 6. Comportamiento

¿Qué métodos pone cada entidad? Algunos obvios:

- `Pizza.calcularPrecio()`.
- `Pizza.contieneIngrediente(...)`.
- `User.tieneEmail(...)`.

¿Y otros menos obvios? ¿`Pizza.añadirIngrediente(...)`? ¿O eso lo hace alguien por fuera? ¿Pizza.eliminarComentario(...)? ¿O los comentarios son inmutables tras crearse?

### 7. `equals` y `hashCode`

¿Por qué criterio? ¿Por id? ¿Por contenido? Ya lo discutiremos en el aula, pero pensadlo.

---

## Lo que **NO** tenéis que hacer en este ejercicio

- **No añadáis Hibernate al `pom.xml`** todavía.
- **No pongáis ninguna anotación de JPA** (`@Entity`, `@Id`, `@Column`, `@OneToMany`, etc.).
- **No penséis en tablas, columnas, claves foráneas o índices.** Eso vendrá después.
- **No penséis en cómo guardar esto en BD.** Eso vendrá después.
- **No creéis DTOs, controladores, servicios.** Solo dominio.

---

## Pista de estructura

Para que arranquéis, una posible estructura de la clase `Pizza` (sin completar):

```java
package com.example.curso.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public final class Pizza {

    private final String name;
    private final String description;
    private final String imageUrl;
    private final List<Ingrediente> ingredientes;

    public Pizza(String name, String description, String imageUrl,
                 List<Ingrediente> ingredientes) {
        // TODO: validar invariantes
        // TODO: asignar atributos (¿copia defensiva de la lista?)
    }

    public BigDecimal calcularPrecio() {
        // TODO: suma de costes de ingredientes × 1.20
    }

    // TODO: getters
    // TODO: equals y hashCode (¿con qué criterio?)
}
```

No copiéis esto sin pensarlo. Decidid vosotros si el constructor recibe estos parámetros, si la pizza tiene id, si la lista es de `Ingrediente` o de `IngredienteId`, etc.

---

## Cómo lo evaluamos

En la próxima sesión, cada uno de vosotros (o por parejas, según el grupo) presenta su modelo en pizarra y respondemos en grupo:

1. **¿Está protegido el dominio?** ¿Se puede crear una pizza inválida? ¿Se puede crear un usuario sin email?
2. **¿Es portable?** ¿Compila sin Hibernate? ¿Sin Spring? ¿Sin nada?
3. **¿Tiene comportamiento real o solo datos?** ¿Hay métodos de negocio o son sacos de getters?
4. **¿Las decisiones tomadas tienen justificación?** No esperamos una sola respuesta correcta, pero sí esperamos que cada decisión la sepáis defender.

---

## Lo que va a pasar después

En el bloque 2, cuando intentemos persistir este modelo, **vais a chocar contra la pared**. Vais a descubrir:

- Que vuestras invariantes y los requisitos de JPA no encajan.
- Que vuestras decisiones de identidad y las claves primarias de BD son cosas distintas.
- Que vuestros métodos de cálculo no se traducen directamente a columnas.
- Que vuestros agregados y las relaciones de tablas no son lo mismo.

**Y ese choque es exactamente lo que os va a hacer entender qué es un ORM y para qué sirve.** No como herramienta mágica, sino como **negociador entre dos modelos incompatibles**.

Si saltarais esto y fuerais directos a anotar entidades, aprenderíais a copiar anotaciones. Haciendo este ejercicio primero, **aprendéis a tomar decisiones de arquitectura**.

---


