@Test public void should_filter_iterable_elements_with_property_in_given_values(){
  Iterable<Player> filteredPlayers=filter(players).with("team").in("OKC","Chicago Bulls").get();
  assertThat(filteredPlayers).containsOnly(rose,noah,durant);
  assertThat(players).hasSize(4);
  filteredPlayers=filter(players).with("name.last").in("Rose","Noah").get();
  assertThat(filteredPlayers).containsOnly(rose,noah);
  assertThat(players).hasSize(4);
}
