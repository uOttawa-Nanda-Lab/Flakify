@Test(expected=IllegalArgumentException.class) public void testReleaseInvalid() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  pool.free(null,true);
}
