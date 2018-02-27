## README

**This project is a maven copy of my fyp which finished at Apr 2017. The purpose to build this project, is to make it easier to run the fyp project with minimal effort spent in configuring the runtime environment.**

**Below is some configuration steps for Mac Os X Users.**

***

### Mac Os X Configuration

1. Maven installation
   1. To make sure there is maven installed in your Mac, execute```mvn -version``` in Terminal. 
   2. If maven is not installed, there are two easy way to install:
      1. Homebrew user: execute ```brew install maven``` **(Recommended)** or 
      2. Follow the official guide http://maven.apache.org/install.html
2. Matlab
   1. Make sure you have install Matlab in you Mac.


***

### Project Configuration

1. Open the pom.xml in root directory of the project, modify the value of the property matlab.control.path.
```
### Example: <matlab.control.path>/Users/zlatan/.m2/repository/matlab/matlab-control/1.0/matlabcontrol-4.1.0.jar</matlab.control.path>
<matlab.control.path>${path of your matlabcontrol jar file}</matlab.control.path>
```

***

### Test the project

1. In Terminal, go to the root directory of this project. Then execute ```mvn clean test```. If everything goes well, it means that the configuration works.

*@Author: Zlatan W HO*

