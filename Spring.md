---

# 🧭 SYSTEM DESIGN (before sprint)

## 🎯 Goal of your app

A **URL Shortener Service** that:

### Core behavior

* Generate short key for long URL
* Retrieve long URL using key
* (Later) redirect users

### Non-functional goals

* Unique keys (no collision issues)
* Expiry support
* Clean API structure
* Simple, scalable architecture

---

## 🧱 Clean Architecture (your target state)

```text
Controller → Service → Repository → Storage
                     ↓
               Domain Model (UrlResponse)
```

### Rules:

* ONLY `UrlResponse` is stored and retrieved
* Service contains ALL logic
* Controller is thin (no business logic)
* Repository is dumb persistence layer

---

# ⚙️ DEVELOPMENT PROTOCOL (IMPORTANT)

Follow this every sprint:

### 🧠 1. One responsibility per layer

No mixing concerns.

### 🧪 2. Always test mentally first

Ask:

> “What happens if key is missing / duplicate / expired?”

### 🚫 3. No premature DTOs

Only add DTOs when:

* multiple response formats exist OR
* external API contract requires it

### 🔁 4. Service is the “truth engine”

Controller never decides logic.

---

# 🚀 SPRINT PLAN (structured rebuild)

We’ll do this in 5 controlled sprints.

---

# 🟢 SPRINT 1 — FOUNDATION CLEANUP (CURRENT FOCUS)

### Goal:

Make system **consistent and model-driven**

---

## Tasks:

### 1. Fix Domain Model usage

* `UrlResponse` becomes ONLY domain object
* Ensure it represents full truth

---

### 2. Fix Repository

Must support:

```java id="repo_s1"
UrlResponse findByKey(String key);
void save(UrlResponse url);
boolean exists(String key);
```

👉 No `String` returns anywhere

---

### 3. Fix Service (core cleanup)

#### Must implement:

```text
createShortUrl(longUrl, userId)
getLongUrl(key)
```

Rules:

* returns `UrlResponse` or `Optional<UrlResponse>`
* no DTO usage
* no null returns
* expiry logic prepared (not enforced yet if needed)

---

### 4. Controller cleanup

* Accept request
* Call service
* Wrap in `ApiResponse`
* NO business logic

---

## 🎯 Sprint 1 DONE when:

✔ Only one model (`UrlResponse`) used everywhere
✔ Repository returns domain objects
✔ Service returns Optional or domain objects
✔ Controller is dumb
✔ No fake timestamps / hardcoding

---

# 🟡 SPRINT 2 — RELIABILITY LAYER

### Goal:

Make system safe under real-world usage

---

## Tasks:

### 1. Add expiry enforcement

* if `expiresAt < now → return empty`

---

### 2. Add uniqueness safety

* repository must enforce uniqueness OR atomic insert

---

### 3. Replace random key logic (optional improvement)

* switch to Base62 counter OR safe ID generator

---

### 4. Improve error handling

* introduce consistent exceptions OR error responses

---

## 🎯 Sprint 2 DONE when:

✔ Expired URLs don’t resolve
✔ No collision bugs
✔ Service behaves predictably

---

# 🔵 SPRINT 3 — API DESIGN CLEANUP

### Goal:

Make API professional and consistent

---

## Tasks:

### 1. Standardize API responses

Keep:

```java id="api_s3"
ApiResponse<T>
```

---

### 2. Add proper POST endpoint

```text
POST /url
→ creates short URL
```

---

### 3. Add redirect endpoint (optional but important)

```text
GET /{key}
→ HTTP 302 redirect
```

---

## 🎯 Sprint 3 DONE when:

✔ API is consistent
✔ Proper HTTP semantics used
✔ Redirect works (if enabled)

---

# 🟣 SPRINT 4 — DATA & STORAGE EVOLUTION

### Goal:

Make system real-world scalable

---

## Tasks:

* Switch repository to DB (JPA or JDBC)
* Add indexes on key
* Ensure uniqueness constraint at DB level

---

## 🎯 Sprint 4 DONE when:

✔ Persistent storage works
✔ Restart-safe system
✔ DB enforces uniqueness

---

# 🔴 SPRINT 5 — SCALABILITY (optional advanced)

### Goal:

Production readiness

* caching (Redis)
* rate limiting
* analytics (click tracking)
* distributed key generation

---

# 🧠 SIMPLE RULE SET (KEEP THIS ALWAYS)

### ✔ DO

* One model = one source of truth
* Service owns logic
* Repository owns data
* Controller only orchestrates

### ❌ DON’T

* return String from service
* duplicate DTOs unnecessarily
* compute logic in controller
* ignore expiry or consistency rules
