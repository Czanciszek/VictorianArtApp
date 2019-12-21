# VictorianArtApp
##TODO:

###Aplikacja:

Ogólne:
* Poprawność wpisywanych danych (praktycznie wszędzie)

Bootstrap:
* ogólny wygląd aplikacji

Zamówienia:
* Edycja zamówień
* Usuwanie zamówień

Panel zamówień:
* Filtry produktów (po nicku, typie)
* Info: Typ1 (z imieniem i datą), Typ2 (projekt)

Dodać:
* Panel zamówień gotowych do wysyłki
_______________________
###Bazy danych:

Trigger
* Obniżanie stanu magazynu po dodaniu produktu
* Zmiana gotowości produktu, jeśli wszystkie daty są NOT NULL
* Zmiana gotowości zamówienia, jeśli wszystkie produktu są gotowe