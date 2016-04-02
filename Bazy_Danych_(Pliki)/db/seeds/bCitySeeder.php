<?php

use Phinx\Seed\AbstractSeed;

class bCitySeeder extends AbstractSeed
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
            $row = $this->fetchRow('SELECT id_kraj FROM kraj WHERE nazwa_kraj LIKE "'.$faker->country.'"');
            $row = is_bool($row) ? $faker->numberBetween($min = 1, $max = 99) : $row[0];
            $data[] = [
                'nazwa_miejscowosc' => $faker->city,
                'id_kraj' => $row
            ];
        }

        $this->insert('miejscowosc', $data);
    }
}
