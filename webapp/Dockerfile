FROM node:lts-alpine3.19 as buildstep
COPY ./storyteller /var/source
WORKDIR /var/source
RUN npm install
RUN npm run build
FROM nginx:1.25.4 as runstep
COPY --from=buildstep /var/source/dist/storyteller/browser /www/data/storyteller