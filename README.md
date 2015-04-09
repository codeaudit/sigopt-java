# Sigopt Java Bindings [![Build Status](https://travis-ci.org/sigopt/sigopt-java.svg?branch=master)](https://travis-ci.org/sigopt/sigopt-java)

You can sign up for a Sigopt experiment at https://sigopt.com.

Requirements
============

Java 1.6 and later.

Installation
============

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.sigopt</groupId>
  <artifactId>sigopt-java</artifactId>
  <version>1.27.1</version>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.sigopt:sigopt-java:1.27.1"
```

### Others

You'll need to manually install the following JARs:

* The Sigopt JAR from https://github.com/sigopt/sigopt-java/releases/latest
* [Google Gson](http://code.google.com/p/google-gson/) from <http://google-gson.googlecode.com/files/google-gson-2.2.4-release.zip>.

### [ProGuard](http://proguard.sourceforge.net/)

If you're planning on using ProGuard, make sure that you exclude the Sigopt bindings. You can do this by adding the following to your `proguard.cfg` file:

    -keep class com.sigopt.** { *; }

Usage
=====

SigoptExample.java

```java
import java.util.HashMap;
import java.util.Map;

import com.sigopt.Sigopt;
import com.sigopt.exception.SigoptException;
import com.sigopt.model.Charge;
import com.sigopt.net.RequestOptions;

public class SigoptExample {

    public static void main(String[] args) {
        RequestOptions requestOptions = (new RequestOptionsBuilder()).setApiKey("YOUR-SECRET-KEY").build();
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        chargeMap.put("amount", 100);
        chargeMap.put("currency", "usd");
        Map<String, Object> cardMap = new HashMap<String, Object>();
        cardMap.put("number", "4242424242424242");
        cardMap.put("exp_month", 12);
        cardMap.put("exp_year", 2020);
        chargeMap.put("card", cardMap);
        try {
            Charge charge = Charge.create(chargeMap, requestOptions));
            System.out.println(charge);
        } catch (SigoptException e) {
            e.printStackTrace();
        }
    }
}
```

See [SigoptTest.java](https://github.com/sigopt/sigopt-java/blob/master/src/test/java/com/sigopt/SigoptTest.java) for more examples.

Testing
=======

You must have Maven installed. To run the tests, simply run `mvn test`. You can run particular tests by passing `-D test=Class#method` -- for example, `-D test=SigoptTest#testPlanCreate`.
