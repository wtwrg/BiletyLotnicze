<?php

use Phinx\Seed\AbstractSeed;

class gReservationSeeder extends AbstractSeed
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
                'karta_pokladowa' => $faker->bothify('??#?##'),
                'rzad' => $faker->numberBetween($min = 1, $max = 20),
                'miejsce' => $faker->numberBetween($min = 1, $max = 100),
                'zmiana_rezerwacji' => $faker->randomElement($array = array (0,1)),
                'id_uzytkownik' => $faker->numberBetween($this->fetchRow('SELECT uzytkownik_id FROM uzytkownik')[0], $max = $this->fetchRow('SELECT count(*) FROM uzytkownik')[0]),
                'id_lot' => $faker->numberBetween($this->fetchRow('SELECT id_lot FROM lot')[0], $max = $this->fetchRow('SELECT count(*) FROM lot')[0])
            ];
        }

        $this->insert('rezerwacja', $data);
    }
}
