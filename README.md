# Storytellr

## Branches

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
KEYCLOAK_ISSUER_URI
KEYCLOAK_FRONTEND_CLIENT_ID
API_DATABASE_USER
API_DATABASE_PASSWORD
API_DATABASE_NAME
API_DATABASE_DRIVER_CLASS_NAME
API_JPA_DATABASE_PLATFORM
API_HIBERNATE_MODE
API_JPA_SHOW_SQL
API_DEBUG_LEVEL
MONGODB_HOST
MONGODB_PORT

```

### Production Deployment

- create a env file like in dev mode
- run `docker compose -p storytellr --profile prod up -d`
