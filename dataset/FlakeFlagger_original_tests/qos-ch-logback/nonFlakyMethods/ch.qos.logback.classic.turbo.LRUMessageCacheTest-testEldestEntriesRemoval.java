@Test public void testEldestEntriesRemoval(){
  final LRUMessageCache cache=new LRUMessageCache(2);
  Assert.assertEquals(0,cache.getMessageCountAndThenIncrement("0"));
  Assert.assertEquals(1,cache.getMessageCountAndThenIncrement("0"));
  Assert.assertEquals(0,cache.getMessageCountAndThenIncrement("1"));
  Assert.assertEquals(1,cache.getMessageCountAndThenIncrement("1"));
  Assert.assertEquals(0,cache.getMessageCountAndThenIncrement("2"));
  Assert.assertEquals(0,cache.getMessageCountAndThenIncrement("0"));
  Assert.assertEquals(0,cache.getMessageCountAndThenIncrement("1"));
  Assert.assertEquals(0,cache.getMessageCountAndThenIncrement("2"));
}
