/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.api;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.shouldHaveThrown;
import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.util.Dates.parseDatetime;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.assertj.core.api.iterable.Extractor;
import org.assertj.core.data.MapEntry;
import org.assertj.core.test.CartoonCharacter;
import org.assertj.core.test.Maps;
import org.assertj.core.test.Name;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link SoftAssertions}</code>.
 *
 * @author Brian Laframboise
 */
public class SoftAssertionsTest {

  private SoftAssertions softly;

  private CartoonCharacter homer;
  private CartoonCharacter fred;

  @Before
  public void setup() {
    softly = new SoftAssertions();

    CartoonCharacter bart = new CartoonCharacter("Bart Simpson");
    CartoonCharacter lisa = new CartoonCharacter("Lisa Simpson");
    CartoonCharacter maggie = new CartoonCharacter("Maggie Simpson");

    homer = new CartoonCharacter("Homer Simpson");
    homer.getChildren().add(bart);
    homer.getChildren().add(lisa);
    homer.getChildren().add(maggie);

    CartoonCharacter pebbles = new CartoonCharacter("Pebbles Flintstone");
    fred = new CartoonCharacter("Fred Flintstone");
    fred.getChildren().add(pebbles);
  }

  @Test public void should_pass_when_using_extracting_with_iterator(){Iterator<Name> names=asList(name("John","Doe"),name("Jane","Doe")).iterator();try (AutoCloseableSoftAssertions softly=new AutoCloseableSoftAssertions()){softly.assertThat(names).extracting("first").as("using extracting()").contains("John").contains("Jane");} }

  private static Name name(String first, String last) {
    return new Name(first, last);
  }

  private static ChildrenExtractor children() {
    return new ChildrenExtractor();
  }

  private static class ChildrenExtractor implements Extractor<CartoonCharacter, Collection<CartoonCharacter>> {
    @Override
    public Collection<CartoonCharacter> extract(CartoonCharacter input) {
      return input.getChildren();
    }
  }
}
