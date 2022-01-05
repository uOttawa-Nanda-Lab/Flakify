@Test public void should_create_filter_from_iterable(){
  Filters<Player> filter=filter(players);
  assertEquals(players,filter.get());
}
