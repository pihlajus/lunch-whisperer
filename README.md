# Lunch whisperer
Serverless Kotlin application which screen scrapes and parses lunch info from [lounaat.info](https://www.lounaat.info/) and sends wanted data to Slack channel.
This is also my first touch to Kotlin/Kotless.

## Table of contents
- [Lunch whisperer](#lunch-whisperer)
    * [How it works](#how-it-works)
    * [Prerequisites](#prerequisites)
        + [Slack application](#slack-application)
        + [AWS account](#aws-account)
    * [Configuration](#configuration)
    * [Running](#running)
        + [Running locally](#running-locally)
        + [Deployment](#deployment)
    * [Debugging](#debugging)

## How it works
Lunch whisperer uses [Kotless](https://github.com/JetBrains/kotless) - Kotlin serverless framework.
Kotless generates Terraform code from your application code and by its own words,
_gives you one magic button to deploy your Web application as a serverless application on AWS_!

## Prerequisites

### Slack application
Slack is free to use and you can create [Slack application](https://api.slack.com/apps) very quickly and
without any programming.

### AWS account
You have to have AWS account and which can be used with [Kotless framework](https://github.com/JetBrains/kotless).
Kotless uses Terraform under the hood to create the needed AWS resources and configurations.
Hadr Hariri has written an [instruction](https://hadihariri.com/2020/05/12/from-zero-to-lamda-with-kotless/)
how to set up your AWS account for Kotless.

## Configuration
There are few constant variables which you have to fill in to [Main.kt](src/main/kotlin/fi/atkpihlainen/lunchWhisperer/Main.kt).
Currently, there is no possibility to use environment variables with Kotless like in [Serverless framework](https://serverless.com/).
If there are some secrets that you don't want to keep in VCS you can use e.g. [AWS secrets manager](https://aws.amazon.com/secrets-manager/).


| Variable      | Description   |
| ------------- | ------------- |
| LOUNAAT_INFO_URL | API key that enables Grafana API usage |
| SLACK_OAUTH_TOKEN | Created automatically by Slack app, enables request verification |
| SLACK_CHANNEL_ID | Slack channel id |
| MENUS_TO_SENT | Restaurant names, which menus you want to send to Slack |
| FAVOURITE_DISHES | List you favourite dishes and if you favourite is available somewhere, menu is sent to Slack |

For deployment purposes fill AWS specific parameters to [build.gradle.kts](build.gradle.kts).

## Running

### Running locally
You can run application locally using `kotless local` Gradle task.

### Deployment
Deploying is done using `kotless deploy` Gradle task.

## Debugging
The only way I managed to debug the application locally was using remote JVM debugging with good old
VM options `-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005` and attaching
to process.

