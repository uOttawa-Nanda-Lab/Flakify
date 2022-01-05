@Test public void assertGetMapWhenIsEmpty(){
  assertThat(new ShardingItemParameters("").getMap(),is(Collections.EMPTY_MAP));
}
