@Test(expected=IllegalArgumentException.class) public void testNullConstructor() throws Exception {
  pool=new BasicNIOConnPool(null,new BasicHttpParams());
}
