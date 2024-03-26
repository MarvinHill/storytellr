# Storyteller

## Branches
### Prefixes
- feature/
- bug/
- chore/

## Commits
Commits use the [Conventional Commit Structure](https://www.conventionalcommits.org/en/v1.0.0/) naming pattern.

A helper tool can be found at [link](https://commit-creator.netlify.app/).
### Scope Naming
Current existing Scopes in Storyteller
- api
- webapp

## Project Structure

- The **infra folder** contains the infrastructure as code.
- The **webapp folder** contains the angular frontend projekt.
- The **api folder** contains a spring boot backend api service that is consumed by the webapp.

### Local Development
- create a ```.env``` file in the same folder as the ```docker-compose.yml``` file
- run ```docker compose up```.

**.env File Content**
```
KEYCLOAK_DATABASE_VENDOR=???
KEYCLOAK_DB_SCHEMA=???
KEYCLOAK_DB_URL=???
KEYCLOAK_DATABASE_USER=???
KEYCLOAK_DATABASE_NAME=???
KEYCLOAK_DATABASE_PASSWORD=???
KEYCLOAK_HTTP_RELATIVE_PATH=???

KEYCLOAK_ADMIN_USER=???
KEYCLOAK_ADMIN_PASSWORD=???
```