cd /var/source
echo "cd into /var/source -----------------------------------------------------"
yes | npm install
echo "npm install -------------------------------------------------------------"
npx ng serve --disable-host-check --host 0.0.0.0 --port 80 --poll 200  
echo "npx ng serve --host 0.0.0.0 --port 80 --poll 200 ------------------------"