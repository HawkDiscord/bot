/*
 * Hawk - The next generation Discord moderation bot
 *
 * Copyright (C) 2019  Michael Rittmeister
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */

package cc.hawkbot.bot

import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.Options
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.core.config.ConfigurationSource
import org.apache.logging.log4j.core.config.Configurator
import java.util.*

fun main(args: Array<String>) {

    val launchedAt = System.currentTimeMillis()

    val options = Options()
    options.addOption("L", "log-level", true, "Sets the log-level")
    options.addOption("D", "debug", false, "Enables the debug mode")

    val cmd = DefaultParser().parse(options, args)


    Configurator.setRootLevel(Level.toLevel(cmd.getOptionValue("L"), Level.INFO))
    // Don't know if that is necessary but sometimes log4j is shit
    Configurator.initialize(ClassLoader.getSystemClassLoader(), ConfigurationSource(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("log4j2.xml"))))

    // Launch Hawk
    HawkBot(launchedAt, cmd.hasOption("D"), cmd, args)

}