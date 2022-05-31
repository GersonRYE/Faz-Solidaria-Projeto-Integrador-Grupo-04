*npm install
*npx knex migrate:latest
*npm start
testando


routes:
    GET /pagamentos 
            optional: ?page=PAGENUMBER

    POST /pagamentos/pagamento
            body:{
                format: JSON,
                data:  {
                    "value": number,
                    "user": string,
                    "card": MOD_10_NUMBER
                }
            }
            
    GET /pagamentos/pagamento/PAGAMENTO_ID