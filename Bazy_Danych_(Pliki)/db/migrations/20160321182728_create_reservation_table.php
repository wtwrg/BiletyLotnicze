<?php

use Phinx\Migration\AbstractMigration;

class CreateReservationTable extends AbstractMigration
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
        $table = $this->table('rezerwacja', ['id' => 'id_rezerwacja']);
        $table->addColumn('karta_pokladowa', 'string', ['limit' => 45])
            ->addColumn('rzad', 'integer')
            ->addColumn('miejsce', 'integer')
            ->addColumn('zmiana_rezerwacji', 'boolean')
            ->addColumn('id_uzytkownik', 'integer')
            ->addForeignKey('id_uzytkownik', 'uzytkownik', ['uzytkownik_id'])
            ->create();
        
    }
}
