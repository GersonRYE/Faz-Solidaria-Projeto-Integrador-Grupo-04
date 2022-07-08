const connection = require('../database/connection')
const { v4: uuid } = require('uuid')
const { isValidCardNumber } = require('../utils/validators/validators')

const logger = require('../utils/logger/logger')


module.exports = {

    async getAllPayments(request, response) {
        const { page = 1 } = request.query

        try {
            const pagamentos =
                await connection('pagamentos')
                    .limit(5)
                    .offset((page - 1) * 5)
                    .select([
                        'id',
                        'user',
                        'value'
                    ])


            return response.status(200).json(pagamentos)
        } catch (error) {
            logger.ERROR('getAllPayments', error)
            return response.status(500).json({ message: "Something was wrong" })
        }
    },

    async createPayment(request, response) {
        const { user, value, card } = request.body
        
        if (!isValidCardNumber(card)) {
            return response.status(400).json({ message: "Invalid card number" })
        }

        if (isNaN(value)) {
            return response.status(400).json({ message: "Invalid value" })
        }


        try {
            const id = uuid()
            const [pagamento] =
                await connection('pagamentos')
                    .insert({
                        id,
                        user,
                        value: Number(value),
                        card
                    })


            /**
             * All the business logic to really create a debit at user credit card needs to be implemented from here
             *
             */ 
           
            
            if (pagamento) {
                return response.status(200).json({ message: "Payment was created successfully", id })
            }

        } catch (error) {

            logger.ERROR('createPayment', error)
            return response.status(500).json({ message: "Something was wrong. Verify all required properties" })
        }

    },


    async getPayment(request, response) {
        const { id } = request.params
        try {
            const payment =
                await connection('pagamentos')
                    .where('id', id)
                    .select([
                        'id',
                        'user',
                        'value'
                    ])


            return response.status(200).json(payment)


        } catch (error) {
            logger.ERROR('getPayment', error)
            return response.status(500).json({ message: "Something was wrong. Verify all required properties" })
        }
    }
}