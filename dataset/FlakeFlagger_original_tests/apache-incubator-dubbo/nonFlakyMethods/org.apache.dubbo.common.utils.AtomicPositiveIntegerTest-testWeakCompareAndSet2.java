@Test public void testWeakCompareAndSet2() throws Exception {
  assertThat(i1.weakCompareAndSet(i1.get(),2),is(true));
  assertThat(i1.get(),is(2));
}
