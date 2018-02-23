# Overview

The plugin adds a feature of recurring issues to Jira. **It is under construction.**
You can read how it is created in [Jira plugin development tutorial](https://sunrayapps.com/tutorials/jira/plugin-development/overview-0.html).
The plugin will be available for free on the Atlassian's marketplace.

# Supported Jira versions

All the Jira versions between 7.2.0 and 7.8.0 are supported.

# How to build the plugin

## Requirements

You need to install Java 8 in order to build the plugin from the source.

If you build the plugin for the first time. You need to install additional dependencies
(not accessible via maven repositories)

```
java -jar jpdd-1.0.jar -m ./mvnw
```

## Build

```
./mvnw clean package
```

## Run integration tests

You need to install chrome in order to run integration tests. By default, the tests are run in headless mode. Currently, integration tests work only on 64 bit Linux.

```
./mvnw clean integration-test -Djira.version=7.2.12
```