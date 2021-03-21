import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends Simulation {

  // http conf
  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept","application/json")
    .proxy(Proxy("localhost", 8888))

  // scenario definition
  val scn = scenario("MyFirstTest")
    .exec(http("Get All  Games")
    .get("videogames"))

  // load scenario
  setUp(
    scn.inject(
      atOnceUsers(1)
    ).protocols(httpConf)
  )
}
