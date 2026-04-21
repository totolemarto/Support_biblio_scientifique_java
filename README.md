# YASCL - Yet Another Scientific Computing Library in Java

-----------------

|                    |                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **General**        | [![License](https://img.shields.io/github/license/totolemarto/Support_biblio_scientifique_java)](LICENSE) [![Java Version](https://img.shields.io/badge/Java-21-blue)](https://docs.oracle.com/en/java/javase/21/) [![Jitpack](https://jitpack.io/v/totolemarto/Support_biblio_scientifique_java.svg)](https://jitpack.io/#totolemarto/Support_biblio_scientifique_java)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| **SonarQube**      | [![Quality Gate Status](https://im2ag-sonar.univ-grenoble-alpes.fr/api/project_badges/measure?project=projet-m1-devops-yascl&metric=alert_status&token=sqb_7380cf6250cd6d41849c16f099cfea2f42b94b0e)](https://im2ag-sonar.univ-grenoble-alpes.fr/dashboard?id=projet-m1-devops-yascl) [![Coverage](https://im2ag-sonar.univ-grenoble-alpes.fr/api/project_badges/measure?project=projet-m1-devops-yascl&metric=coverage&token=sqb_7380cf6250cd6d41849c16f099cfea2f42b94b0e)](https://im2ag-sonar.univ-grenoble-alpes.fr/dashboard?id=projet-m1-devops-yascl) [![Security Rating](https://im2ag-sonar.univ-grenoble-alpes.fr/api/project_badges/measure?project=projet-m1-devops-yascl&metric=software_quality_security_rating&token=sqb_7380cf6250cd6d41849c16f099cfea2f42b94b0e)](https://im2ag-sonar.univ-grenoble-alpes.fr/dashboard?id=projet-m1-devops-yascl)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
| **GitHub Actions** | [![Github Pages](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/github-pages.yaml/badge.svg)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/github-pages.yaml) [![CI](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/ci.yaml/badge.svg?branch=main)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/ci.yaml) [![Commitlint](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/commitlint.yaml/badge.svg?branch=main)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/commitlint.yaml) [![.github/workflows/release-please.yaml](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/release-please.yaml/badge.svg?branch=main)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/release-please.yaml) [![Build & Deploy Documentation (dev)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/github-pages.yaml/badge.svg?branch=main)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/github-pages.yaml) [![Build & Deploy Documentation (release)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/release-docs.yaml/badge.svg?branch=main)](https://github.com/totolemarto/Support_biblio_scientifique_java/actions/workflows/release-docs.yaml)  |

## What is it?

YASCL is a Java library for scientific computing. It provides a set of tools inspired by [NumPy](https://numpy.org/).

The project is part of the M1 DevOps course at the University of Grenoble Alpes. The subject can be found [here](https://im2ag-moodle.univ-grenoble-alpes.fr/pluginfile.php/33094/mod_resource/content/9/projet_devops.pdf).

## Table of Contents

- [Main Features](#main-features)
- [License](#license)
- [Where to get it?](#where-to-get-it)
- [Dependencies](#dependencies)
- [Installation from source code](#installation-from-source-code)
- [Documentation](#documentation)
- [Good practices](#good-practices)

## Main Features

This library provides several functionalities:

- Manage arrays with an arbitrary number of dimensions  
- Arrays can contain different data types (int, double, float, String, boolean)  
- Supported operations depend on the data type.  
  For example, numerical values can be added, subtracted, divided, and multiplied  
- Arrays can be printed and reshaped  

## Where to get it?

The source code of the project is available on [GitHub](https://github.com/totolemarto/Support_biblio_scientifique_java).

The link to add the dependency with Jitpack [Jitpack](https://jitpack.io/#totolemarto/Support_biblio_scientifique_java/)

## Dependencies

For the moment, the project does not have any external dependencies. However, it may use some libraries in the future.

## Installation from source code

To be added.

## License

[MIT License](LICENSE)

## Documentation

The documentation of the project is available on [GitHub Pages](https://totolemarto.github.io/Support_biblio_scientifique_java/).

## Good practices


Intégration continue ✅

Insertion de Badges ✅

Valorisation de votre bibliothéque ✅

Utiliser SonarQube pour l’analyse statique de code ✅

Livraison continue (Maven) ✅

## Git Workflow

In order to work collaboratively, we follow this workflow:

- We use multiple branches with prefixes to indicate the type of work (e.g., feature, documentation, etc.)
- When a collaborator wants to merge their work into the main branch, CI checks must pass
- At least one reviewer approval is required before merging
- Direct pushes to the main branch without review are not allowed

-----------------

[Back to top](#yascl---yet-another-scientific-computing-library-in-java)
