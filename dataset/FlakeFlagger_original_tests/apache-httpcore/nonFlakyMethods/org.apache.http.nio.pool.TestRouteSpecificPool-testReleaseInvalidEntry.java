@Test(expected=IllegalStateException.class) public void testReleaseInvalidEntry() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  IOSession session=Mockito.mock(IOSession.class);
  LocalPoolEntry entry=new LocalPoolEntry("whatever",session);
  pool.free(entry,true);
}
