@Test public void should_filter_iterable_elements_with_property_not_in_given_values(){
  Iterable<Player> filteredPlayers=filter(players).with("team").notIn("OKC","Miami Heat").get();
  assertThat(filteredPlayers).containsOnly(rose,noah);
  assertThat(players).hasSize(4);
  filteredPlayers=filter(players).with("name.last").notIn("Rose","Noah").get();
  assertThat(filteredPlayers).containsOnly(durant,james);
  assertThat(players).hasSize(4);
}
