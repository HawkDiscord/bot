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

package cc.hawkbot.bot;

import de.foryasee.events.SubscribeEvent;
import org.apache.commons.cli.CommandLine;
import org.discordlist.cloud.node.NodeBuilder;
import org.discordlist.cloud.node.core.Node;
import org.discordlist.cloud.node.events.ReadyEvent;
import org.discordlist.cloud.node.util.logging.Logger;

public class HawkBot {

    private static final org.slf4j.Logger log = Logger.getLogger();

    private static HawkBot instance;
    private final long launchedAt;
    private final CommandLine cmd;
    private final boolean dev;
    private final Node node;

    public HawkBot(long launchedAt, boolean dev, CommandLine cmd, String[] args) {
        instance = this;

        this.launchedAt = launchedAt;
        this.cmd = cmd;
        this.dev = dev;

        var builder = new NodeBuilder(args);
        builder.getEventManager().register(this);
        node = builder.build();
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    private void initialize(ReadyEvent event) {
        log.info("[Hawk] Hawk got initialized in {} ms", System.currentTimeMillis() - launchedAt);
    }

    public static HawkBot getInstance() {
        return instance;
    }

    public long getLaunchedAt() {
        return launchedAt;
    }

    public CommandLine getCmd() {
        return cmd;
    }

    public boolean isDev() {
        return dev;
    }

    public Node getNode() {
        return node;
    }
}
