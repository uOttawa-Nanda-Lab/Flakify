@Test public void shouldCreateProxyForAValidObject(){
  final ReadOnlyContext object=Context.standaloneContext();
  final ReadOnlyContext proxy=ProxyFactory.proxy(new ObjectFactory<ReadOnlyContext>(){
    public ReadOnlyContext create(){
      return object;
    }
  }
,ReadOnlyContext.class);
  assertNotNull(proxy);
  assertNotSame(object,proxy);
}
