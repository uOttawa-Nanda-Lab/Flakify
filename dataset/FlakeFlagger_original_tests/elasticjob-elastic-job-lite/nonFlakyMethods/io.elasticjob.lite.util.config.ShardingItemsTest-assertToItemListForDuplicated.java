@Test public void assertToItemListForDuplicated(){
  assertThat(ShardingItems.toItemList("0,1,2,2"),is(Arrays.asList(0,1,2)));
}
