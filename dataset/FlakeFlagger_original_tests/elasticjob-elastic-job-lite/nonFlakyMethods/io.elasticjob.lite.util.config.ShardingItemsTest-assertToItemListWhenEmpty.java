@Test public void assertToItemListWhenEmpty(){
  assertThat(ShardingItems.toItemList(""),is(Collections.EMPTY_LIST));
}
