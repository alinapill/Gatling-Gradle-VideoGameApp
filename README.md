Performance test suite
======================

Test suite used to check service behaviour & functionality on an application under high loads of traffic.

Course code for the Gatling Fundamentals Udemy course: https://www.udemy.com/gatling-fundamentals

### Tools used:
- Scala
- Gatling (for HTTP interaction)
- Maven

### Concepts illustrated in this suite of tests:
- Capture traffic through a Proxy
- Add pause time
- Check response codes
- Check Response body with JSON Path
- Extract data form response body (for correlation)
- Code reuse with methods
- Looping HTTP calls
- CSV feeder
- Basic custom feeder
- Complex custom feeder (using JSON Template file)
- Load simulation design (ramp up users per second, run simulation for a Fixed Period)
- Runtime parameters

### Engine
Run 'Engine'.
Select the simulation number you want to run.
Press _Enter_ when the text *Select run description (optional)* is displayed.

*example*
```Choose a simulation number:
[0] MyFirstTest
[1] simulations.AddPauseTime
[2] simulations.CheckResponseCode
2
Select run description (optional)

Simulation simulations.CheckResponseCode started...
```

### Run from command line
#### Run a single simulation
```mvn gatling:test -Dgatling.simulationClass=simulations.RuntimeParameters```

#### Run with parameters
```mvn gatling:test -Dgatling.simulationClass=simulations.RuntimeParameters -DUSERS=10 -DRAMP_DURION=5 -DDURATION=30```

If the parameters are not specified in the maven command, it will take the default values defined in the test.

#### Reporting
By running the tests on the local machine, Gatling reports are generated in (project root -> target folder -> simulationName -> index.html)


### Jenkins

#### Starting and stopping Jenkins
To stop the Jenkins server, open any terminal window and enter the command:

```brew services stop jenkins-lts```

To start the Jenkins server again, use the command:

```brew services start jenkins-lts```


### How to set a job in Jenkins
Add new item
At *Source Code Management* section:

- Specify Git repository:
```https://github.com/alinapill/Gatling-Gradle-VideoGameApp```

- Select *Poll SCM* checkbox

- Schedule: 
```* * * * *```
(every minute searches for repo updates)

- Invoke top-level maven targets - command:
```gatling:test -Dgatling.simulationClass=finalSimulation.VideoGameFullTest```

- Post-build Actions -> Track a load simulation -> select *Enable simulation tracking*

#### Build with parameters in Jenkins
Go to *Configure*.
Check _This project is parameterized_ and Save.
Go to _Build with parameters_ and enter the value you want.