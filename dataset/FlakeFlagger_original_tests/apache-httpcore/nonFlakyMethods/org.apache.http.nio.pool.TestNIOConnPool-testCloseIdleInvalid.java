@Test(expected=IllegalArgumentException.class) public void testCloseIdleInvalid() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  LocalSessionPool pool=new LocalSessionPool(ioreactor,2,2);
  pool.closeIdle(50,null);
}
