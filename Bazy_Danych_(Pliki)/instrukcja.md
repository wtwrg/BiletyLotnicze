#Instalacja

Otworzyć plik phinx.yml, gdzie należy wprowadzić swoje dane konfiguracyjne:

```
development:
        adapter: mysql # rodzaj drivera
        host: localhost # adres hosta
        name: development_db # nazwa bazy danych
        user: root # użytkownik
        pass: '' # hasło
        port: 3306 
        charset: utf8 #kodowanie
```

W tym pliku wazne jest żeby nie zmienić wcieć ponieważ są one odpowednio parsowane

Upewnić się ze na komputerze znajduje się Interpreter PHP oraz 
zainstalowany silnik do obsługi bazy danych.

jak nie to pobierz https://www.apachefriends.org/pl/download.html 
5.6.19 / PHP 5.6.19. 
 
Na komputerze z zainstalowanym interpreterem PHP w wierszu poleceń, 
przejść do lokalizacji z projektem, a nastepnie wykonać polecenie, 
aby zainportować tabelki do bazy danych

```
$ vendor\bin\phinx migrate
```

Aby wypełnić baze przykładowymi danymi nalezy użyć polecenia

```
$ vendor\bin\phinx seed:run
```
