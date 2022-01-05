@Test(expected=IllegalStateException.class) public void testNotNull2() throws Exception {
  notNull(null,new IllegalStateException("null object"));
}
