@Test public void using_custom_comparator_test(){
  assertions.usingComparator(comparator);
  assertSame(assertions.objects.getComparator(),comparator);
  assertSame(assertions.bytes.getComparator(),comparator);
}
