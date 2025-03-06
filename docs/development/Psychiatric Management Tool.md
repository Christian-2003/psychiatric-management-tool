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
Ein zentrales Element zur Qualitätssicherung ist die Realisierung des Domain Driven Design (DDD) innerhalb des Programmes.

### Analyse der Ubiquitous Language
Wichtig zum allgemeinen Verständnis der Anwendung ist die Ubiquitous Language (UL) für das Programm. Im Nacchfolgenden wird die UL detailliert analysiert.

###### Begriffe der Ubiquitous Language
Folgende Begriffe existieren innerhalb der UL:

Begriff | Definition
--- | ---
Patient | Ein Patient ist eine Person, welche in einer psychiatrischen Einrichtung behandelt wird. Dabei ist diese Person üblicherweise von einer psychischen Krankheit befallen, die während ihrem Aufenthalt geheilt werden soll.
Krisen&shy;interventions&shy;bereich (KIB) | Ein KIB ist ein Raum innerhalb einer psychiatrischen Einrichtung, in welchem sich jeweils maximal ein Patient ohne Beobachtung von Pflegern aufhalten kann. Eine psychiatrische Einrichtung benötigt für jeden eingetragenen Patienten einen KIB. Umgangssprachlich wird ein KIB auch aus "Gummizelle" bezeichnet.
Pfleger | Pfleger sind Mitarbeiter einer psychiatrischen Einrichtung. Diese Pfleger betreuen und behandeln die psychischen Krankheiten von Patienten.
Büro | Ein Büro ist ein Arbeitszimmer eines Pflegers, in welchem schriftliche Arbeiten, wie beispielsweise das Verfassen von Berichten oder Ähnliches, durchgeführt werden.
Bericht | Ein Bericht beschreibt Vorfälle oder Diagnosen, welche einen (oder mehrere) Patienten betreffen. Dabei werden Berichte immer von einem Pfleger geschrieben und unterschrieben.

###### Beziehungen
Folgende Beziehungen bestehen zwischen den Begriffen der UL:

* Ein **Patient** ist immer exakt einem **KIB** zugeordnet.
* Ein **Patient** wird immer von einem **Pfleger** betreut.
* Ein **Pfleger** hat immer genau ein **Büro**, in welchem schriftliche Arbeiten durchgeführt werden können.
* Ein **Pfleger** kann für einen (oder mehrere) **Patient(en)** einen **Bericht** verfassen, in welchem Vorfälle oder Diagnosen festgehalten werden.
* Ein **Bericht** wird immer sowohl vom **Pfleger**, welcher diesen Bericht verfasst hat, als auch vom **Pfleger** des (oder der) betroffenen **Patienten**, unterschrieben.

### Analyse und Begründung der verwendeten Muster
Die taktischen Muster des DDD werden an verschiedenen Stellen der Anwendung verwendet. Im Nachfolgenden sind die entsprechenden Stellen dokumentiert und begründet.

