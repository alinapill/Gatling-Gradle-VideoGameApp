package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class CheckResponseCode extends Simulation  {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  val scn = scenario("Video game DB - 3 calls")
    .exec(http("Get all  video games | 1st call")
      .get("videogames")
    .check(status.is(200)))
    .pause(5)

    .exec(http("Get specific game | 2nd call")
      .get("videogames/1")
    .check(status.in(200 to 210)))
    // random pause between 1 and 20 seconds
    .pause(1, 20)

    .exec(http("Get all video games | 3rd call")
      .get("videogames")
    .check(status.not(404), status.not(500)))
    .pause(3000.milliseconds)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}
