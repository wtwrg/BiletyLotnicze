<?php

use Phinx\Seed\AbstractSeed;

class dPlaneSeeder extends AbstractSeed
{
    /**
     * Run Method.
     *
     * Write your database seeder using this method.
     *
     * More information on writing seeders is available here:
     * http://docs.phinx.org/en/latest/seeding.html
     */
    public function run()
    {
        $faker = Faker\Factory::create();
        $data = [];
        for ($i = 0; $i < 100; $i++) {
            $data[] = [
                'nazwa_samolotu' => $faker->word,
                'klasa_lotu' => $faker->randomElement($array = array (
                    'Business Class',
                    'Premium Economy',
                    'Economy Class',
                    'Economy Simple'
                    ))
            ];
        }

        $this->insert('samolot', $data);
    }
}
