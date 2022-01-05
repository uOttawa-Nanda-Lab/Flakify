@Test public void testSortSimpleNameNull() throws Exception {
  assertNull(CollectionUtils.sortSimpleName(null));
  assertTrue(CollectionUtils.sortSimpleName(new ArrayList<String>()).isEmpty());
}
