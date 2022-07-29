package com.github.jknack.handlebars;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class MissingValueResolverTest {

  @Test(expected=HandlebarsException.class) public void throwExceptionOnMissingValue() throws IOException{final Object hash=new Object();Handlebars handlebars=new Handlebars().with(new MissingValueResolver(){@Override public String resolve(final Object context,final String var){throw new IllegalStateException("Missing variable: " + var);}});handlebars.compile("{{missingVar}}").apply(hash);}
}
