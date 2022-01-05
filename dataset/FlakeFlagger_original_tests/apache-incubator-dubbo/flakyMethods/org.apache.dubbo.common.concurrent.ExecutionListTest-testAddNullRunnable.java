@Test(expected=NullPointerException.class) public void testAddNullRunnable(){
  this.executionList.add(null,mock(Executor.class));
}
