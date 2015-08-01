/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.github.piotrkot.cli.CommandLineArgs;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link CSVCommand} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class CSVCommandTest {
    /**
     * Can run if argument given.
     *
     * @throws Exception If fails
     */
    @Test
    public void runWithArg() throws Exception {
        Assert.assertTrue(
            "Cannot run CSV",
            new CSVCommand(
                new InputStreamCache(
                    new ByteArrayInputStream("".getBytes(Charsets.UTF_8))
                ),
                new PrintWriter(System.out)
            ).canRun(new CommandLineArgs("-c"))
        );
    }

    /**
     * Can form CSV header.
     *
     * @throws Exception If fails.
     */
    @Test
    public void formCSVHeader() throws Exception {
        Assert.assertEquals(
            "invalid header",
            ", Word 1, Word 2, Word 3",
            new CSVCommand(
                new InputStreamCache(
                    new ByteArrayInputStream(
                        "hello world. Hi my friend".getBytes(Charsets.UTF_8)
                    )
                ),
                new PrintWriter(System.out)
            ).header()
        );
    }

    /**
     * Can convert into CSV.
     *
     * @throws Exception If fails
     */
    @Test
    public void convertIntoCSV() throws Exception {
        final StringBuilder output = new StringBuilder(0);
        new CSVCommand(
            new InputStreamCache(
                new ByteArrayInputStream(
                    "First? No, second".getBytes(Charsets.UTF_8)
                )
            ),
            output
        ).run();
        Assert.assertEquals(
            "wrong CSV output",
            Joiner.on(System.lineSeparator()).join(
                ", Word 1, Word 2",
                "Sentence 1, First",
                "Sentence 2, No, second"
            ),
            output.toString()
        );
    }
}
