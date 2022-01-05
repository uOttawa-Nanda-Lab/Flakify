@Test public void using_custom_comparator_test(){
  assertions.usingComparator(comparator);
  assertSame(getObjects(assertions).getComparator(),comparator);
  assertSame(getDates(assertions).getComparator(),comparator);
}
