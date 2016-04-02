<?php

use Phinx\Seed\AbstractSeed;

class fFlightSeeder extends AbstractSeed
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
                'czas_przylotu' => $faker->dateTimeInInterval($startDate = 'now', $interval = '+ 3 hours')->format('Y-m-d H:i:s'),
                'czas_odlotu' => $faker->dateTimeInInterval($startDate = 'now', $interval = '+ 0 hours')->format('Y-m-d H:i:s'),
                'cena_lotu' => $faker->randomFloat($nbMaxDecimals = NULL, $min = 0, $max = NULL),
                'id_samolot' => $faker->numberBetween($this->fetchRow('SELECT id_samolot FROM samolot')[0], $max = $this->fetchRow('SELECT count(*) FROM samolot')[0]),
                'id_port' => $faker->numberBetween($this->fetchRow('SELECT id_port FROM port')[0], $max = $this->fetchRow('SELECT count(*) FROM port')[0])
            ];
        }

        $this->insert('lot', $data);
    }
}
