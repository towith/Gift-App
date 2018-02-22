# run backend
git clone https://github.com/jaxio/celerio-angular-quickstart.git
cd celerio-angular-quickstart/quickstart
ng new web
rm web/src/app/app.module.ts web/src/app/app.component.* web/src/styles.css
mvn -Pdb,metadata,gen generate-sources
mvn spring-boot:run
Make sure you wait until spring boot starts and listen on port 8080

# run frontend
- Then from a second console run:
cd celerio-angular-quickstart/quickstart/web
npm install --save @angular/animations
npm install --save @angular/material@2.0.0-beta.6
npm install --save primeng@4.1.0-rc.2
npm install --save font-awesome
ng serve --proxy-config proxy.conf.json