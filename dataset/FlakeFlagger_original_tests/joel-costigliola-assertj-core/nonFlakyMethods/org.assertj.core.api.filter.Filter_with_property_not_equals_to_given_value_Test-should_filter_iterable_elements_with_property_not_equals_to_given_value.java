@Test public void should_filter_iterable_elements_with_property_not_equals_to_given_value(){
  Iterable<Player> nonOKCPlayers=filter(players).with("team").notEqualsTo("OKC").get();
  assertThat(nonOKCPlayers).containsOnly(rose,noah,james);
  assertThat(players).hasSize(4);
  Iterable<Player> filteredPlayers=filter(players).with("name.last").notEqualsTo("Rose").get();
  assertThat(filteredPlayers).containsOnly(durant,noah,james);
  assertThat(players).hasSize(4);
}
