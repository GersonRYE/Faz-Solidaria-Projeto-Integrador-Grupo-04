/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.up = function (knex) {
    return knex.schema.createTable('pagamentos', (table) => {
        table.string('id').primary()
        table.string('user').notNullable()
        table.integer('value').notNullable()
        table.integer('card').notNullable()
    })
};

/**
 * @param { import("knex").Knex } knex
 * @returns { Promise<void> }
 */
exports.down = function (knex) {
    return knex.schema.dropTable('pagamentos')
};
