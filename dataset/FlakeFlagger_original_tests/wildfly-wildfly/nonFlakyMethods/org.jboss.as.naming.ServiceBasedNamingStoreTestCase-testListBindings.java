@Test public void testListBindings() throws Exception {
  final Object value=new Object();
  bindObject(ServiceName.JBOSS.append("TestBean"),value);
  bindObject(ServiceName.JBOSS.append("foo","TestBean"),value);
  bindObject(ServiceName.JBOSS.append("foo","bar","TestBean"),value);
  bindObject(ServiceName.JBOSS.append("foo","bar","baz","TestBean"),value);
  store.add(ServiceName.JBOSS.append("foos","bar"));
  store.add(ServiceName.JBOSS.append("fo","bar"));
  store.add(ServiceName.JBOSS.append("foo","ba","baz"));
  store.add(ServiceName.JBOSS.append("foo","bart","baz"));
  store.add(ServiceName.JBOSS.append("foo","bar","ba"));
  store.add(ServiceName.JBOSS.append("foo","bar","bazt"));
  store.add(ServiceName.JBOSS.append("foo","bar","art"));
  store.add(ServiceName.JBOSS.append("other","one"));
  List<Binding> list=store.listBindings(new CompositeName(""));
  assertEquals(5,list.size());
  assertContains(list,"TestBean",Object.class);
  assertContains(list,"foo",NamingContext.class);
  assertContains(list,"fo",NamingContext.class);
  assertContains(list,"foos",NamingContext.class);
  assertContains(list,"other",NamingContext.class);
  list=store.listBindings(new CompositeName("foo"));
  assertEquals(4,list.size());
  assertContains(list,"TestBean",Object.class);
  assertContains(list,"ba",NamingContext.class);
  assertContains(list,"bart",NamingContext.class);
  assertContains(list,"bar",NamingContext.class);
  for (  Binding binding : list) {
    if (binding.getName().equals("bar")) {
      final Object bean=Context.class.cast(binding.getObject()).lookup("TestBean");
      assertNotNull(bean);
      assertEquals(value,bean);
    }
  }
}
