/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for Help command triggering.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class HELPCommandTest {
    /**
     * Can run if one argument given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithOneArg() throws Exception {
        final HELPCommand help = new HELPCommand();
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(help.option()),
            new String[]{"asd"}
        );
        Assert.assertTrue("not called with one arg", help.canRun(cline));
    }
    /**
     * Can run if no arg given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithNoArgs() throws Exception {
        final HELPCommand help = new HELPCommand();
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(help.option()),
            new String[]{""}
        );
        Assert.assertTrue("not called with no args", help.canRun(cline));
    }
    /**
     * Can run if help option given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithHelpOption() throws Exception {
        final HELPCommand help = new HELPCommand();
        //@checkstyle MultipleStringLiteralsCheck (3 lines)
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(help.option()),
            new String[]{"-h"}
        );
        Assert.assertTrue("not called with help option", help.canRun(cline));
    }
    /**
     * Can run if more options given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithMoreOptions() throws Exception {
        final HELPCommand help = new HELPCommand();
        final Option other = new Option("o", false, "other");
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(help.option()).addOption(other),
            new String[]{"-h", "-o"}
        );
        Assert.assertTrue("not called with more options", help.canRun(cline));
    }
    /**
     * Can run if option and argument given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithOptionAndArg() throws Exception {
        final HELPCommand help = new HELPCommand();
        final Option other = new Option("u", false, "another");
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(help.option()).addOption(other),
            new String[]{"-u", "arg"}
        );
        Assert.assertTrue("not called with option and arg", help.canRun(cline));
    }
}
