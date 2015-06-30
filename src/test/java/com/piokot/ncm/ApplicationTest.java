/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Application} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@lhsystems.com)
 * @version $Id$
 * @since 1.0
 */
public final class ApplicationTest {
    /**
     * Output stream.
     */
    private transient ByteArrayOutputStream out;

    /**
     * Set up for each test.
     */
    @Before
    public void setUp() {
        System.setIn(
            new ByteArrayInputStream("content".getBytes(Charsets.UTF_8))
        );
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Can output correct XML document.
     *
     * @throws Exception If it fails.
     */
    @Test
    public void outputXML() throws Exception {
        Application.main("-x");
        Assert.assertEquals(
            "XML content not same",
            Joiner.on(System.lineSeparator()).join(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                "<text>",
                "<sentence>",
                "<word>content</word>",
                "</sentence>",
                "</text>"
            ),
            this.out.toString()
        );
    }

    /**
     * Can output correct CSV document.
     *
     * @throws Exception If it fails.
     */
    @Test
    public void outputCSV() throws Exception {
        Application.main("-c");
        Assert.assertEquals(
            "CSV content not same",
            Joiner.on(System.lineSeparator()).join(
                ", Word 1",
                "Sentence 1, content"
            ),
            this.out.toString()
        );
    }

    /**
     * Can output correct Help message.
     *
     * @throws Exception If it fails.
     */
    @Test
    public void outputHelp() throws Exception {
        Application.main("-h");
        // @checkstyle LineLength (4 lines)
        Assert.assertEquals(
            "Help content not same",
            Joiner.on(System.lineSeparator()).join(
                "usage: java -jar target/ncm-task-1.0-SNAPSHOT.jar [-x] [-c] < input",
                "",
                "Converts character stream with UTF_8 charset",
                "to sentences with sorted words.",
                "Output is put to the stdout.",
                "Options:",
                "-x output is in XML form",
                "-c output is in CSV form",
                "-h this help is displayed.",
                "",
                ""
            ),
            this.out.toString()
        );
    }
}
