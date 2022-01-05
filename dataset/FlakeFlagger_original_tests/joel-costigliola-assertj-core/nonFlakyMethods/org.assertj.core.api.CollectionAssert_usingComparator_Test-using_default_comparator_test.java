@Test public void using_default_comparator_test(){
  assertions.usingDefaultComparator();
  assertSame(assertions.objects,Objects.instance());
  assertSame(assertions.bytes,Bytes.instance());
}
