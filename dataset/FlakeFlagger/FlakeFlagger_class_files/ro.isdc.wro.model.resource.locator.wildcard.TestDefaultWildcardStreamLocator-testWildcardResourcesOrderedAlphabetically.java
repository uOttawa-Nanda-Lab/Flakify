/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.resource.locator.wildcard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.util.WroUtil;

/**
 * @author Alex Objelean
 */
public class TestDefaultWildcardStreamLocator {
  private WildcardStreamLocator locator;

  @Before
  public void setUp() {
    locator = new DefaultWildcardStreamLocator();
  }

  @Test public void testWildcardResourcesOrderedAlphabetically() throws IOException{locator=new DefaultWildcardStreamLocator(){@Override void triggerWildcardExpander(final Collection<File> allFiles,final WildcardContext wildcardContext) throws IOException{final Collection<String> filenameList=new ArrayList<String>();for (final File file:allFiles){filenameList.add(file.getName());}Assert.assertEquals(Arrays.toString(new String[]{"tools.expose-1.0.5.js","tools.overlay-1.1.2.js","tools.overlay.apple-1.0.1.js","tools.overlay.gallery-1.0.0.js"}),Arrays.toString(filenameList.toArray()));}};final UriLocator uriLocator=new ClasspathUriLocator(){@Override public WildcardStreamLocator newWildcardStreamLocator(){return locator;}};uriLocator.locate("classpath:" + WroUtil.toPackageAsFolder(getClass()) + "/*.js");}
}
