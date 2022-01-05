@Test(expected=IllegalArgumentException.class) public void testRemoveInvalid() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  pool.remove(null);
}
