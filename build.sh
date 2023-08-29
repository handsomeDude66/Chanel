cd vue-project
npm ci
npm run build

cd ..
rm -r src/main/resources/static
mv vue-project/dist static
