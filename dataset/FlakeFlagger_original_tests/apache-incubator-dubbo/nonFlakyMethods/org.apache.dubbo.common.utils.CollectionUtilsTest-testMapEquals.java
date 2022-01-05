@Test public void testMapEquals() throws Exception {
  assertTrue(CollectionUtils.mapEquals(null,null));
  assertFalse(CollectionUtils.mapEquals(null,new HashMap<String,String>()));
  assertFalse(CollectionUtils.mapEquals(new HashMap<String,String>(),null));
  assertTrue(CollectionUtils.mapEquals(CollectionUtils.toStringMap("1","a","2","b"),CollectionUtils.toStringMap("1","a","2","b")));
  assertFalse(CollectionUtils.mapEquals(CollectionUtils.toStringMap("1","a"),CollectionUtils.toStringMap("1","a","2","b")));
}
