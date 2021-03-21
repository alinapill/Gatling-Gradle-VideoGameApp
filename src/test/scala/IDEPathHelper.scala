import java.nio.file.{Paths}

object IDEPathHelper {

	private val projectRootDir = Paths.get(getClass.getClassLoader.getResource("gatling.conf").toURI).getParent.getParent.getParent

	private val mavenTargetDirectory = projectRootDir.resolve("target")

	private val mavenSourcesDirectory = projectRootDir.resolve("src/test/scala")

	val mavenResourcesDirectory = projectRootDir.resolve("src/test/resources")

	val mavenBinariesDirectory = mavenTargetDirectory.resolve("test-classes")

	val resourcesDirectory = mavenResourcesDirectory

	val recorderSimulationsDirectory = mavenSourcesDirectory

	val resultsDirectory = mavenTargetDirectory.resolve("gatling")

	val recorderConfigFile = mavenResourcesDirectory.resolve("recorder.conf")
}

