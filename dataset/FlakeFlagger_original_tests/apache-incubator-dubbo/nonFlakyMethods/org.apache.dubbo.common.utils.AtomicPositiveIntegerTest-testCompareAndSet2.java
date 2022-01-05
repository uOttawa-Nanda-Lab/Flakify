@Test public void testCompareAndSet2() throws Exception {
  assertThat(i1.compareAndSet(i1.get(),2),is(true));
  assertThat(i1.get(),is(2));
}
