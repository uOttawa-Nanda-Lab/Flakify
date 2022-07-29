package org.assertj.core.api.filter;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class Filter_with_property_equals_to_given_value_short_version_Test {
@Test public void should_filter_iterable_elements_with_property_equals_to_given_value(){
  Iterable<Player> bullsPlayers=filterIterable(players,"team","Chicago Bulls");
  assertThat(bullsPlayers).containsOnly(rose,noah);
  assertThat(players).hasSize(4);
  Iterable<Player> filteredPlayers=filter(players).with("name.last","James").get();
  assertThat(filteredPlayers).containsOnly(james);
  assertThat(players).hasSize(4);
}

}