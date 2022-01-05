@Test(expected=IllegalArgumentException.class) public void testNullConstructor2() throws Exception {
  pool=new BasicNIOConnPool(reactor,null);
}
