/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.resource.support.naming;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.model.resource.support.hash.HashStrategy;
import ro.isdc.wro.util.WroUtil;


/**
 * Test class for {@link NamingStrategy} implementations.
 *
 * @author Alex Objelean
 * @created 15 Aug 2010
 */
public class TestNamingStrategy {
  private NamingStrategy namingStrategy;
  private static final String HASH = "HASH";

  @Before
  public void setUp() {
    namingStrategy = new DefaultHashEncoderNamingStrategy() {
      @Override
      protected HashStrategy getHashStrategy() {
        return new HashStrategy() {
          public String getHash(final InputStream inputStream)
              throws IOException {
            return HASH;
          }
        };
      };
    };
  }

  @Test(expected=NullPointerException.class) public void cannotAcceptNullOriginalName() throws Exception{namingStrategy.rename(null,WroUtil.EMPTY_STREAM);}
}
