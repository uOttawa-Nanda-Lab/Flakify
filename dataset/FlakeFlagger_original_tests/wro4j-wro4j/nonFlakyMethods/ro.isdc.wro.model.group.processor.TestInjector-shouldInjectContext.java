@Test public void shouldInjectContext() throws Exception {
  final Callable<?> inner=new Callable<Void>(){
    @Inject private ReadOnlyContext object;
    public Void call() throws Exception {
      assertNotNull(object);
      return null;
    }
  }
;
  victim.inject(inner);
  inner.call();
}
