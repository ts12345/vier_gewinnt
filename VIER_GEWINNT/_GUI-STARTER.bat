REM  VIER GEWINNT! - Startskript für den grafischen Starter
REM  Dieses Startskript ist nur ein Provisorium, bis VIER GEWINNT! als JAR gepackt vorliegt

REM am Zeilenanfang bedeutet, dass es sich um einen Kommentar handelt


REM  Schaltet die Anzeige der Befehle aus
REM  Das "@" bedeutet, dass der Befehl selbst nicht angezeigt wird
@ECHO OFF

REM  Löscht das, was bisher auf der Konsole angezeigt wurde:
cls

REM  Zeigt den Titel des Spiels an:
ECHO **** VIER GEWINNT ****
ECHO ======================

REM  Fügt nach dem Titel eine Leerzeile ein:
ECHO.

REM  Wechselt in das "Vier Gewinnt"-Verzeichnis:
cd %CD%

REM  Startet "Vier Gewinnt":
java -Dfile.encoding=cp850 MENUE
