const fs = require('fs')

const ERROR = (service, error) => {
    const now = new Date()
    const messageError = `${now.toLocaleString()} - Error during execution of process: ${error}`
    fs.writeFile(`src/logs/${now.getTime()}_${service}.txt`, messageError, e => console.log(e))
}


module.exports = {
    ERROR
}