Klasse | Taktisches Muster | Begründung
--- | --- | ---
`PersonalData` | Value Object | Die Klasse `PersonalData` beinhaltet persönliche Daten über Personen, die vom Programm verwaltet werden. Zu den perwsönlichen Daten zählen beispielsweise Vorname, Nachname und Geburtsdatum. Instanzen dieser Klasse werden durch das Konzept der Dependency Injection in Objekte, die Personen modellieren, eingepflegt. Da sich die persönlichen Daten einer Person selten ändern, eignen sich Value Objects hervorragend zur Realisierung.
`Patient` | Entity | Die Klasse `Patient` ist als Entity realisiert. Sie beinhaltet Daten, die einen Patienten betreffen. Zu diesen Daten gehören unter Anderem persönliche Daten (also `PersonalData`), sowie eine eindeutige UUID. Des Weiteren beinhaltet die Klasse ein Attribut, welches den Pfleger identifiziert, welcher dem Patienten zugewiesen ist. Da sich der zugewiesene Pfleger jederzeit ändern kann, eignen sich Value Objects nicht zur Realisierung eines Patienten. Des Weiteren besitzen Patienten eine eindeutige UUID, über welche diese identifiziert werden können, was die Umsetzung durch eine Entity fördert.
`Nurse` | Entity | Die Klasse `Nurse` ist als Entity realisiert. Sie beinhaltet Daten, die einen Pfleger betreffen. Zu diesen Daten gehören unter Anderem persönliche Daten (also `PersonalData`), sowie eine eindeutige UUID. Des Weiteren beinhaltet die Klasse ein Attribut, welches das Büro identifiziert, in welchem der Pfleger schriftliche Arbeiten durchführt. Da sich das zugewiesene Büro jederzeit ändern kann, eignen sich Value Objects nicht zur Realisierung eines Pflegers. Des Weiteren besitzen Pfleger eine eindeutige UUID, über welche diese identifiziert werden können, was die Umsetzung durch eine Entity fördert.
`RoomData` | ValueObject | Die Klasse `RoomData` beinhaltet alle Daten über einen Raum der psychiatrischen Einrichtung. Diese Daten beinhalten eine eindeutige UUID des Raumes, sowie eine Beschreibung, über welche der Raum für Menschen identifizierbar ist. Da sich diese Daten im täglichen Betrieb nicht ändern (außer möglicherweise bei Umbauten der psychiatrischen Einrichtung), ist dies als Value Object realisiert.
`CrisisInterventionArea` | Entity | Die Klasse `CrisisInterventionArea` modelliert einen KIB, dem ein Patient zugewiesen werden kann. Sie besteht aus Raumdaten (also `RoomData`) und einer UUID, welche den zugewiesenen Patienten identifiziert. Ist dem KIB kein Patient zugewiesen, so besitzt diese UUID den Wert `null`. Dies kann entsprechend einer Methode `hasAssignedPatient()` überprüft werden. Da sich der zugewiesene Patient jederzeit ändern kann ist der KIB als Entity realisiert.
`Office` | Entity | Die Klasse `Office` modelliert ein Büro eines Pflegers. Sie beinhaltet ausschließlich Rauminformationen (also `RoomData`). Die Zuweisung von Pflegern zu Büros ist über Pfleger gelöst, da mehrere Pfleger in einem Büro arbeiten können.

<br/>

## 2 Clean Architecture
Im Nachfolgenden wird die Schichtarchitektur geplant und begründet.

### Schichtarchitektur planen und begründen
Die Schichtenarchitektur kann durch nachfolgendes Diagramm visualisiert werden:
![](../img/architecture.drawio.svg)

Der Darstellung ist zu entnehmen, dass die Anwendung aus vier Schichten besteht, die im Nachfolgenden genauer erläutert werden.

