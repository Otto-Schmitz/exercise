# Backend Exercises (Java)

**Language / Idioma:** [English](#english) · [Português](#português)

---

<a id="english"></a>

## English

Repository with **Java** implementations of **per-user rate limiters** and a **meeting room scheduler** exercise.

### News

- Current lineup: Fixed Window rate limiter, Sliding Window rate limiter, Meeting Scheduler.
- Each exercise includes a `main` method with basic usage examples.

### Structure

- **`RateLimiterFixedWindow.java`**: Fixed window rate limiter
- **`RateLimiterSlidingWindow.java`**: Sliding window rate limiter
- **`MeetingScheduler.java`**: Meeting room scheduler (conflict-free bookings)

### Implementations

| Exercise                  | File                           | Description |
|--------------------------|--------------------------------|-------------|
| Rate Limiter (Fixed Window)   | `RateLimiterFixedWindow.java`   | Limits requests within fixed time windows. O(1) per request. May allow boundary bursts. |
| Rate Limiter (Sliding Window) | `RateLimiterSlidingWindow.java` | Limits requests within a moving time window. O(1) amortized. More precise than fixed window. |
| Meeting Scheduler             | `MeetingScheduler.java`         | Schedules conflict-free room bookings and finds the next available slot. |

### Other exercises

- **`MeetingScheduler.java`**: Schedules meetings per room using non-overlapping intervals and finds the next available slot.

### Build and run

Compile from the project root:

```bash
javac *.java
```

To run a class that has a `main` method:

```bash
java exercise.FILENAME
```

### Requirements

- Java (JDK 8 or higher)

---

<a id="português"></a>

## Português

Repositório com implementações em **Java** de **limitadores de taxa por usuário** e um exercício de **agendamento de salas de reunião**.

### Novidades

- Conjunto atual: limitador de janela fixa, limitador de janela deslizante, Meeting Scheduler.
- Cada exercício inclui um método `main` com exemplos básicos de uso.

### Estrutura

- **`RateLimiterFixedWindow.java`**: Limitador de taxa com janela fixa
- **`RateLimiterSlidingWindow.java`**: Limitador de taxa com janela deslizante
- **`MeetingScheduler.java`**: Agendador de salas (sem conflitos)

### Implementações

| Exercício                     | Arquivo                         | Descrição |
|------------------------------|---------------------------------|-----------|
| Limitador (Janela Fixa)      | `RateLimiterFixedWindow.java`   | Limita requisições em janelas de tempo fixas. O(1) por requisição. Pode permitir picos nas bordas. |
| Limitador (Janela Deslizante)| `RateLimiterSlidingWindow.java` | Limita requisições em uma janela de tempo móvel. O(1) amortizado. Mais preciso que janela fixa. |
| Meeting Scheduler            | `MeetingScheduler.java`         | Agenda salas sem conflito e encontra o próximo horário disponível. |

### Outros exercícios

- **`MeetingScheduler.java`**: Agenda reuniões por sala usando intervalos sem sobreposição e encontra o próximo horário disponível.

### Como compilar e executar

Compile a partir da raiz do projeto:

```bash
javac *.java
```

Para executar uma classe com método `main`:

```bash
java exercise.FILENAME
```

### Requisitos

- Java (JDK 8 ou superior)
