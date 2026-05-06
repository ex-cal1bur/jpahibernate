# Curso Hibernate JPA · 15h

## Bloque 1 — Punto de partida (3h)

1.1. Caso real: el choque con el entorno

1.2. Comparativa de versiones: Java, Hibernate, `javax` vs `jakarta`

1.3. Qué es un ORM: el problema del *impedance mismatch*

1.4. JDBC a pelo vs ORM

1.5. JPA, Hibernate y Jakarta EE: especificación, implementación, paraguas

1.6. El sentido del ORM en DDD

## Bloque 2 — Modelado del dominio (3h)

2.1. Diseño del dominio: Pizza, Ingrediente, Comment, User

2.2. POJOs puros: constructores que validan, métodos de negocio

2.3. Invariantes y validación en el dominio

2.4. Identidad de dominio vs identidad de fila

2.5. Agregados: qué entra dentro, qué queda fuera

2.6. El choque con la persistencia: las preguntas que aparecen

## Bloque 3 — Anotaciones JPA y entidades de persistencia (3h)

3.1. Separación dominio / entidad de persistencia / mapper / repository

3.2. `@Entity`, `@Id`, `@GeneratedValue`, `@Column`, `@Table`

3.3. `@Enumerated`, `@Embeddable`, `@Transient`

3.4. Relaciones: `@ManyToOne`, `@OneToMany`, `@ManyToMany`, `@OneToOne`

3.5. `mappedBy`, `@JoinColumn`, `@JoinTable`: lado propietario vs inverso

3.6. Caso N:M con atributos: entidad asociativa

3.7. Caso `@OneToOne` Comment→User: implicaciones de índices y queries

3.8. Bean Validation en la entidad: complemento, no sustituto del dominio

## Bloque 4 — EntityManager, estados y transacciones (3h)

4.1. `EntityManagerFactory` y `EntityManager`

4.2. Estados de las entidades: `transient`, `managed`, `detached`, `removed`

4.3. Operaciones: `persist`, `merge`, `find`, `remove`, `flush`, `refresh`, `detach`, `clear`

4.4. Dirty checking

4.5. Transacciones: `EntityTransaction`, `begin`, `commit`, `rollback`

4.6. Contexto de persistencia y ciclo de la transacción

4.7. Decisiones de carga: lazy en comentarios, agregado completo en ingredientes

4.8. Configuración programática sin `persistence.xml`

4.9. Mención al XML como cita histórica

## Bloque 5 — Consultas, errores y cierre (3h)

5.1. JPQL: sintaxis, parámetros, `TypedQuery`

5.2. Joins, `JOIN FETCH` y el problema N+1

5.3. Paginación, `UPDATE` y `DELETE` masivos

5.4. Criteria API: para qué existe y por qué duele

5.5. Mención a Specifications como alternativa moderna

5.6. SQL nativo: `createNativeQuery`, mapeo a entidades y a DTOs

5.7. Cuándo usar JPQL, Criteria o SQL nativo

5.8. Errores comunes: `LazyInitializationException`, N+1, ciclos en `toString`, transacción olvidada

5.9. Recapitulación: decisiones aprendidas

5.10. Siguientes pasos: Spring Data JPA, testing, migraciones, alternativas