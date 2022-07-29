package com.github.jknack.handlebars;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.github.jknack.handlebars.Handlebars.SafeString;
import com.github.jknack.handlebars.custom.Blog;

public class ReflectiveHelperTest extends AbstractTest {

  @Test public void testHelperWithParamsAndOptions() throws IOException{shouldCompileTo("{{helperWithParamsAndOptions \"string\" true 4}}",$,"helperWithParamsAndOptions:string:true:4");}
}
