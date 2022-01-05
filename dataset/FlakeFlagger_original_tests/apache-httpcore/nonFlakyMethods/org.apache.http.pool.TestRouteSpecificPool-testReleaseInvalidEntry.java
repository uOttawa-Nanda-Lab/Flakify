@Test(expected=IllegalStateException.class) public void testReleaseInvalidEntry() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  HttpConnection conn=Mockito.mock(HttpConnection.class);
  LocalPoolEntry entry=new LocalPoolEntry(ROUTE,conn);
  pool.free(entry,true);
}
