# Psychiatric Management Tool
> **Important:** Please note that the contents of this file are in German, since this file is important for a project at a German university.

Dieses Dokument beinhaltet eine vollständige Dokumentation des Psychiatric Management Tools.

###### Inhaltsverzeichnis
1. [Domain Driven Design](#1-domain-driven-design)
    * [Analyse der Ubiquitous Language](#analyse-der-ubiquitous-language)
    * [Analyse und Begründung der verwendeten Muster](#analyse-und-begründung-der-verwendeten-muster)
2. [Clean Architecture](#2-clean-architecture)
    * [Schichtarchitektur planen und begründen](#schichtarchitektur-planen-und-begründen)
3. [Programming Principles](#3-programming-principles)
4. [Refactoring](#4-refactoring)
5. [Entwurfsmuster](#5-entwurfsmuster)

<br/>

## 1 Domain Driven Design
Ein zentrales Element zur Qualitätssicherung ist die Realisierung des Domain Driven Design (DDD) innerhalb des Programmes. Im Nachfolgenden wird die Ubiquitous Language (UL) für das Projekt analysiert. Des Weiteren soll analysiert und begründet werden, welche Muster des DDD angewendet werden.

### Analyse der Ubiquitous Language
Im Nachfolgenden wird die UL, die für die umgesetzten Bestandteile des Programmes relevant sind, analysiert.

###### Begriffe der Ubiquitous Language
Folgende Begriffe existieren innerhalb der UL:

Begriff | Definition
--- | ---
Patient | Ein Patient ist eine Person, welche in einer psychiatrischen Einrichtung behandelt wird. Dabei ist diese Person üblicherweise von einer psychischen Krankheit befallen, die während ihrem Aufenthalt geheilt werden soll.
Krisen&shy;interventions&shy;bereich (KIB) | Ein KIB ist ein Raum innerhalb einer psychiatrischen Einrichtung, in welchem sich jeweils maximal ein Patient aufhalten kann. Eine psychiatrische Einrichtung benötigt für jeden eingetragenen Patienten einen KIB. Umgangssprachlich wird ein KIB auch aus "Gummizelle" bezeichnet.

###### Beziehungen
Folgende Beziehungen bestehen zwischen den Begriffen der UL:

* Ein **Patient** ist immer exakt einem **KIB** zugeordnet.
* Ein **Patient** kann während seinem Aufenthalt anderen **KIBs** zugeordnet werden.
* Wird ein **Patient** einem **KIB** zugeordnet, dann muss dieser **KIB** vorher frei sein.
* Ein **KIB** ist entweder durch einen **Patienten** der Einrichtung belegt, oder leer. Es ist nicht möglich, dass ein **KIB** durch einen nicht existierenden **Patienten** belegt ist.
* Ein **KIB** kann nur dann gelöscht werden, wenn dieser nicht durch einen **Patienten** belegt ist.

### Analyse und Begründung der verwendeten Muster
Die taktischen Muster des DDD werden an verschiedenen Stellen der Anwendung verwendet. Im Nachfolgenden sind die entsprechenden Stellen dokumentiert und begründet.

Klasse | Taktisches Muster | Begründung
--- | --- | ---
`PersonalData` | Value Object | Die Klasse `PersonalData` beinhaltet persönliche Daten über Personen, die vom Programm verwaltet werden. Zu den persönlichen Daten zählen beispielsweise Vorname, Nachname und Geburtsdatum. Instanzen dieser Klasse werden durch das Konzept der Dependency Injection in Objekte, die Personen modellieren, eingepflegt. Da sich die persönlichen Daten einer Person selten ändern, eignen sich Value Objects hervorragend zur Realisierung. Sollten sich diese Daten dennoch ändern, so muss ein neues Objekt mit den aktualisierten Daten angelegt werden.
`Patient` | Entity | Die Klasse `Patient` beinhaltet Daten, die einen Patienten betreffen. Zu diesen Daten gehören unter Anderem persönliche Daten (also `PersonalData`), sowie eine eindeutige UUID, die den Patienten innerhalb des Programmes identifiziert. Aufgrund dieser UUID eignet sich eine Realisierung als Entity hervorragend.
`RoomData` | ValueObject | Die Klasse `RoomData` beinhaltet alle Daten über einen Raum der psychiatrischen Einrichtung. Aktuell beinhaltet diese Klasse lediglich ein Attribut, welches einen Anzeigenamen für einen Raum speichert. Später kann die Klasse jedoch erweitert werden um andere wichtige Daten über Räume zu beinhalten, wie beispielsweise die Fläche eines Raumes, o.Ä. Da sich die Daten dieser Klasse üblicherweise nicht ändern, eigenet sich hier eine Realisierung durch Value Objects hervorragend.
`CrisisInterventionArea` | Entity | Die Klasse `CrisisInterventionArea` modelliert einen KIB, dem ein Patient zugewiesen werden kann. Sie besteht aus Raumdaten (also `RoomData`) und einer UUID, welche den KIB im Programm eindeutig identifiziert. Außerdem speichern Objekte der Klasse die UUID des Patienten, welcher dem KIB zugeordnet ist. Ist dem KIB kein Patient zugewiesen, so besitzt diese UUID den Wert `null`. Dies kann entsprechend einer Methode `hasAssignedPatient()` überprüft werden. Da die Assoziation zwischen Patienten und KIB über die Klasse `CrisisInterventionArea` realisiert ist, kann maximal ein Patient pro KIB zugeordnet werden, was den Anforderungen entspricht. Durch die vorhandene ID, die den KIB eindeutig identifiziert, und der zugeweisene Patient jederzeit geändert werden kann, eignet sich eine Realisierung als Entity.
`PatientService` | Domain Service | Die Klasse `PatientService` ermöglicht das Erzeugen, Bearbeiten, Abfragen und Löschen von Patienten. Dies ist nötig, da beispielsweise bei der Erzeugung eines Patienten, direkt ein freier KIB belegt werden muss. Ebenfalls muss beim Löschen eines Patienten dessen Assoziation im belegten KIB entfernt werden. Daher ist hier ein Domain Service notwendig.
`CrisisInterventionAreaService` | Domiain Service | Die Klasse `CrisisInterventionAreaService` ermöglicht das Erzeugen, Bearbeiten, Abfragen und Löschen von KIBs. Ähnlich wie bei Patienten ist beim Löschen eines KIB darauf zu achten, dass dieser von keinem Patienten belegt ist. Daher ist auch hier eine Realisierung als Domain Service notwendig.

<br/>

## 2 Clean Architecture
Im Nachfolgenden wird die Schichtarchitektur geplant und begründet.

### Schichtarchitektur planen und begründen
Die grobe Schichtarchitektur ist durch die nachfolgende Darstellung gegeben, wobei nicht alle Klassen der Anwendung enthalten sind:
![](../img/architecture.drawio.svg)

Der Darstellung ist zu entnehmen, dass die Anwendung aus vier Schichten besteht, die im Nachfolgenden genauer erläutert werden.

###### Domain Code
Der Domain Code stellt die innerste Schicht der Anwendung dar. Die Schicht enthält alle Domain-spezifischen Klassen. Demnach sind in dieser Schicht beispielsweise Value Objects und Entities enthalten, die [hier](#analyse-und-begründung-der-verwendeten-muster) bereits erläutert wurden. Quellcode, der in dieser Schicht zu finden ist, ist vollständig Unabhängig von anderen Schichten.

Für die einzelnen Entitäten, die in dieser Schicht definiert sind, werden im Domain Code ebenfalls Repository-Interfaces (`PatientRepsoitory` und `CrisisInterventionAreaRepository`) definiert, die in höheren Schichten implementiert werden.

Für die einzelnen Entitäten sind im Domain Code ebenfalls Domain Services definiert (`PatientService` und `CrisisInterventionAreaService`). Diese Services erlauben das Erstellen, Bearbeiten und Löschen von Entitäten und stellen dabei sicher, dass die Entitäten keine Invarianten zulassen. Beispielsweise werden unter Anderem folgende Invarianten abgefangen:
* Patient wird erstellt und keinem (leeeren) KIB zugeordnet
* KIB wird gelöscht während ein Patient diesem zugewiesen ist
* Patient wird einem bereits belegten KIB zugewiesen

###### Application Code
Der Application Code enthält alle Klassen und Interfaces, die innerhalb der Anwendung weitläufig verwendet werden.

Dies bedeutet, dass in dieser Schicht die Implementierung aller Befehle enthalten ist, die über die Kommandozeile aufgerufen werden können. Zu diesen Befehlen zählen beispielsweise `CreatePatientCommand`, welcher einen neuen Patienten erstellt oder `ListCiasCommand`, welcher alle verfügbaren KIBs auflistet. Um in den äußeren Schichten möglichst unabhängig von der Implementierung der Befehle zu sein, implementieren diese Klassen jeweils das Interface `Command`. Über dieses Interface greifen die äußeren Schichten auf die einzelnen Befehle zu und führen diese aus.

###### Plugins
Diese Schicht enthält die konkrete Implementierung der Repositories. Dabei sind die Repositories derzeit lediglich so implementiert, dass die Daten aus lokalen JSON-Dateien geladen werden. Weitere Implementierungen können in Zukunft jedoch hinzugefügt werden, sodass andere Datenquellen (wie beispielsweise eine lokale Datenbank oder eine REST-API) für die Anwendung herangezogen werden können.

Außerdem enthält die Schicht die `ConsoleAdapter`-Klasse, welche die Befehlseingabe über die Kommandozeile ermöglicht. Eingegebene Befehle werden durch den `CommandParser` geparst und auf implementierte Befehle aus der [Application Code](#application-code)-Schicht über die `CommandRegistry` gemappt. Diese Aufteilung ist zur Erfüllung Des Single Responsibility-Prinzip durchgeführt.

Außerdem enthält diese Schicht alle Schnittstellen, über welche die Anwendung mit externem Quellcode kommuniziert. Aktuell enthält die Schicht lediglich eine Klasse `FileSerializer`, welche strukturierte JSON-Dateien lesen und schreiben kann. Dazu wird die Gson-Bibliothek von Google verwendet. Da es sich hierbei um eine externe Software handelt, wird der Zugriff auf diese Bibliothek durch die Klasse `FileSerializer` abstrahiert. Dadurch entsteht im Quellcode eine einzige zentrale Stelle, in welcher der Serialisierungsalgorithmus bei Bedarf ausgetauscht werden kann.

<br/>

## 3 Programming Principles
Um einen qualitativen Quellcode zu realisieren, wird bei der Entwicklung der Software auf die Anwendung von Programmierprinzipien geachtet. Im Nachfolgenden werden dazu eingige der Prinzipien erläutert.

###### Single Responsibility-Prinzip
Das Single Responsibility Prinzip erfordert, dass eine Klasse im Quellcode ausschließlich eine einzige Aufgabe wahrnimmt.

Dieses Prinzip lässt sich beispielsweise anhand der Beziehung zwischen den einzelnen Repositories und der Klasse `FileSerializer` erkennen.

Die Repositories stellen der Anwendung strukturierte Daten zur Verfügung, die persistent gespeichert werden. Die konkreten Implementiertungen `FilePatientRepository`  und `FileCrisisInterventionAreaRepository` persistieren die Daten jeweils in lokalen Dateien. Dabei ist die Aufgabe der Repositories jedoch ausschließlich, der Anwendung Zugriff auf die Daten zu ermöglichen. Dies ist die einzige Aufgabe für diese Klassen.

Die Klasse `FileSerializer` wird von den zuvor genannten Repositories verwendet, um die Daten persistent in lokalen Dateien zu speichern. Dabei ist die einzige Aufgabe des `FileSerializers` das (De-)Serialisieren und Speichern bzw. Laden der Daten.

Somit wird durch diese Aufteilung der Aufgaben sichergestellt, dass die jeweiligen Klassen ausschließlich Ihre entsprechenden Aufgaben wahrnehmen. Dies erhäht die Übersichtlichkeit und Wartbarkeit des Quellcodes.

Außerdem kann somit einfach der Serialisierungsalgorithmus angepasst oder ausgetauscht werden, falls beispielsweise eine andere Bibliothek verwendet werden soll.

###### Open / Closed-Prinzip
Das Open / Closed-Prinzip erfordert, dass Klassen für Erweiterungen offen sind, jedoch keine Modifikationen erlauben. Dies kann im Quellcode anhand der Repositories erkannt werden.

Die Geschäftslogik der Anwendung interagiert ausschließlich mit den Interfaces der entsprechenden Repositories. Deren Implementierung ist für die Funktionsweise des Programmes im Wesentlichen unerheblich.

Zum Funktionieren der Anwendung wird minimal eine einzige Implementierung für jedes Repository verfügbar sein, was aktuell durch `FilePatientRepository` und `FileCrisisInterventionAreaRepository` gegeben ist. Diese Klassen ermöglichen das Persistieren von Daten in lokalen JSON-Dateien.

Die Interfaces sind offen für Erweiterungen, da sie von anderen Klassen implementiert werden können. Soll beispielsweise eine Unterstützung für eine lokale Datenbank hinzugefügt werden, so müssen die entsprechenden Interfaces durch weitere Klassen implementiert werden, die die Verbindung zur Datenbank herstellen. Die Anwendung könnte dann später entscheiden, welche der Implementierungen verwendet werden sollen.

Die Interfaces sind geschlossen für Änderungen, da es sich hierbei lediglich um Interfaces handelt und für die Erweiterungen (bspw. die Unterstützung einer lokalen Datenbank) keine Modifizierung bisherigen Quellcodes benötigt wird.

###### Liskov'sches Substitutionsprinzip
Das Liskov'sche Substitutionsprinzip besagt, dass alle Klassen im Quellcode durch ihre Unterklassen ersetzt werden können, ohne dabei undefiniertes Verhalten zu erzeugen. Obwohl das Projekt keine Vererbungen verwendet, kann das Prinzip durch die Interfaces erklärt werden, die zur Realisierung der Repositories verwendet werden.

Die Geschäftslogik der Anwendung interagiert ausschließlich mit den definierten Interfaces. Die eigentliche Implementierung der Interfaces ist für das Funktionieren also unerheblich. Daher ist es möglich, an jeder Stelle des Quellcodes die entsprechenden konkreten Implementierungen anstatt der Interfaces einzusetzen.

###### Interface Segregation-Prinzip
Das Interface Segregation-Prinzip erfordert, dass mehrere kleinere Interfaces einem (oder wenigen) großen Interfaces bevorzugt werden sollen. So können Klassen ausschließlich die Interfaces implementieren, die auch benötigt werden.

Dieses Prinzip lässt sich ebenfalls an der Realisierung der Repositories erkennen. Die Anwendung greift nicht direkt auf die konkreten Implementierung der Repositories (also `FilePatientRepository`, `FileCrisisInterventionAreaRepository` oder andere konkrete Implementierungen die später hinzugefügt werden können) zu, sondern lediglich über deren Interfaces `PatientRepository` und `CrisisInterventionAreaRepository`. Die zuvor genannten konkreten Implementierungen dieser Interfaces implementieren zusätzlich jeweils noch das Interface `SavableRepository`, welches Methoden zum Laden und Speichern der Daten ergänzt. Dies wird beim Starten und Beenden der Anwendung benötigt, damit Daten aus Dateien (oder mit alternativen Implementierungen aus einer Datenbank, REST-API oder Ähnlichem) geladen und gespeichert werden können. Wird der Code jedoch für Unit-Tests ausgeführt, werden üblicherweise Mock-Daten verwendet. Diese Mock-Daten erfordern nicht die Fähigkeit Daten persistent zu laden und speichern. Daher können Mock-Implementierungen für die Repositories auf die Implementierung des `SavableRepository`-Interface verzichten.

###### Dependency Inversion-Prinzip
Das Depdendency Inversion-Prinzip besagt, dass innere Schichten (also beispielsweise [Domain Code](#domain-code) und [Application Code](#application-code)) nicht von konkreten Implementierungen äußerer Schichten (also zum Beispiel [Adapters](#adapters) und [Plugins](#plugins)) abhängig sind. Dazu können Interfaces verwendet werden.

Im Programm kann man dieses Prinzip ebenfalls an den Repositories erkennen. Im Application Code sind für die einzelnen Repositories jeweils Interfaces definiert, über welche auf die Repositories zugegriffen sind. Die konkrete Implementierung, die sich hinter dem Interface verbirgt, ist dabei für die inneren Schichten unerheblich. Dadurch sind die inneren (langlebigen) Schichten nicht von konkreten Implementiertungen äußerer Schichten abhängig.

<br/>

## 4 Refactoring
Hello, World!

<br/>

## 5 Entwurfsmuster
Hello, World!

<br/>

***

2025-03-06  
&copy; 2025 Christian-2003
