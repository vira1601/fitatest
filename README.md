# fitatest

# Automated Android Testing
This automation testing using Appium + jUnit 5

# Preparation Set Up
Before you use this automation test you should clone this repo

1. Copy and paste this command on your terminal
   > git clone https://github.com/vira1601/fitatest.git
2. Install editor such: VSCode/ItelliJ
3. Install Appium Server
4. Install Android Studio
   > After that create new device emulator (i'm using Android 9)
5. Install JDK 17
6. Set JAVA_HOME & ANDROID_HOME
7. Install Maven from terminal

# How To Run Test
Follow instruction:
- Open folder automation
- Start Appium Server
- Open emulator Android Studio or real device
- Open terminal
- change your directory to automation folder, e.g in my local directory is: c:\Users\Documents\Vira\vira_shrine\shrine-appium-junit>
- Copy and paste this command on your terminal
  > mvn --version (check the version and make sure maven already installed)
  > mvn clean test -Dtest=shrine.java (command to run this automation test)

If preparation set up correct then you will see a console output
<details><summary>Click to see result</summary>
<img width="818" alt="shrine test passed" src="https://user-images.githubusercontent.com/43611621/204951965-ff080e51-8091-4947-8e47-2ab6b6483afb.png">
   (https://user-images.githubusercontent.com/43611621/204952748-0943579c-3f08-4e6c-973d-a4a45a31ed75.mp4)
</details>
<p>

# About Project
This automation using maven for requisite dependencies including:
- [Java Client for Appium](https://mvnrepository.com/artifact/io.appium/java-client)  to execute in a Java Virtual Machine (JVM) on a client device
- [Selenium Java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) as a automated testing framework used to validate web applications across different browsers and platforms
- [JUnit5 a.k.a Jupiter](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine) as a unit testing framework for Java programming language
- [Maven Surfire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/dependency-info.html) the plugin that runs the unit tests in a Maven project
