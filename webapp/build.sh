cd /var/source
echo "changed folder"
yes | npm install
echo "npm install done"
npx ng serve --host 0.0.0.0 --port 80 --poll 200  