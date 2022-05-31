const express = require('express');
const Pagamentos = require('./controllers/pagamentos')


const routes = express.Router();


routes.get('/pagamentos', Pagamentos.getAllPayments)
routes.get('/pagamentos/pagamento/:id', Pagamentos.getPayment)
routes.post('/pagamentos/pagamento', Pagamentos.createPayment)



module.exports = routes;