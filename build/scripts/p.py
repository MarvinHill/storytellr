import json
import os
from dotenv import load_dotenv

load_dotenv("../../infra/.env")

j = json.load(open("../../keycloak/import/storyteller.json"))

clientArray = j["clients"]

for client in clientArray:
    if client["name"] == "storytellr-backend":
        client["secret"] = str(os.environ['BACKEND_CLIENT_SECRET'])
        break

with open("../../keycloak/import/processed_storyteller.json", "w") as outfile:
    json.dump(j, outfile, indent=4)
