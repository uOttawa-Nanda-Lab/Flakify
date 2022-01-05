@Test public void shouldInjectWroConfiguration() throws Exception {
  final Callable<?> inner=new Callable<Void>(){
    @Inject private WroConfiguration object;
    public Void call() throws Exception {
      Assert.assertNotNull(object);
      return null;
    }
  }
;
  victim.inject(inner);
  inner.call();
}
