# XML dowloading app

## Overview

Kopidlno Village Data Processor is a Spring Boot application that downloads, extracts, and parses XML data from a remote URL, and stores specific data into a PostgreSQL database.
The application utilizes DOM for XML parsing and Hibernate for ORM.

## Features

- Downloads a ZIP file containing XML data from the specified [URL](https://www.smartform.cz/download/kopidlno.xml.zip)!
- Extracts and parses the XML file using DOM.
- Stores parsed data into PostgreSQL using Hibernate.
- Utilizes Spring Data JPA for easy database interaction.

## Prerequisites

- Java 8 or higher
- Maven
- PostgreSQL