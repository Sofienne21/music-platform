# Étape 1 : build React avec Node
FROM node:20-alpine as build
WORKDIR /app
COPY . .

ARG VITE_API_URL
ENV VITE_API_URL=$VITE_API_URL

RUN npm install
RUN npm run build

# Étape 2 : servir l'app avec Nginx
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html

# Supprime la config par défaut de Nginx
RUN rm /etc/nginx/conf.d/default.conf

# Ajoute une config simple qui supporte React Router
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80