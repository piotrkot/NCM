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
 * Tests for XML command triggering.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class XMLCommandTest {
    /**
     * Can run if argument given.
     *
     * @throws Exception If fails
     */
    @Test
    public void runWithArg() throws Exception {
        Assert.assertTrue(
            "Cannot run XML",
            new XMLCommand(
                new InputStreamCache(
                    new ByteArrayInputStream("".getBytes(Charsets.UTF_8))
                ),
                new PrintWriter(System.out)
            ).canRun(new CommandLineArgs("-x"))
        );
    }

    /**
     * Can convert into XML.
     *
     * @throws Exception If fails
     */
    @Test
    public void convertIntoXML() throws Exception {
        final StringBuilder output = new StringBuilder(0);
        new XMLCommand(
            new InputStreamCache(
                new ByteArrayInputStream(
                    "content".getBytes(Charsets.UTF_8)
                )
            ),
            output
        ).run();
        Assert.assertEquals(
            "wrong XML output",
            Joiner.on(System.lineSeparator()).join(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                "<text>",
                "<sentence>",
                "<word>content</word>",
                "</sentence>",
                "</text>"
            ),
            output.toString()
        );
    }
}
