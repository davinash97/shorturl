# URL Shortener

A simple Spring Boot application to shorten URLs, similar to services like bit.ly or tinyurl. Supports development and production profiles with DevTools enabled for development.

---

## Features

* Shorten long URLs into short, shareable links
* Redirect short URLs to their original URLs
* Dev and Prod profiles for easy development and deployment
* Automatic reload during development using Spring Boot DevTools

---

## Getting Started

### Prerequisites

* Java 17+
* Maven 3.9+

---

### Clone the repository

```bash
git clone https://github.com/davinash97/url_shortener.git
cd url_shortener
```

---

### Run in Development

```bash
./run.sh dev
```

* The application will start on `http://localhost:8080`
* LiveReload will auto-refresh the browser on template changes

---

### Run in Production

Production profile uses your configured production database:

```bash
./run.sh prod
```

* DevTools and LiveReload are disabled

---

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

### Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b <your-branch-name>`)
3. Commit your changes (`git commit -m "Add xyz"`)
4. Push to the branch (`git push origin <your-branch-name>`)
5. Open a Pull Request

---
<!-- 
### License

MIT License © 2026 [Avinash] -->
