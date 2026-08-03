# Reto Colaborativo — Login con API REST en Android

Aplicación Android que consume una API REST con autenticación por token. El usuario escribe
sus credenciales, la app las envía al servidor, recibe un token, y con ese token pide los datos
del usuario y los muestra en pantalla. La sesión se guarda en el dispositivo para que sobreviva
al cierre de la aplicación.

**Tecnologías:** Kotlin · Retrofit 2 · Gson · OkHttp · Corrutinas · SharedPreferences · ViewBinding
**API:** [DummyJSON](https://dummyjson.com)

---

## El equipo

| Integrante | Rol en el repositorio | Rama de trabajo |
|---|---|---|
| **Jubelit Zapata** | Creó el repositorio | `Jubelit` |
| **Juan Jaller** | Clonó el repositorio | `JuanJaller` |

El repositorio fue creado por **Jubelit Zapata** en la cuenta `gjubelit-source`, con el commit
inicial *"Creacion de app en trabajo colaborativo"*. **Juan Jaller** lo clonó y a partir de ahí
los dos trabajamos en paralelo sobre la misma base.

---

## Organización que usamos y por qué

Usamos **una mezcla entre la opción A y la opción B**. En lugar de escoger una sola, tomamos lo
que mejor nos servía de cada una y armamos este esquema:

```
main            <- version estable y final
  |
Developer       <- rama de integracion, donde se junta el trabajo de los dos
  |
  +-- Jubelit      <- rama personal
  +-- JuanJaller   <- rama personal
```

**Cómo funciona:**

1. Cada uno trabaja en su rama personal (`Jubelit` y `JuanJaller`), sin pisar el trabajo del otro.
2. Lo que ya está listo sube a `Developer`, que es donde el trabajo de ambos se junta y se prueba.
3. Cuando `Developer` está estable, pasa a `main` mediante **Pull Request**.

**Por qué lo hicimos así:**

- Las **ramas personales** nos dieron libertad para experimentar y equivocarnos sin romperle nada
  al otro. Si algo quedaba a medias, se quedaba en nuestra rama y ya.
- La **rama `Developer`** funciona como filtro: la integración se prueba ahí antes de tocar `main`,
  así `main` siempre queda con una versión que compila y funciona.
- Los **Pull Requests** nos obligaron a mirar el código del otro antes de mezclarlo. Eso hizo que
  los dos entendiéramos todo el proyecto, no solo la parte que cada uno escribió.

### Las ramas del repositorio

| Rama | Quién la usa | Para qué sirve |
|---|---|---|
| `main` | Ambos (solo por PR) | Versión estable. No se trabaja directamente sobre ella |
| `Developer` | Ambos | Rama de integración. Aquí se junta y se prueba el trabajo de los dos |
| `Jubelit` | Jubelit Zapata | Rama personal de trabajo diario |
| `JuanJaller` | Juan Jaller | Rama personal de trabajo diario |

La rama `Developer` se creó desde el principio, en el segundo commit del proyecto
(*"Creacion de la rama de desarrollo"*), antes incluso de empezar a programar. Eso fue a propósito:
queríamos tener el flujo de trabajo montado antes de que hubiera código que pudiera romperse.

### Los Pull Requests

Usamos dos Pull Requests para integrar hacia `main`:

| PR | Desde | Commit de merge |
|---|---|---|
| #1 | `Jubelit` | `8fcbe31` |
| #2 | `JuanJaller` | `57dd0e9` |

Además hicimos varios merges directos entre `Developer` y las ramas personales para mantenernos
sincronizados durante el día (`5e1025a`, `c7ac5d7`).

### Historial de commits

Este es el recorrido completo del proyecto, del más antiguo al más reciente:

| # | Commit | Autor | Descripción |
|---|---|---|---|
| 1 | `d327e26` | Jubelit | Creación de app en trabajo colaborativo |
| 2 | `d53e34a` | Jubelit | Creación de la rama de desarrollo |
| 3 | `448646a` | Juan | Configuración permisos de internet |
| 4 | `20c5c8e` | Juan | Añado permisos de internet |
| 5 | `6847c10` | Jubelit | Configuración de las dependencias del proyecto y activación de binding |
| 6 | `d171bd1` | Juan | Se añaden modelos de datos |
| 7 | `c7ac5d7` | Jubelit | Merge de `Developer` hacia `Jubelit` |
| 8 | `57dd0e9` | — | Merge pull request #2 desde `JuanJaller` |
| 9 | `8fcbe31` | — | Merge pull request #1 desde `Jubelit` |
| 10 | `5e1025a` | Jubelit | Merge de `Developer` hacia `Jubelit` |
| 11 | `15bdaca` | Jubelit | Creación de la carpeta `red/` y la interface con los endpoints |
| 12 | `745201b` | Juan | Añado `RetrofitClient` |
| 13 | `8acb014` | Jubelit | Cambios en la main: se une el login, se guarda el token y pedimos los datos |
| 14 | `2838dbb` | Jubelit | Declarar binding en la main y se añade diseño XML del login |
| 15 | `923a349` | Jubelit | Arreglo de línea |
| 16 | `46f4b83` | Jubelit | Se modifica el XML, se agrega botón, un text para mostrar y conexión con SharedPreferences |
| 17 | `cff6780` | Jubelit | Se actualiza código de la main |
| 18 | `d5b56a4` | Jubelit | Se hacen las validaciones en la main |
| 19 | `0f1d7a7` | Juan | Se hicieron mejoras a las validaciones |

Se ve claramente el orden en que fue creciendo el proyecto: primero la infraestructura
(permisos, dependencias), después los modelos y la capa de red, luego la pantalla, y por último
las validaciones y los mensajes al usuario.

---

## Cómo avanzamos juntos por la guía base y por la extensión

El trabajo fue **conjunto de principio a fin**. No nos repartimos el proyecto en dos mitades para
que cada uno hiciera la suya por su lado: fuimos avanzando a la par, dándonos indicaciones
mutuamente y resolviendo cada punto entre los dos.

**Guía base.** Montamos primero el esqueleto de la aplicación:

- Creación del proyecto y configuración de las dependencias (Retrofit, Gson, OkHttp, corrutinas).
- El permiso de `INTERNET` en el `AndroidManifest.xml`.
- La carpeta `red/` con `RetrofitClient` (la configuración) y `ApiService` (los endpoints).
- La carpeta `modelos/` con las clases que describen lo que se envía y lo que se recibe.
- El login inicial funcionando con credenciales fijas, mostrando el resultado en el Logcat.

**Extensión.** Sobre esa base fuimos resolviendo los puntos del reto uno por uno, probando cada
uno antes de pasar al siguiente. Esa fue la parte que más nos ayudó a entender cómo encaja todo:
al ver el token viajar desde el login hasta la petición protegida, el concepto dejó de ser teoría.

Cuando a alguno se le trababa algo, el otro le daba la indicación y seguíamos. Todo el proyecto
lo entendimos los dos por igual, incluyendo los retos.

---

## Dificultades al sincronizar o integrar

**No tuvimos dificultades.** La sincronización con `pull` y `push` funcionó sin problemas durante
todo el trabajo, y las integraciones entre ramas se hicieron limpiamente.

Creemos que eso se debió en buena parte a la organización que escogimos:

- Al trabajar cada uno en su **rama personal**, casi nunca tocamos los mismos archivos al mismo
  tiempo, que es lo que normalmente provoca los conflictos.
- Hacer `pull` de `Developer` **antes** de empezar a trabajar nos evitó quedarnos atrás respecto
  al otro.
- Los **commits pequeños y frecuentes**, con mensajes que decían qué se hizo, permitían ver
  rápido en qué iba el otro.

Entendimos bien toda esta parte y no se nos presentó ningún bloqueo.

---

## La extensión: los cuatro puntos

La extensión de la guía pedía cuatro cosas. Las trabajamos en este orden, probando cada una antes
de seguir con la siguiente.

### 1. Campos de texto y botón en lugar de credenciales fijas

**Antes:** el usuario y la contraseña estaban escritos dentro del código y el login se disparaba
solo al abrir la app.

**Ahora:** la pantalla tiene dos `EditText` (`edtName` y `edtPassword`) y un `Button` (`btnLogin`).
Al tocar el botón:

1. Se leen los dos campos y se les aplica `.trim()` para quitar espacios sobrantes — un espacio
   invisible al final del usuario hace que el servidor rechace credenciales que están bien.
2. Se valida que ninguno esté vacío. Si lo están, se marca el campo con `.error` para que aparezca
   el globito rojo con el mensaje.
3. Si alguno está vacío, se corta con `return@setOnClickListener` y no se llama a la API.
4. Si todo está bien, se llama a `hacerLogin(username, password)`.

Usamos **tres `if` en serie** y no uno solo: los dos primeros solo pintan el error de su campo, y
el tercero es el que decide si se puede continuar. Así, si el usuario dejó los dos campos vacíos,
ve los dos mensajes a la vez en lugar de tener que corregirlos de a uno.

### 2. Guardar el token en SharedPreferences

Cuando el login sale bien, se guardan **los dos tokens** en el disco del teléfono:

```kotlin
sharedPreferences.edit()
    .putString(KEY_ACCESS_TOKEN, datos?.accessToken)
    .putString(KEY_REFRESH_TOKEN, datos?.refreshToken)
    .apply()
```

Guardamos también el `refreshToken`, aunque todavía no se use, porque es lo que hará falta para
renovar la sesión cuando el token de acceso venza.

Y al arrancar la aplicación, antes de mostrar el formulario, se revisa si quedó una sesión abierta:

```kotlin
token = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
if (token != null) {
    obtenerUsuario()
}
```

Si hay token guardado, la app entra directo sin pedir credenciales. Eso es lo que hace que la
sesión sobreviva al cerrar la app, igual que en WhatsApp o Instagram.

### 3. Mostrar los datos en pantalla

Cambiamos todos los `Log` por escrituras en el `TextView` `edtMostrar`, y lo aplicamos a
**todas** las situaciones, no solo cuando el login sale bien:

| Situación | Qué ve el usuario |
|---|---|
| Login correcto | Nombre completo, usuario y correo |
| Usuario o contraseña incorrectos | `Usuario o contraseña incorrectos (400)` |
| Sin conexión a internet | `Error de red: ...` |
| El servidor rechaza el token | `No se pudieron traer los datos (401)` |

Antes, cuando algo fallaba, el mensaje solo salía en el Logcat: el usuario se quedaba mirando una
pantalla que no reaccionaba y no sabía si la app estaba cargando, si se había roto o si su
contraseña estaba mal.

También ajustamos el `TextView` a `layout_width="0dp"` con márgenes, porque con `wrap_content` un
correo largo se salía del borde de la pantalla.

### 4. Manejar el token vencido con `/auth/refresh`

> **Estado: pendiente.** Este punto está diseñado pero todavía no implementado.

El plan es el siguiente. Cuando el `accessToken` vence, la petición a `/auth/me` responde con el
código **401**. En el `else` de `obtenerUsuario()` ya se detecta esa respuesta, así que ahí se
engancha el flujo:

1. Leer el `refreshToken` guardado en SharedPreferences.
2. Llamar a `POST /auth/refresh` enviándolo.
3. Si responde bien, guardar los **dos** tokens nuevos (el de refresco también se renueva) y
   reintentar la petición original.
4. Si el refresh también falla, borrar la sesión y volver a mostrar el formulario de login.

El punto delicado es **evitar el bucle infinito**: si el reintento vuelve a dar 401, se intentaría
refrescar otra vez indefinidamente. La solución es pasarle a `obtenerUsuario()` un parámetro
booleano (`yaReintento`, con valor por defecto `false`) y solo intentar el refresh cuando sea
`false`.

Para probarlo sin esperar una hora, DummyJSON acepta el parámetro opcional `expiresInMins` en el
login: con valor `1` emite un token que vence en un minuto.

---

## Cómo resolvimos el reto final

> El reto pedía agregar un mensaje visible en pantalla (por ejemplo un `Toast`) que aparezca
> cuando el login falla, como cuando la contraseña es incorrecta.

Lo resolvimos tocando únicamente la función `hacerLogin()` de `MainActivity.kt`, que es donde ya
se sabía si el login había salido bien o mal. Esa función tiene tres caminos posibles: el `if`
que se ejecuta cuando el servidor acepta las credenciales, el `else` que se ejecuta cuando el
servidor responde pero rechaza el login, y el `catch` que se ejecuta cuando no hubo respuesta del
servidor. Agregamos un `Toast.makeText(...).show()` en los dos últimos, dejando el primero intacto
para que el camino exitoso siguiera funcionando exactamente igual que antes:

```kotlin
} else {
    Log.e("API", "Login falló: ${resp.code()}")
    binding.edtMostrar.text = "Usuario o contraseña incorrectos (${resp.code()})"
    Toast.makeText(
        this@MainActivity,
        "Login fallido: usuario o contraseña incorrectos",
        Toast.LENGTH_LONG
    ).show()
}
```

**Por qué lo hicimos así.** Decidimos ponerlo en `hacerLogin()` y no en el listener del botón
porque en el momento de tocar el botón todavía no se sabe si las credenciales son correctas: eso
solo lo sabe el servidor, y la respuesta llega después, dentro de la corrutina. Poner el Toast
antes habría sido adivinar.

Lo pusimos en **dos** ramas y no en una sola porque un login puede fallar por dos motivos muy
distintos, y al usuario le sirve saber cuál es: el `else` cubre cuando el servidor contestó y
rechazó las credenciales (contraseña equivocada, código 400), y el `catch` cubre cuando ni
siquiera hubo conversación con el servidor (sin internet, wifi caído). Por eso los mensajes son
diferentes: *"Login fallido: usuario o contraseña incorrectos"* y *"No se pudo conectar con el
servidor"*. Si hubiéramos puesto el mismo texto en ambos, alguien sin internet creería que se
equivocó de contraseña y estaría intentando corregir algo que está bien.

Usamos `Toast.LENGTH_LONG` en lugar de `LENGTH_SHORT` porque son mensajes de error y conviene que
den tiempo a leerse. Y escribimos `this@MainActivity` en vez de `this` porque el código está
dentro de un bloque `lifecycleScope.launch { }`: ahí `this` se refiere a la corrutina, no a la
Activity, y `Toast.makeText` necesita el contexto de la Activity.

El Toast **no reemplaza** el mensaje del `TextView`, se suma a él. El Toast aparece y desaparece
solo a los pocos segundos, así que llama la atención en el momento; el texto del `TextView` se
queda en pantalla y sirve para consultarlo después. Los dos juntos cubren mejor la situación que
cualquiera por separado.

---

## Cómo probar la aplicación

Hay que usar un usuario que exista en DummyJSON. El patrón de contraseña es siempre el nombre de
usuario seguido de `pass`:

| Usuario | Contraseña |
|---|---|
| `emilys` | `emilyspass` |
| `michaelw` | `michaelwpass` |
| `sophiab` | `sophiabpass` |

La lista completa está en https://dummyjson.com/users

**Prueba del camino feliz:** entrar con `emilys` / `emilyspass`. En pantalla debe aparecer:

```
Hola, Emily Johnson
Usuario: emilys
Correo: emily.johnson@x.dummyjson.com
```

**Prueba de que la sesión sobrevive:** iniciar sesión, cerrar la app por completo (deslizarla
fuera de recientes, no solo el botón atrás) y volver a abrirla. Debe mostrar los datos sin pedir
credenciales.

**Pruebas de error:**

| Qué hacer | Qué debe pasar |
|---|---|
| Dejar los dos campos vacíos y tocar Ingresar | Salen los globitos rojos y no se llama a la API |
| Usuario `emilys` con una contraseña inventada | **Toast** *"Login fallido: usuario o contraseña incorrectos"* y el mensaje en el TextView |
| Apagar el wifi y los datos | **Toast** *"No se pudo conectar con el servidor"* y el error de red en el TextView |

> **Nota:** todavía no hay botón de cerrar sesión, así que una vez que se entra la app queda con
> sesión iniciada. Para volver a ver el formulario durante las pruebas hay que borrar los datos de
> la app desde Ajustes, o desinstalarla y volverla a instalar.

---

## Estructura del proyecto

```
app/src/main/
├── AndroidManifest.xml              Permiso de INTERNET
├── java/com/example/reto_colaborativo/
│   ├── MainActivity.kt              Pantalla, validaciones y lógica
│   ├── modelos/
│   │   ├── LoginRequest.kt          Lo que enviamos al hacer login
│   │   ├── LoginResponse.kt         Lo que recibimos al hacer login
│   │   └── serResponse.kt           Los datos del perfil (clase UserResponse)
│   └── red/
│       ├── ApiService.kt            Los endpoints disponibles
│       └── RetrofitClient.kt        Configuración de Retrofit
└── res/layout/activity_main.xml     Diseño de la pantalla
```

---

## Documentación ampliada

En `documento colaborativo.docx` está la explicación técnica detallada: los conceptos de
fondo (qué es un token, por qué son dos, qué es una corrutina), `MainActivity` explicada línea por
línea, los diagramas del flujo completo, y un capítulo con los problemas que encontramos durante
el desarrollo y cómo los resolvimos.
