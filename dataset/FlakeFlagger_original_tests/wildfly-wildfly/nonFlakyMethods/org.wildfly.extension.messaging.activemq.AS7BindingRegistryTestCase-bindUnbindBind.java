@Test public void bindUnbindBind() throws Exception {
  WildFlyBindingRegistry registry=new WildFlyBindingRegistry(container);
  Object obj=new Object();
  String name=UUID.randomUUID().toString();
  assertNull(getBinderServiceFor(name));
  assertTrue(registry.bind(name,obj));
  assertNotNull(getBinderServiceFor(name));
  registry.unbind(name);
  assertNull(getBinderServiceFor(name));
  assertTrue(registry.bind(name,obj));
  assertNotNull(getBinderServiceFor(name));
}
