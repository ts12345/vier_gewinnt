REM  VIER GEWINNT - Startskript für den Server

REM am Zeilenanfang bedeutet, dass es sich um einen Kommentar handelt


REM  Schaltet die Anzeige der Befehle aus
REM  Das "@" bedeutet, dass der Befehl selbst nicht angezeigt wird
@ECHO OFF

REM  Löscht das, was bisher auf der Konsole angezeigt wurde:
cls

REM  Zeigt die Überschrift an:
ECHO **** VIER GEWINNT - SERVER ****
ECHO ===============================

REM  Fügt nach dem Titel eine Leerzeile ein:
ECHO.

REM  Wechselt in das Verzeichnis mit dem "Vier Gewinnt"-Server:
cd %CD%

REM  Startet den "Vier Gewinnt"-Server
java -Dfile.encoding=cp850 STARTER
