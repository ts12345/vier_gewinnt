REM  VIER GEWINNT - Startskript
REM  Dieses Startskript ist nur Ersatz für den grafischen Starter, der sich derzeit in Entwicklung befindet

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
java -Dfile.encoding=cp850 STARTER
