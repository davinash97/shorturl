# URL Shortener

A simple Spring Boot application to shorten URLs, similar to services like bit.ly or tinyurl. Supports development and production profiles with DevTools enabled for development.


## Project Architecture

### - Controller.java

Handles HTTP requests.

| Method | Path     | Parameters                                       | Action                                                             |
| ------ | -------- | ------------------------------------------------ | ------------------------------------------------------------------ |
| `POST` | `/url`   | `long_url` (query param, body, or path variable) | Calls `longToShort()` in service and returns short key             |
| `GET`  | `/{key}` | `key` (path variable)                            | Calls `shortToLong()` in service and **redirects** to original URL |

---

### - Services.java

Business logic layer — coordinates encoding/decoding and repository operations.

```text
longToShort(url) → returns short key
shortToLong(key) → returns original URL
```

* Checks key uniqueness (in-memory or DB)
* Can handle collisions if needed
* Currently memory-based; later replaced by repository + transactional DB

### - Repository.java (No ORM)

Direct database operations.

```text
setup()					→ creates table if not exists (first run)
longToShort(key, url) 	→ inserts new unique key; returns true if success, false if collision
shortToLong(key) 		→ fetches URL by key; returns URL if exists, else null
```


### -  Core.java

Encodes/decodes numeric IDs to Base62 for short URLs.

```text
encode(value) → Base62 encoded string
decode(shortKey) → returns numeric ID (for testing only)
```

* Keeps encoding logic independent of DB or service
* Supports deterministic and reversible conversion


## How it Works (Prototype Flow)
1. **POST /url** → Controller calls Service
2. **Service generates random ID** → Core encodes to Base62 key
3. **Repository inserts key→url mapping** → returns success/failure
4. **Service returns short key** to controller
5. **GET /{key}** → Controller calls Service → Repository returns original URL → redirect

## Features

* Shorten long URLs into short, shareable links
* Redirect short URLs to their original URLs
* Dev and Prod profiles for easy development and deployment
* Automatic reload during development using Spring Boot DevTools

## Getting Started

### Prerequisites

* Java 17+
* Maven 3.9+

### Clone the repository

```bash
git clone https://github.com/davinash97/url_shortener.git
cd url_shortener
```
### Pre-requirements
Copy config.env and make a new file named `.env` with the changes

### Run in Development

```bash
./run dev
```

* The application will start on `http://localhost:8080`
* LiveReload will auto-refresh the browser on template changes

### Run in Production

Production profile uses your configured production database:

```bash
./run prod
```

* DevTools and LiveReload are disabled

### Project Structure

```
src/
├─ main/
│  ├─ java/com/shorturl/    # Application code
│  └─ resources/
│     ├─ application.properties
│     ├─ application-dev.properties
│     └─ application-prod.properties
```

---

### Technologies Used

* Java 17
* Spring Boot 4.x
* Spring Web (REST API)
* Maven
---

### Future Enhancements

* Wrap DB insert in **transaction** for multi-threaded safety
* Add **unique constraint** on `short_key` in DB
* Add test cases to verify:

  * `longToShort()` → returns unique keys
  * `shortToLong()` → retrieves correct URL
  * Base62 encode/decode correctness

---

### Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b <your-branch-name>`)
3. Commit your changes (`git commit -m "Add xyz"`)
4. Push to the branch (`git push origin <your-branch-name>`)
5. Open a Pull Request

<!-- 
---

### License

MIT License © 2026 [Avinash] -->
