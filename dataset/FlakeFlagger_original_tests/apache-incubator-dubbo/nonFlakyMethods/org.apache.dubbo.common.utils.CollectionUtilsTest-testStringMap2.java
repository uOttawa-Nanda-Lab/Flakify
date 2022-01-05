@Test(expected=IllegalArgumentException.class) public void testStringMap2() throws Exception {
  toStringMap("key","value","odd");
}
