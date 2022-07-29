package com.github.jknack.handlebars;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class EmptyTemplateTest {

  @Test public void apply() throws IOException{assertEquals("",Template.EMPTY.apply((Object)null));assertEquals("",Template.EMPTY.apply((Context)null));}
}
