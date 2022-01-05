@Test public void should_create_filter_from_array(){
  Player[] playersArray=players.toArray(new Player[0]);
  Filters<Player> filter=filter(playersArray);
  assertEquals(players,filter.get());
}
