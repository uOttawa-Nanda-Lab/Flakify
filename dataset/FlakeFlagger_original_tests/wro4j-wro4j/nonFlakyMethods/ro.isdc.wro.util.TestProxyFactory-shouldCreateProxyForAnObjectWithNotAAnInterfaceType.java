@Test public void shouldCreateProxyForAnObjectWithNotAAnInterfaceType(){
  final Object proxy=ProxyFactory.proxy(new ObjectFactory<Object>(){
    public Object create(){
      return new Object();
    }
  }
,Object.class);
  Validate.notNull(proxy);
  LOG.debug("Proxy: {}",proxy);
}
