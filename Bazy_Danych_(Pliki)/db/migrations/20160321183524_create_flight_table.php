<?php

use Phinx\Migration\AbstractMigration;

class CreateFlightTable extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * http://docs.phinx.org/en/latest/migrations.html#the-abstractmigration-class
     *
     * The following commands can be used in this method and Phinx will
     * automatically reverse them when rolling back:
     *
     *    createTable
     *    renameTable
     *    addColumn
     *    renameColumn
     *    addIndex
     *    addForeignKey
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change()
    {
        $table = $this->table('lot', ['id' => 'id_lot']);
        $table->addColumn('czas_przylotu', 'datetime')
            ->addColumn('czas_odlotu', 'datetime')
            ->addColumn('cena_lotu', 'float')
            ->create();
        $refTable = $this->table('rezerwacja');
        $refTable->addColumn('id_lot', 'integer')
            ->addForeignKey('id_lot', 'lot', ['id_lot'])
            ->update();
    }
}
