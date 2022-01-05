@Test public void should_filter_iterable_elements_satisfying_condition(){
  Iterable<Player> potentialMvpPlayers=filter(players).being(potentialMvp).get();
  assertThat(potentialMvpPlayers).containsOnly(rose,james);
  assertThat(players).hasSize(4);
}
