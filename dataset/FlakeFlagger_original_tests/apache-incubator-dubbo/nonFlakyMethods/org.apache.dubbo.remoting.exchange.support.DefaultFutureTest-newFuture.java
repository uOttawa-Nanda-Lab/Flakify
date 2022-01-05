@Test public void newFuture(){
  DefaultFuture future=defaultFuture(3000);
  Assert.assertNotNull("new future return null",future);
}
