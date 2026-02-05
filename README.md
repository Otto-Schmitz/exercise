# Rate Limiter Exercise

**Language / Idioma:** [English](#english) · [Português](#português)

---

<a id="english"></a>

## English

Repository with **Java** implementations of **per-user rate limiters** and a **meeting room scheduler** exercise.

### Structure

- **`RateLimiterFixedWindow.java`**: Fixed window rate limiter
- **`RateLimiterSlidingWindow.java`**: Sliding window rate limiter
- **`MeetingScheduler.java`**: Meeting room scheduler (conflict-free bookings)

### Implementations

| Algorithm       | File                         | Description |
|----------------|------------------------------|-------------|
| Fixed Window   | `RateLimiterFixedWindow.java`  | Limits requests within fixed time windows. O(1) per request. May allow boundary bursts. |
| Sliding Window | `RateLimiterSlidingWindow.java`| Limits requests within a moving time window. O(1) amortized. More precise than fixed window. |

### Other exercises

- **`MeetingScheduler.java`**: Schedules meetings per room using non-overlapping intervals and finds the next available slot.

### Build and run

Compile from the project root:

```bash
javac *.java
```

To run a class that has a `main` method:

```bash
java exercise.RateLimiterFixedWindow
```

or

```bash
java exercise.RateLimiterSlidingWindow
```

### Requirements

- Java (JDK 8 or higher)

---

<a id="português"></a>

## Português

Repositório com implementações em **Java** de **limitadores de taxa por usuário** e um exercício de **agendamento de salas de reunião**.

### Estrutura

- **`RateLimiterFixedWindow.java`**: Limitador de taxa com janela fixa
- **`RateLimiterSlidingWindow.java`**: Limitador de taxa com janela deslizante
- **`MeetingScheduler.java`**: Agendador de salas (sem conflitos)

### Implementações

| Algoritmo      | Arquivo                        | Descrição |
|----------------|--------------------------------|-----------|
| Janela Fixa    | `RateLimiterFixedWindow.java`  | Limita requisições em janelas de tempo fixas. O(1) por requisição. Pode permitir picos nas bordas. |
| Janela Deslizante | `RateLimiterSlidingWindow.java` | Limita requisições em uma janela de tempo móvel. O(1) amortizado. Mais preciso que janela fixa. |

### Outros exercícios

- **`MeetingScheduler.java`**: Agenda reuniões por sala usando intervalos sem sobreposição e encontra o próximo horário disponível.

### Como compilar e executar

Compile a partir da raiz do projeto:

```bash
javac *.java
```

Para executar uma classe com método `main`:

```bash
java exercise.RateLimiterFixedWindow
```

ou

```bash
java exercise.RateLimiterSlidingWindow
```

### Requisitos

- Java (JDK 8 ou superior)
