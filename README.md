
# InfSus – Sustav za upravljanje plesnim studijem

Full-stack web aplikacija razvijena kao zajednički fakultetski projekt u sklopu kolegija Informacijski sustavi.

Cilj projekta je izrada informacijskog sustava za upravljanje plesnim studijem, uključujući evidenciju plesnih skupina, trenera, članova i povezanih podataka.

Aplikacija koristi React za korisničko sučelje, Java Spring Boot za backend te PostgreSQL za pohranu podataka.

## Korištene tehnologije

### Frontend
- React
- JavaScript
- HTML i CSS
- React Router
- Axios

### Backend
- Java 17
- Spring Boot 3.3
- Spring Web
- Spring Data JPA
- Hibernate
- Gradle

### Baza podataka
- PostgreSQL

## Funkcionalnosti

Projekt sadrži komponente i API-je za:

- Pregled i upravljanje plesnim skupinama
- Dodavanje i uređivanje podataka o trenerima
- Prikaz detalja pojedinih plesnih skupina
- Upravljanje članovima i povezanim podacima
- Navigaciju između različitih dijelova aplikacije
- Komunikaciju frontenda i backenda putem REST API-ja
- Pohranu i dohvat podataka korištenjem PostgreSQL baze

Dio funkcionalnosti razvijen je kao prototip te nije u potpunosti implementiran.

## Arhitektura aplikacije

Aplikacija je organizirana u tri osnovna sloja:

**Frontend – React**

Korisničko sučelje omogućuje pregled, unos i uređivanje podataka. Komunikacija s backendom ostvaruje se putem HTTP zahtjeva korištenjem biblioteke Axios.

**Backend – Spring Boot**

Backend obrađuje REST API zahtjeve, implementira poslovnu logiku i omogućuje komunikaciju s bazom podataka.

**Baza podataka – PostgreSQL**

Podaci se pohranjuju u relacijsku bazu podataka, uz korištenje Spring Data JPA i Hibernate tehnologija.

## Struktura projekta

Glavni dijelovi repozitorija:

- `studifrontend/` – React frontend aplikacija
- `studio/` – Java Spring Boot backend aplikacija
- `studio/src/main/java/` – Java izvorni kod
- `studio/src/main/resources/` – konfiguracija backenda
- `README.md` – dokumentacija projekta

## Pokretanje projekta

### Preduvjeti

Za lokalno pokretanje potrebni su:

- Java 17
- Node.js i npm
- PostgreSQL

### 1. Kloniranje repozitorija

```bash
git clone https://github.com/bb53137/InfSus.git
cd InfSus
```

### 2. Konfiguracija baze podataka

Potrebno je kreirati lokalnu PostgreSQL bazu podataka i provjeriti konfiguraciju u datoteci:

`studio/src/main/resources/application.properties`

Podaci za pristup bazi postavljaju se pomoću varijabli okruženja:

- `DB_USERNAME`
- `DB_PASSWORD`

Lozinke i drugi osjetljivi podaci ne smiju se spremati u repozitorij.

### 3. Pokretanje backenda

```bash
cd studio
./gradlew bootRun
```

Na Windowsu:

```bash
./gradlew.bat bootRun
```

### 4. Pokretanje frontenda

U zasebnom terminalu:

```bash
cd studifrontend
npm install
npm start
```

Za ispravan rad aplikacije potrebna je odgovarajuća konfiguracija baze i dostupnost backend API-ja.

Napomena: postupak pokretanja nije u potpunosti testiran u novom razvojnom okruženju.

## Status projekta

Projekt je razvijen u edukacijske svrhe tijekom studija računarstva.

Obuhvaća razvoj korisničkog sučelja, implementaciju REST API-ja, organizaciju backend logike i povezivanje s relacijskom bazom podataka.

Pojedine funkcionalnosti nisu u potpunosti dovršene te aplikacija nije namijenjena produkcijskoj uporabi.

## Timski rad

Projekt su zajednički razvile dvije studentice u sklopu fakultetskog kolegija.

Povijest Git commitova čuva izvorne doprinose i autorstvo tijekom razvoja.

## Moguća buduća poboljšanja

- Dovršavanje preostalih funkcionalnosti
- Poboljšanje validacije korisničkog unosa
- Implementacija automatiziranih testova
- Poboljšanje korisničkog sučelja
- Dodavanje autentifikacije i autorizacije
- Unaprjeđenje sigurnosti aplikacije
- Priprema aplikacije za deployment

## Licenca

Projekt je razvijen u obrazovne svrhe kao zajednički studentski rad. Nije dodijeljena otvorena licenca.
