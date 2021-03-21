package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class CheckResponseAndExtract extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/app/")
    .header("Accept", "application/json")

  val scn = scenario("Check JSON path")
    .exec(http("Get specific game")
    .get("videogames/1")
    .check(jsonPath("$.name").is("Resident Evil 4")))

    .exec(http("Get all games")
    .get("videogames")
    .check(jsonPath("$[2].id").saveAs("gameId")))
    .exec { session => println(session); session }

    .exec(http("Get specific game with gameId previously saved")
    .get("videogames/${gameId}")
      .check(jsonPath("$.name").is("Gran Turismo 3"))
      .check(bodyString.saveAs("responseBody")))
    .exec { session => println(session("responseBody").as[String]); session}

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpConf)
}

