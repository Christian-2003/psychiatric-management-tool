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

<br/>

## 3 Programming Principles
Hello, World!

<br/>

## 4 Refactoring
Hello, World!

<br/>

## 5 Entwurfsmuster
Hello, World!

<br/>

***

&copy; 2025 Christian-2003
