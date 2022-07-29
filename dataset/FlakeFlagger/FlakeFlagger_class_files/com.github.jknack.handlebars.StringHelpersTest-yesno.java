package com.github.jknack.handlebars;

import static com.github.jknack.handlebars.StringHelpers.abbreviate;
import static com.github.jknack.handlebars.StringHelpers.capitalize;
import static com.github.jknack.handlebars.StringHelpers.capitalizeFirst;
import static com.github.jknack.handlebars.StringHelpers.center;
import static com.github.jknack.handlebars.StringHelpers.cut;
import static com.github.jknack.handlebars.StringHelpers.defaultIfEmpty;
import static com.github.jknack.handlebars.StringHelpers.ljust;
import static com.github.jknack.handlebars.StringHelpers.lower;
import static com.github.jknack.handlebars.StringHelpers.rjust;
import static com.github.jknack.handlebars.StringHelpers.slugify;
import static com.github.jknack.handlebars.StringHelpers.stringFormat;
import static com.github.jknack.handlebars.StringHelpers.stripTags;
import static com.github.jknack.handlebars.StringHelpers.upper;
import static com.github.jknack.handlebars.StringHelpers.wordWrap;
import static com.github.jknack.handlebars.StringHelpers.yesno;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * Unit test for {@link StringHelpers}.
 *
 * @author edgar.espina
 * @since 0.2.2
 */
public class StringHelpersTest extends AbstractTest {

  @Test public void yesno() throws IOException{Options options=createMock(Options.class);expect(options.hash("yes","yes")).andReturn("yes");expect(options.hash("no","no")).andReturn("no");expect(options.hash("maybe","maybe")).andReturn("maybe");replay(options);assertEquals("yesno",yesno.name());assertEquals("yes",yesno.apply(true,options));assertEquals("no",yesno.apply(false,options));assertEquals("maybe",yesno.apply(null,options));verify(options);}

}