###### Domain Code
Der Domain Code stellt die innerste Schicht der Anwendung dar. Die Schicht enthält alle Domain-spezifischen Klassen. Demnach sind in dieser Schicht beispielsweise Value Objects und Entities enthalten, die [hier](#analyse-und-begründung-der-verwendeten-muster) bereits erläutert wurden. Quellcode, der in dieser Schicht zu finden ist, ist vollständig Unabhängig von anderen Schichten.

Die einzelnen Entitäten, die in dieser Schicht definiert sind, erhalten in weiter oben liegenden Schichten jeweils ein Repository, über welches die Entitäten geladen und gespeichert werden können.

###### Application Code
Der Application Code enthält alle Klassen und Interfaces, die innerhalb der Anwendung weitläufig verwendet werden. Darüber Hinaus enthält die Schicht die Geschäftslogik für die Anwendung.

Eine besondere Eigenschaft dieser Schicht besteht darin, dass sie die Repositories der im Domain Code spezifizierten Entitäten enthält. Jede Entität erhält dabei ein eigenes Repository. Die Schicht definiert dabei lediglich über Interfaces, welche Methoden die einzelnen Repositories haben. Im Sinne der Erweiterbarkeit sollen in dieser Schicht keine konkreten Implementierungen der Repositores vorhanden sein. Die Implementierung wird in einer höheren Schicht vorgenommen, damit diese  ausgetauscht werden können. So kann beispielsweise ermöglicht werden, dass die Repositories zum Laden der Daten entweder auf lokale Dateien, oder auf eine REST-API zugreifen - abhängig von der Implementierung.

###### Adapters
Die Schicht der Adapters enthält die konkrete Implementierung der Repositories. Dabei sind die Repositories derzeit lediglich so implementiert, dass die Daten aus lokalen Dateien geladen werden. Weitere Implementierungen können in Zukunft jedoch hinzugefügt werden, sodass andere Datenquellen (wie beispielsweise eine lokale Datenbank oder eine REST-API) für die Anwendung herangezogen werden können.

###### Plugins
Diese Schicht enthält alle Schnittstellen, über welche die Anwendung mit externem Quellcode kommuniziert. Aktuell enthält die Schicht lediglich eine Klasse `FileSerializer`, welche strukturierte JSON-Dateien lesen und schreiben kann. Dazu wird die Gson-Bibliothek von Google verwendet. Da es sich hierbei um eine externe Software handelt, wird der Zugriff auf diese Bibliothek durch die Klasse `FileSerializer` abstrahiert. Dadurch entsteht im Quellcode eine einzige zentrale Stelle, in welcher der Serialisierungsalgorithmus bei Bedarf ausgetauscht werden kann.

<br/>

## 3 Programming Principles
Um einen qualitativen Quellcode zu realisieren, wird bei der Entwicklung der Software auf die Anwendung von Programmierprinzipien geachtet. Im Nachfolgenden werden dazu eingige der Prinzipien erläutert.

###### Single Responsibility-Prinzip
Das Single Responsibility Prinzip erfordert, dass eine Klasse im Quellcode ausschließlich eine einzige Aufgabe wahrnimmt.

Dieses Prinzip lässt sich beispielsweise anhand der Beziehung zwischen den einzelnen Repositories und der Klasse `FileSerializer` erkennen.

Die Repositories stellen der Anwendung strukturierte Daten zur Verfügung, die persistent gespeichert werden. Die konkreten Implementiertungen `FilePatientRepository`, `FileNurseRepository`, `FileOfficeRepository` und `FileCrisisInterventionAreaRepository` persistieren die Daten jeweils in lokalen Dateien. Dabei ist die Aufgabe der Repositories jedoch ausschließlich, der Anwendung Zugriff auf die Daten zu ermöglichen. Dies ist die einzige Aufgabe für diese Klassen.

Die Klasse `FileSerializer` wird von den zuvor genannten Repositories verwendet, um die Daten persistent in lokalen Dateien zu speichern. Dabei ist die einzige Aufgabe des `FileSerializers` das (De-)Serialisieren und Speichern bzw. Laden der Daten.

Somit wird durch diese Aufteilung der Aufgaben sichergestellt, dass die jeweiligen Klassen ausschließlich Ihre entsprechenden Aufgaben wahrnehmen. Dies erhäht die Übersichtlichkeit und Wartbarkeit des Quellcodes.

###### Open / Closed-Prinzip
Das Open / Closed-Prinzip erfordert, dass Klassen für Erweiterungen offen sind, jedoch keine Modifikationen erlauben. Dies kann im Quellcode anhand der Repositories erkannt werden.

Die Geschäftslogik der Anwendung interagiert ausschließlich mit den Interfaces der entsprechenden Repositories. Deren Implementierung ist für die Funktionsweise des Programmes im Wesentlichen unerheblich.

Zum Funktionieren der Anwendung wird minimal eine einzige Implementierung für jedes Repository verfügbar sein, was aktuell durch `FilePatientRepository`, `FileNurseRepository`, `FileOfficeRepository` und `FileCrisisInterventionAreaRepository` gegeben ist. Diese Klassen ermöglichen das Persistieren von Daten in lokalen JSON-Dateien.

Die Interfaces sind offen für Erweiterungen, da sie von anderen Klassen implementiert werden können. Soll beispielsweise eine Unterstützung für eine lokale Datenbank hinzugefügt werden, so müssen die entsprechenden Interfaces durch weitere Klassen impleemntiert werden, die die Verbindung zur Datenbank herstellen. Die Anwendung kann dann später entscheiden, welche der Implementierungen verwendet werden sollen.

Die Interfaces sind geschlossen für Änderungen, da es sich hierbei lediglich um Interfaces handelt und für die Erweiterungen (bspw. die Unterstützung einer lokalen Datenbank) keine Modifizierung bisherigen Quellcodes benötigt wird.

###### Liskov'sches Substitutionsprinzip
Das Liskov'sche Substitutionsprinzip besagt, dass alle Klassen im Quellcode durch ihre Unterklassen ersetzt werden können, ohne dabei undefiniertes Verhalten zu erzeugen. Obwohl das Projekt keine Vererbungen verwendet, kann das Prinzip durch die Interfaces erklärt werden, die zur Realisierung der Repositories verwendet werden.

Die Geschäftslogik der Anwendung interagiert ausschließlich mit den definierten Interfaces. Die eigentliche Implementierung der Interfaces ist für das Funktionieren also unerheblich. Daher ist es möglich, an jeder Stelle des Quellcodes die entsprechenden konkreten Implementierungen anstatt der Interfaces einzusetzen.

###### Interface Segregation-Prinzip
xyz

###### Dependency Inversion-Prinzip

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
