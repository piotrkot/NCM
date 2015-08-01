/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.github.piotrkot.cli.CommandLineArgs;
import com.google.common.base.Joiner;
import com.piokot.ncm.api.Command;
import lombok.extern.slf4j.Slf4j;

/**
 * Command for showing help.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class HELPCommand implements Command {

    @Override
    public boolean canRun(final CommandLineArgs cli) {
        return cli.getOptions().size() != 1
            || cli.findOption("h").iterator().hasNext();
    }

    @SuppressWarnings("PMD.SystemPrintln")
    @Override
    public void run() {
        // @checkstyle LineLength (3 lines)
        System.err.println(
            Joiner.on(System.lineSeparator()).join(
                "usage: java -jar target/ncm-task-1.0-SNAPSHOT.jar [-x | -c] < input",
                "",
                "Converts character stream with UTF_8 charset",
                "to sentences with sorted words.",
                "Output is put to the stdout.",
                "Options:",
                "-x output is in XML form",
                "-c output is in CSV form",
                "-h this help is displayed."
            )
        );
    }
}
