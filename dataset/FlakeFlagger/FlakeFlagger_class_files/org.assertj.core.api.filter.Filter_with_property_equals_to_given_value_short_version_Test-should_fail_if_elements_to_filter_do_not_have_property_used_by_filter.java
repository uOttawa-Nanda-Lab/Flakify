package org.assertj.core.api.filter;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class Filter_with_property_equals_to_given_value_short_version_Test {
@Test public void should_fail_if_elements_to_filter_do_not_have_property_used_by_filter(){
  try {
    filterIterable(players,"nickname","dude");
    fail("IntrospectionError expected");
  }
 catch (  IntrospectionError e) {
    assertThat(e).hasMessage("No getter for property 'nickname' in org.assertj.core.test.Player");
  }
}

}