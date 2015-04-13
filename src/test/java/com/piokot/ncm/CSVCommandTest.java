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
 * Tests for CSV command triggering.
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
        final CSVCommand csv = new CSVCommand(
            new ByteArrayInputStream("hello".getBytes(Charsets.UTF_8)),
            new FileOutputStream(File.createTempFile("temp", "txt"))
        );
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(csv.option()),
            new String[]{"-c"}
        );
        Assert.assertTrue("Cannot run CSV", csv.canRun(cline));
    }

    /**
     * Can convert into CSV.
     *
     * @throws Exception If fails
     */
    @Test
    public void convertIntoCSV() throws Exception {
        final ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        final CSVCommand csv = new CSVCommand(
            new ByteArrayInputStream("content".getBytes(Charsets.UTF_8)),
            ostream
        );
        csv.run();
        Assert.assertEquals(
            "wrong CSV output",
            Joiner.on(System.getProperty("line.separator")).join(
                ", Word 1",
                "Sentence 1, content",
                ""
            ),
            ostream.toString()
        );
    }
}
