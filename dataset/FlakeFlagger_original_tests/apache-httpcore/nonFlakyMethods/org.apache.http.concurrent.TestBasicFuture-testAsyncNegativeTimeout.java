@Test(expected=TimeoutException.class) public void testAsyncNegativeTimeout() throws Exception {
  final BasicFuture<Object> future=new BasicFuture<Object>(null);
  future.get(-1,TimeUnit.MILLISECONDS);
}
