# VictorianArtApp
##TODO:

###Aplikacja:

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
* Panel wykończeń (po wydrukowaniu)
_______________________
###Bazy danych:

Tabela procedura:
* Kolumna na cenę zamówienia

Trigger
* Obniżanie stanu magazynu po dodaniu produktu
* Zmiana gotowości produktu, jeśli wszystkie daty są NOT NULL
* Zmiana gotowości zamówienia, jeśli wszystkie produktu są gotowe