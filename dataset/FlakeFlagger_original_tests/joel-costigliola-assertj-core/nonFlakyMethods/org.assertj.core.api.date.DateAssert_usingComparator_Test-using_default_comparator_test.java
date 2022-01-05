@Test public void using_default_comparator_test(){
  assertions.usingDefaultComparator();
  assertSame(getObjects(assertions),Objects.instance());
  assertSame(getDates(assertions),Dates.instance());
}
