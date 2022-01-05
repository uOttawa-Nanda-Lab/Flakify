@Test(expected=IllegalArgumentException.class) public void testWrongSize(){
  new UnsafeByteArrayOutputStream(-1);
}
