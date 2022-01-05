@Test public void isDone(){
  DefaultFuture future=defaultFuture(3000);
  Assert.assertTrue("init future is finished!",!future.isDone());
  future.cancel();
  Assert.assertTrue("cancel a future failed!",future.isDone());
}
