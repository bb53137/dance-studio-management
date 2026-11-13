
# Dance Studio Management System

### Informacijski sustav za upravljanje plesnim studijem

Full-stack web aplikacija razvijena kao zajednički fakultetski projekt u sklopu kolegija Informacijski sustavi na Fakultetu elektrotehnike i računarstva Sveučilišta u Zagrebu (FER).

Cilj projekta je razvoj informacijskog sustava koji omogućuje organizaciju i upravljanje podacima plesnog studija, uključujući plesne skupine, trenere, članove i njihove međusobne odnose.

Projekt obuhvaća razvoj korisničkog sučelja, implementaciju REST API-ja, poslovnu logiku i povezivanje s relacijskom bazom podataka.

## Tehnologije

### Backend
- Java 17
- Spring Boot 3.3
- Spring Web
- Spring Data JPA
- Hibernate
- Gradle
- REST API

### Frontend
- React 18
- JavaScript
- HTML5 i CSS3
- React Router
- Axios

### Baza podataka
- PostgreSQL

### Alati
- Git i GitHub
- Gradle Wrapper
- npm

## Funkcionalnosti

Aplikacija obuhvaća sljedeće funkcionalnosti:

- Pregled plesnih skupina i njihovih detalja
- Dodavanje i uređivanje plesnih skupina
- Pregled, dodavanje i uređivanje trenera
- Evidenciju članova i njihovih povezanosti s plesnim skupinama na backendu
- Upravljanje povezanošću trenera i plesnih skupina
- REST API za dohvat, unos, izmjenu i brisanje podataka
- Povezivanje React frontenda sa Spring Boot backendom
- Pohranu i dohvat podataka iz PostgreSQL baze

Pojedini dijelovi korisničkog sučelja i funkcionalnosti nalaze se u prototipnoj fazi.

## Arhitektura aplikacije

Projekt koristi višeslojnu arhitekturu koja razdvaja korisničko sučelje, poslovnu logiku i pristup podacima.

### Frontend – React

React je korišten za izradu korisničkog sučelja. Navigacija između stranica implementirana je pomoću React Routera, dok se Axios koristi za komunikaciju s backend REST API-jem.

### Backend – Spring Boot

Backend je organiziran kroz sljedeće slojeve:

- **Controller** – obrada HTTP zahtjeva i REST API endpointi
- **Service** – implementacija poslovne logike
- **Repository** – pristup podacima putem Spring Data JPA
- **Model** – entiteti i odnosi među podacima
- **DTO** – objekti za prijenos podataka

### Baza podataka – PostgreSQL

PostgreSQL služi za pohranu podataka o članovima, trenerima, plesnim skupinama i njihovim međusobnim odnosima.

Za objektno-relacijsko mapiranje koriste se Hibernate i Spring Data JPA.

## Struktura projekta

```text
dance-studio-management/
│
├── plesni-studio/                 # React frontend
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   ├── App.js
│   │   └── index.js
│   └── package.json
│
├── studio/                        # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ples/studio/
│   │   │   │   ├── controller/
│   │   │   │   ├── service/
│   │   │   │   ├── repository/
│   │   │   │   ├── model/
│   │   │   │   └── exception/
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── build.gradle
│   └── gradlew
│
├── .gitignore
└── README.md
```

## Pokretanje projekta

### Preduvjeti

Za lokalno pokretanje potrebni su:

- Java 17
- Node.js i npm
- PostgreSQL

### 1. Kloniranje repozitorija

```bash
git clone https://github.com/bb53137/dance-studio-management.git
cd dance-studio-management
```

### 2. Konfiguracija baze podataka

Kreirati lokalnu PostgreSQL bazu podataka naziva `Studio`.

Postojeća konfiguracija nalazi se u:

`studio/src/main/resources/application.properties`

Konfigurirani JDBC URL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/Studio
```

Potrebno je osigurati da PostgreSQL koristi odgovarajući port ili prilagoditi lokalnu konfiguraciju.

Podaci za pristup bazi koriste varijable okruženja:

- `DB_USERNAME`
- `DB_PASSWORD`

Lozinke i ostali osjetljivi podaci ne pohranjuju se izravno u izvorni kod.

### 3. Pokretanje backenda

Iz glavne mape projekta:

```bash
cd studio
./gradlew bootRun
```

Na Windowsu moguće je koristiti:

```bash
./gradlew.bat bootRun
```

Backend se prema zadanoj Spring Boot konfiguraciji pokreće na portu 8080.

### 4. Pokretanje frontenda

U zasebnom terminalu, iz glavne mape projekta:

```bash
cd plesni-studio
npm install
npm start
```

React razvojni poslužitelj prema zadanim postavkama koristi:

`http://localhost:3000`

Za rad aplikacije potrebno je imati pokrenutu PostgreSQL bazu i backend servis.

**Napomena:** Postupak pokretanja nije u potpunosti provjeren u novom razvojnom okruženju. Moguće su dodatne prilagodbe konfiguracije ili inicijalizacija podataka.

## Testiranje i status projekta

Projekt sadrži osnovne testne datoteke za frontend i backend.

Aplikacija je razvijena u edukacijske svrhe kao fakultetski projekt te nije namijenjena produkcijskoj uporabi bez dodatnih sigurnosnih i funkcionalnih poboljšanja.

Posebno je potrebno unaprijediti autentifikaciju, sigurno rukovanje korisničkim lozinkama, autorizaciju i zaštitu osobnih podataka.

## Timski rad

Projekt su zajednički razvile dvije studentice u sklopu fakultetskog kolegija.

Ovaj repozitorij predstavlja zasebnu, očišćenu portfolio verziju zajedničkog projekta.

Izvorna Git povijest nije prenesena u ovu verziju repozitorija.

## Moguća buduća poboljšanja

- Dovršavanje preostalih frontend funkcionalnosti
- Implementacija sigurne autentifikacije i autorizacije
- Hashiranje i zaštita korisničkih lozinki
- Poboljšanje validacije podataka i obrade pogrešaka
- Proširenje automatiziranih testova
- Poboljšanje korisničkog sučelja i responzivnosti
- Konfiguracija aplikacije za deployment

## Licenca

Projekt je razvijen u obrazovne svrhe kao zajednički studentski rad. Nije dodijeljena otvorena licenca.
