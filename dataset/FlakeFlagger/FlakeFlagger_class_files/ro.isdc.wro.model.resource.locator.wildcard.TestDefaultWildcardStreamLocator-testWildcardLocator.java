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

  @Test public void testWildcardLocator() throws IOException{locator=new DefaultWildcardStreamLocator(){@Override void triggerWildcardExpander(final Collection<File> allFiles,final WildcardContext wildcardContext) throws IOException{Assert.assertEquals(2,allFiles.size());}};final UriLocator uriLocator=new ClasspathUriLocator(){@Override public WildcardStreamLocator newWildcardStreamLocator(){return locator;}};uriLocator.locate("classpath:" + WroUtil.toPackageAsFolder(getClass()) + "/*.css");}
}
