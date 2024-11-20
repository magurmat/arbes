# Telephone Bill Calculator Library

**Telephone Bill Calculator** is a Java library for calculating the cost of telephone calls based on specific billing rules. The library provides a simple and extensible API for handling call logs and computing total costs with support for promotional offers.

---

## Features

- Calculates call costs based on:
    - **Daytime rates (8:00–16:00)**: 1.00 Kč per started minute.
    - **Nighttime rates**: 0.50 Kč per started minute.
    - **Reduced rates** for calls longer than 5 minutes: 0.20 Kč per started minute beyond the first 5 minutes.
- Supports promotional rules:
    - Excludes calls to the most frequently called phone number from billing.
    - If multiple numbers have the same frequency, the numerically highest number is excluded.
- Easy-to-use interface for integration into other Java projects.

---

## Getting Started

### Prerequisites

- **Java 17** or later
- **Gradle 8.4** or later

---

### Building the Library

1. Clone the repository:
   ```bash
   git clone https://github.com/magurmat/arbes.git
   cd arbes

2. Build the project:
   ```bash
   ./gradlew build

3. Run tests to ensure everything works:
   ```bash
   ./gradlew test

4. Run tests to ensure everything works:
   ```bash
   ./gradlew publishToMavenLocal

This will publish the library to the default local Maven repository at: ~/.m2/repository/com/phonecompany/billing/arbes/1.0.0


## Using the Library in Another Project

### Adding the Dependency

In your new project's build.gradle file, add the following:
```groovy
       plugins {
        id("java")
        }
        
        repositories {
            mavenCentral()
            mavenLocal() // Include the local Maven repository
        }
        
        dependencies {
            implementation("com.phonecompany.billing:arbes:1.0.0")
        }
```

### Example usage

Here’s how you can use the library in your project:
```java
import com.phonecompany.billing.service.TelephoneBillCalculator;
import com.phonecompany.billing.service.TelephoneBillCalculatorImpl;

public class Main {
public static void main(String[] args) {
TelephoneBillCalculator calculator = new TelephoneBillCalculatorImpl();

        // Example call log
        String phoneLog = "420774577453,13-01-2020 18:10:15,13-01-2020 18:12:57\n" +
                          "420776562353,18-01-2020 08:59:20,18-01-2020 09:10:00";

        // Calculate the total cost
        System.out.println("Total cost: " + calculator.calculate(phoneLog));
    }