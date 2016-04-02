<?php

use Phinx\Seed\AbstractSeed;

class cUserSeeder extends AbstractSeed
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
            if($i % 7 == 0)
                $data[] = [
                    'imie' => $faker->firstNameMale,
                    'nazwisko' => $faker->lastName,
                    'plec' => 1,
                    'pesel' => $faker->numerify('###########'),
                    'numer_paszportu' => $faker->numerify('ASE ####'),
                    'ulica' => $faker->streetName,
                    'nr_domu' => $faker->buildingNumber,
                    'numer_tel' => $faker->e164PhoneNumber,
                    'id_miejscowosc' => $faker->numberBetween($this->fetchRow('SELECT id_miejscowosc FROM miejscowosc')[0], $max = $this->fetchRow('SELECT count(*) FROM miejscowosc')[0])
                ];
            else
            $data[] = [
                'imie' => $faker->firstNameFemale,
                'nazwisko' => $faker->lastName,
                'plec' => 0,
                'pesel' => $faker->numerify('###########'),
                'numer_paszportu' => $faker->numerify('ASL ####'),
                'ulica' => $faker->streetName,
                'nr_domu' => $faker->buildingNumber,
                'numer_tel' => $faker->e164PhoneNumber,
                'id_miejscowosc' => $faker->numberBetween($this->fetchRow('SELECT id_miejscowosc FROM miejscowosc')[0], $max = $this->fetchRow('SELECT count(*) FROM miejscowosc')[0])
            ];
        }

        $this->insert('uzytkownik', $data);
    }
}
