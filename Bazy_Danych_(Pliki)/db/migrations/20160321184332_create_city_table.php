<?php

use Phinx\Migration\AbstractMigration;

class CreateCityTable extends AbstractMigration
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
        $table = $this->table('miejscowosc', ['id' => 'id_miejscowosc']);
        $table->addColumn('nazwa_miejscowosc', 'string', ['limit' => 45])
            ->create();
        $refTable = $this->table('uzytkownik');
        $refTable->addColumn('id_miejscowosc', 'integer')
            ->addForeignKey('id_miejscowosc', 'miejscowosc', ['id_miejscowosc'])
            ->update();
    }
}
