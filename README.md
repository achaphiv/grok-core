grok
====

````
./mvnw -s settings.xml clean verify
````

Importing into intellij
=======================

Make sure to uncheck "Use maven 3 to import project"


Using in another project
========================

```
<repository>
  <id>bintray-grok</id>
  <url>http://dl.bintray.com/grok/maven</url>
  <snapshots>
    <enabled>false</enabled>
  </snapshots>
</repository>
```

```
<dependency>
  <groupId>com.grokclimbing.grok.core</groupId>
  <artifactId>grok-core-api</artifactId>
  <version>0.2.46</version>
</dependency>
```

[ ![Download](https://api.bintray.com/packages/grok/maven/grok-core/images/download.svg) ](https://bintray.com/grok/maven/grok-core/_latestVersion)

[![Build Status](https://travis-ci.org/achaphiv/grok-core.svg?branch=master)](https://travis-ci.org/achaphiv/grok-core)