const express = require('express')
const routes = require('./routes')

const app = express()


app.use(express.json({limit: '50mb'}))
app.use(routes)

app.listen(3000, ()=>{
    console.log('Aplicação rodando na porta 3000')
})