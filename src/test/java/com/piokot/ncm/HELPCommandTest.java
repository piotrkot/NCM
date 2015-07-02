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
     * Can run if one argument given and an other option.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithOneArgOneOption() throws Exception {
        final HELPCommand help = new HELPCommand();
        final Option ocase = new Option("o", false, "o case");
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(ocase), new String[]{"asd", "-o"}
        );
        Assert.assertTrue(
            "not called with one arg and option",
            help.canRun(cline)
        );
    }
    /**
     * Can run if for two options given and no args.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithTwoOptionsNoArgs() throws Exception {
        final HELPCommand help = new HELPCommand();
        final Option ccase = new Option("c", false, "c case");
        final Option xcase = new Option("x", false, "x case");
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(ccase).addOption(xcase),
            new String[]{"-c", "-x"}
        );
        Assert.assertTrue(
            "not called with two options and no args",
            help.canRun(cline)
        );
    }
    /**
     * Can run if help option given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithHelpOption() throws Exception {
        final HELPCommand help = new HELPCommand();
        final CommandLine cline = new BasicParser().parse(
            new Options().addOption(help.option()), new String[]{"-h"}
        );
        Assert.assertTrue("not called with help option", help.canRun(cline));
    }
    /**
     * Can run if no options nor arguments given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithNoOptionsNorArgs() throws Exception {
        final HELPCommand help = new HELPCommand();
        final CommandLine cline = new BasicParser().parse(
            new Options(), new String[]{""}
        );
        Assert.assertTrue(
            "not called with no options nor arguments",
            help.canRun(cline)
        );
    }
}
