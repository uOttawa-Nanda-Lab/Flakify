@Test(expected=IllegalArgumentException.class) public void testWeakCompareAndSet1() throws Exception {
  i1.weakCompareAndSet(i1.get(),-1);
}
