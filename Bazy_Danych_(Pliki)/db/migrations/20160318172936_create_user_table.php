<?php

use Phinx\Migration\AbstractMigration;

class CreateUserTable extends AbstractMigration
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
        $table = $this->table('uzytkownik', ['id' => 'uzytkownik_id']);
        $table->addColumn('imie', 'string', ['limit' => 45])
            ->addColumn('nazwisko', 'string', ['limit' => 45])
            ->addColumn('plec', 'boolean')
            ->addColumn('pesel', 'biginteger')
            ->addColumn('numer_paszportu', 'string', ['limit' => 45])
            ->addColumn('ulica', 'string', ['limit' => 45])
            ->addColumn('nr_domu', 'integer')
            ->addColumn('numer_tel', 'integer')
            ->create();
    }
}
