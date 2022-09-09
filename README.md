# Java Project Lvl2
Java Project Lvl2 is a CLI application that allows you to compare text differences between two files.

Application features:
 - Supports various input formats: YAML and JSON
 - Report generation as plain text, stylish and JSON

I use the picocli framework to easily create a CLI application and
Gradle as a build automation tool. GitHub Actions acts as a continuous integration (CI) platform.
The JUnit framework is used to write tests. I also use Make & Makefile for frequently used commands in order to save time.

### Hexlet tests and linter status:
[![Actions Status](https://github.com/hopetoknow/java-project-lvl2/workflows/hexlet-check/badge.svg)](https://github.com/hopetoknow/java-project-lvl2/actions)

### GitHub Actions workflow status badge
[![Java CI](https://github.com/hopetoknow/java-project-lvl2/actions/workflows/main.yml/badge.svg?branch=main)](https://github.com/hopetoknow/java-project-lvl2/actions/workflows/main.yml)

### Maintainability badge from Code Climate
[![Maintainability](https://api.codeclimate.com/v1/badges/644d800d4484e158c119/maintainability)](https://codeclimate.com/github/hopetoknow/java-project-lvl2/maintainability)

### Test Coverage badge from Code Climate
[![Test Coverage](https://api.codeclimate.com/v1/badges/644d800d4484e158c119/test_coverage)](https://codeclimate.com/github/hopetoknow/java-project-lvl2/test_coverage)

## App launch example

![Differ gif](https://raw.githubusercontent.com/hopetoknow/my-gifs/main/GIFs/Differ.gif)

[Asciinema URL](https://asciinema.org/a/h4TTKdvEvwTcYSjtImWkGUDrJ)

## Install
```sh
make
```

## Run
```sh
cd build/install/app/bin/
./app [-hV] [-f=format] <filepath1> <filepath2> 
```
```sh
  <filepath1>           path to first file
  <filepath2>           path to second file
  -f, --format=format   output format [default: stylish] Possible values: stylish, plain, json.
  -h, --help            Show this help message and exit.
  -V, --version         Print version information and exit.
```

## Build
```sh
make build
```

## Run checkstyle
```sh
make lint
```

## Check for Dependency Updates
```sh
make check-updates
```