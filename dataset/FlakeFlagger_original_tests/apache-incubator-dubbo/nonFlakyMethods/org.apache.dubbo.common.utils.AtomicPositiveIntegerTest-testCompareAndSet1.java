@Test(expected=IllegalArgumentException.class) public void testCompareAndSet1() throws Exception {
  i1.compareAndSet(i1.get(),-1);
}
