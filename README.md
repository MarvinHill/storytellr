# Storytellr
Storytellr is a project for writers and readers. As a user you can write your own books and publish them and read the books written by other people. There should be interaction between readers and writers.
This project was developed by Marvin Hill and Janina Dörflinger from Heilbronn University for Application Projects in SoSE 2024.

### Prefixes

- feature/
- bug/
- chore/

## Commits

Commits use the [Conventional Commit Structure](https://www.conventionalcommits.org/en/v1.0.0/) naming pattern.

A helper tool can be found at [link](https://commit-creator.netlify.app/).

### Scope Naming

Current existing Scopes in Storytellr

- api
- webapp
- proxy
- infra
- keycloak
- publicdata

## Project Structure

- The **infra folder** contains the infrastructure as code.
- The **webapp folder** contains the angular frontend projekt.
- The **api folder** contains a spring boot backend api service that is consumed by the webapp.

### Cloning the Project
- Make sure to configure `git config --global core.autocrlf false` before cloning the project.

### Build Project
- injects client secret into keycloak conf
`docker compose -p buildstep -f build.yml --profile dev up -d `

### Local Development

- create a `.env` file in the same folder as the `docker-compose.yml` file
- run `docker compose -p storytellr --profile dev up -d`.

**.env File Content**

```
KEYCLOAK_DATABASE_VENDOR
KEYCLOAK_DB_SCHEMA
KEYCLOAK_DB_URL
KEYCLOAK_DATABASE_USER
KEYCLOAK_DATABASE_NAME
KEYCLOAK_DATABASE_PASSWORD
KEYCLOAK_HTTP_RELATIVE_PATH

KEYCLOAK_ADMIN_USER
KEYCLOAK_ADMIN_PASSWORD

KEYCLOAK_REALM

KEYCLOAK_ADMIN_API_HOST_URI
KEYCLOAK_ISSUER_URI
KEYCLOAK_FRONTEND_CLIENT_ID

API_DATABASE_USER
API_DATABASE_PASSWORD
API_DATABASE_NAME
API_DATABASE_DRIVER_CLASS_NAME
API_JPA_DATABASE_PLATFORM
API_HIBERNATE_MODE
API_JPA_SHOW_SQL

API_KEYCLOAK_CLIENT_CLIENT_ID
API_KEYCLOAK_CLIENT_SECRET

MONGODB_HOST
MONGODB_PORT

API_DEBUG_LEVEL

BACKEND_CLIENT_USERNAME
BACKEND_CLIENT_SECRET
```

### Production Deployment

- create a env file like in dev mode
- run `docker compose -p storytellr --profile prod up -d`
