/*
 * Copyright 2011 France Télécom Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and limitations under the
 * License. This was inspired by TestFingerprintCreatorNamingStrategy.
 */

package ro.isdc.wro.model.resource.support.naming;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.resource.support.hash.CRC32HashStrategy;
import ro.isdc.wro.model.resource.support.hash.HashStrategy;
import ro.isdc.wro.util.WroTestUtils;
import ro.isdc.wro.util.WroUtil;


/**
 * Test class for {@link HashEncoderNamingStrategy}
 *
 * @author Alex Objelean
 * @created 15 Aug 2012
 */
public class TestHashEncoderNamingStrategy {
  private NamingStrategy namingStrategy;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    namingStrategy = new DefaultHashEncoderNamingStrategy() {
      @Override
      protected HashStrategy getHashStrategy() {
        return new CRC32HashStrategy();
      }
    };
    WroTestUtils.createInjector().inject(namingStrategy);
  }

  @Test(expected = NullPointerException.class)
  public void cannotAcceptNullResourceName()
      throws Exception {
    namingStrategy.rename(null, WroUtil.EMPTY_STREAM);
  }
}
