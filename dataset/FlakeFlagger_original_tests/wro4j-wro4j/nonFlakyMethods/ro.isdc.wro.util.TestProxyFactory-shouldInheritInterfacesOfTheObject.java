@Test public void shouldInheritInterfacesOfTheObject(){
  final ResourceAuthorizationManager object=new DefaultResourceAuthorizationManager();
  final ResourceAuthorizationManager proxy=ProxyFactory.proxy(new ObjectFactory<ResourceAuthorizationManager>(){
    public ResourceAuthorizationManager create(){
      return object;
    }
  }
,ResourceAuthorizationManager.class);
  assertNotNull(proxy);
  assertNotSame(object,proxy);
  assertTrue(proxy instanceof MutableResourceAuthorizationManager);
}
