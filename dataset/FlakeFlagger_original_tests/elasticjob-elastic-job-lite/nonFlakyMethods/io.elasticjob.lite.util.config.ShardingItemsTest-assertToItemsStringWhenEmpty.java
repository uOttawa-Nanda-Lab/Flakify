@Test public void assertToItemsStringWhenEmpty(){
  assertThat(ShardingItems.toItemsString(Collections.<Integer>emptyList()),is(""));
}
