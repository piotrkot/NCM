/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
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
        final XMLCommand xml = new XMLCommand(
            new ByteArrayInputStream("hello".getBytes(Charsets.UTF_8)),
            new FileOutputStream(File.createTempFile("temp", "txt"))
        );
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(xml.option()),
            new String[]{"-x"}
        );
        Assert.assertTrue("Cannot run XML", xml.canRun(cline));
    }

    /**
     * Can convert into XML.
     *
     * @throws Exception If fails
     */
    @Test
    public void convertIntoXML() throws Exception {
        final ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        final XMLCommand xml = new XMLCommand(
            new ByteArrayInputStream("content".getBytes(Charsets.UTF_8)),
            ostream
        );
        xml.run();
        Assert.assertEquals(
            "wrong XML output",
            Joiner.on(System.getProperty("line.separator")).join(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                "<text>",
                "<sentence>",
                "<word>content</word>",
                "</sentence>",
                "</text>",
                ""
            ),
            ostream.toString()
        );
    }
}
