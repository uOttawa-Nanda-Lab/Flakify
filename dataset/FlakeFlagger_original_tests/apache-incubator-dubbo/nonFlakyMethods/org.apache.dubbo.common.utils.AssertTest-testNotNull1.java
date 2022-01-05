@Test(expected=IllegalArgumentException.class) public void testNotNull1() throws Exception {
  notNull(null,"null object");
}
