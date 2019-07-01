package com.example.sessionsgenerator

import groovy.util.logging.Slf4j
import org.apache.commons.math3.distribution.PoissonDistribution
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import java.time.Duration
import java.time.Instant

import static java.util.Collections.shuffle

@SpringBootApplication
@Slf4j
class SessionsgeneratorApplication implements CommandLineRunner {
	static void main(String[] args) {
		SpringApplication.run(SessionsgeneratorApplication, args)
	}

	@Override
	void run(String... args) throws Exception {
		final def sessionCount = 10000
		final def arrivalsPerSecond = 5
		final def meanDuration = Duration.ofMinutes(4)
		final def durationVar = Duration.ofMinutes(2)

		final def userCount = meanDuration.toSeconds() / arrivalsPerSecond * 5 as int
		def users = (0..userCount).collect { it+1 }

		def random = new Random()

		def arrivals = new PoissonDistribution(1000/arrivalsPerSecond)

		def sqlFile = new File('build/data.sql')
		sqlFile.parentFile.mkdirs()
		sqlFile.createNewFile()
		sqlFile.text = ''

		def time = Instant.parse('2019-01-07T08:00:00Z')

		sqlFile.withWriter { writer ->
			sessionCount.times { count ->
				if ((count % userCount) == 0) {
					shuffle(users)
				}

				time = time.plusMillis(arrivals.sample())
				def sessionUser = users[random.nextInt(userCount)]
				def sessionEnd = time.plusMillis(meanDuration.toMillis() + random.nextInt(durationVar.toMillis() as int) - durationVar.toMillis()/2 as long)
				writer.writeLine"INSERT INTO sessions (userid, starttime, endtime) VALUES ($sessionUser, '${time.toString()}', '${sessionEnd.toString()}');"
			}
		}

		log.info("Wrote $sessionCount lines to ${sqlFile.absolutePath}")
	}
}
