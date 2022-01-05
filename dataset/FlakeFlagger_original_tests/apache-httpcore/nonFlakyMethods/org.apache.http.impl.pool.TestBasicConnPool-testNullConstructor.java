@Test(expected=IllegalArgumentException.class) public void testNullConstructor() throws Exception {
  pool=new BasicConnPool(null,null);
}
