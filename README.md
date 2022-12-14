# Dot Properties

Du to the impossibility to modify the environment variables via Java.
We need to use the properties file.

This module help you to manage your properties in your project.

## How to install ?

You need to clone the repository and execute the following command:
````bash
mvn install
````

You should have Maven installed. If not you can have the instructions to install it here: https://maven.apache.org/install.html

## Basic usage

Before everything you should configure the **JAVA_PROPS** environment variables.

In the following case the DotProperties will try to load properties file in the following order:

````bash
.properties.${JAVA_PROPS} # in the current directory
.properties.${JAVA_PROPS} # in the resource directory
.${JAVA_PROPS}.properties # in the current directory
.${JAVA_PROPS}.properties # in the resource directory
````

_When a file is found, the properties are load and the others files are ignored._

Example:
```java
DotProperties dotProperties = new DotProperties.Builder().build();
```

## Custom properties file

This section help you to custom DotProperties.

### Refresh properties

You can use these functions in builder to activate the refreshing of the properties.

```java
refresh(); // Refresh every 30 seconds
refresh(int seconds); // Number of seconds between each refresh
refresh(Duration duration); // Duration between each refresh
```

Example:
```java
DotProperties dotProperties = new DotProperties.Builder()
        .refresh()
        .build();
```

### Custom properties file

You can choose with file you want to use for your properties. If you use one of these following functions below, the JAVA_PROPS environment variables will be ignored.

````java
setPath(String path) // Set the path of the properties file
setResourcePath(String path) // Set the resource path of the properties file
````

Example:
````java
DotProperties dotProperties = new DotProperties.Builder()
        .setPath("foo.bar")
        .build();
````

### Build with annotations

You can use the annotation **@PropertiesElement** to build your DotProperties.

```java
enum MyTestEnum { VALUE_ONE, VALUE_TWO };

@Property(name = "propertyOne", required = true)
private String propertyOne = "default";

@Property(name = "propertyTwo", required = true)
private String propertyTwo = "default";

@Property(name = "propertyInteger", required = true)
private int propertyInteger = 0;

@Property(name = "propertyEnumBean", required = true)
private MyTestEnum propertyEnum = MyTestEnum.VALUE_ONE;

public static void main(String[] args) throws NoJavaEnvFoundException, PropertiesAreMissingException, IOException{
    DotProperties dotProperties=new DotProperties.Builder()
        .bean(this)
        .setPath(".properties.test")
        .build();
}
```

Supported types:
- Long
- Integer
- Short
- Byte
- Double
- Float
- Boolean
- Character
- Date
- Duration
- Instant
- String
- File
- Pattern
- ZonedDateTime
- TimeZone
- Enum
- ZoneId

### Required properties

You can give a list of properties that are required. If one of these properties is not found, an exception will be thrown.

````java
requires(String... properties); // Add the list to the list of required properties
requires(List<String> properties); // Add the list to the list of required properties
````

Example:
````java
DotProperties dotProperties = new DotProperties.Builder()
        .requires("bar", "baz")
        .build();
````

### Custom JAVA_PROPS

By default DotProperties use the JAVA_PROPS if you don't give directly a file name. But you can define witch environment variables you want to use instead of JAVA_PROPS.

````java
setJavaEnv(String javaEnv); // Set the environment variable to use for the properties file
````

Example:
````java
DotProperties dotProperties = new DotProperties.Builder()
        .setJavaEnv("JAVA_FOO")
        .build();
````

### Events

You can subscribe to the following events:
````java
/**
 * This function is called when the properties are refreshed and a value was updated.
 */
void onPropertyChanged(String propertiesName, String oldValue, String newValue);
````
Example:

````java

import events.net.dot.properties.DotPropertiesListener;

public class MainClass implements DotPropertiesListener {

    @Override
    public void onPropertyChanged(String propertiesName, String oldValue, String newValue) {
        // Do whatever you want
    }

}

````


## Need more option ?

If you need more options not developed in this module you can open a issue.
Don't forget to leave a star on the repository.