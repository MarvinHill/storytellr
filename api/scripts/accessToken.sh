curl -X POST '$1/protocol/openid-connect/token' \
-H "Content-Type: application/x-www-form-urlencoded" \
-H "Authorization: Basic $2" \
-d 'grant_type=client_credentials'