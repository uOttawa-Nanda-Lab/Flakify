package com.github.jknack.handlebars.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.jknack.handlebars.AbstractTest;

public class CustomDelimiterTest extends AbstractTest {
  @Test public void tripleVariable() throws Exception{assertEquals("+-+{test}-+-",compile("{{=+-+ -+-=}}+-+{test}-+-").text());}
}
