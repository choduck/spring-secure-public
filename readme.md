# Spring Boot Application Deployment to SAP BTP

This is a simple Spring Boot application that is deployed to SAP Business Technology Platform (BTP) using a `manifest.yml` file.

## Application Overview

The application is a basic web application built with Spring Boot. It has a couple of REST endpoints (`/` and `/hello`) that return simple greetings.

## Deployment to SAP BTP

The application is deployed to SAP BTP using the Cloud Foundry command-line interface (CLI). The `manifest.yml` file in the root directory of the project specifies the configuration for the deployment.

### Prerequisites

- You need to have the Cloud Foundry CLI installed on your machine.
- You need to have an account on SAP BTP.

### Deployment Steps

1. Login to SAP BTP:

    ```bash
    cf login -a https://api.cf.<region>.hana.ondemand.com
    ```

    Replace `<region>` with the region of your SAP BTP account.

2. Select your org and space. If you don't know your org or space, you can list them with `cf orgs` and `cf spaces` respectively.

    ```bash
    cf target -o <org> -s <space>
    ```

    Replace `<org>` and `<space>` with your org and space respectively.

3. Deploy the application:

    ```bash
    cf push
    ```

    This command will read the `manifest.yml` file and deploy the application according to its configuration.
