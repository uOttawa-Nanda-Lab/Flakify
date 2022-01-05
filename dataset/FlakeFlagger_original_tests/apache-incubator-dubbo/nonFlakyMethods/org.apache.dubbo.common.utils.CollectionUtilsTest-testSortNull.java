@Test public void testSortNull() throws Exception {
  assertNull(CollectionUtils.sort(null));
  assertTrue(CollectionUtils.sort(new ArrayList<Integer>()).isEmpty());
}
