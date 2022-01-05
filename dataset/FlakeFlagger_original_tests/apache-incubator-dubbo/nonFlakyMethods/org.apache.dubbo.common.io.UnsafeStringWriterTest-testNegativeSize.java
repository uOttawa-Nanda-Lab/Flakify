@Test(expected=IllegalArgumentException.class) public void testNegativeSize(){
  new UnsafeStringWriter(-1);
}
