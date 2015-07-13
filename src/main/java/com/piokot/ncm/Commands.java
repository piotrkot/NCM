/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.piokot.ncm.api.Command;
import java.util.Arrays;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Commands for the Application.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class Commands {
    /**
     * Commands that can handle command line arguments.
     */
    private final transient Iterable<Command> cmnds;
    /**
     * Class constructor.
     *
     * @param commands Commands handling arguments.
     */
    public Commands(final Command... commands) {
        this.cmnds = Arrays.asList(commands);
    }
    /**
     * Routes commands for the command line arguments.
     * <p/>
     * All commands that can run for the given arguments get run.
     *
     * @param arguments Command line arguments.
     */
    @SneakyThrows
    public void dispatch(final String... arguments) {
        log.info("Read args: {}", arguments);
        final Options optns = new Options();
        for (final Command command : this.cmnds) {
            optns.addOption(command.option());
        }
        final CommandLine cline = new BasicParser().parse(optns, arguments);
        log.info("Command line args: {}", cline.getArgList());
        for (final Command command : this.cmnds) {
            if (command.canRun(cline)) {
                command.run();
            }
        }
    }
}
