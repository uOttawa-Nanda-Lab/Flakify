package org.assertj.core.api.filter;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class Filter_with_property_equals_to_given_value_Test {
@Test public void should_fail_if_property_to_filter_on_is_null(){
  try {
    filterIterable(players,null,6000L);
    fail("NullPointerException expected");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("The property name to filter on should not be null");
  }
}

}