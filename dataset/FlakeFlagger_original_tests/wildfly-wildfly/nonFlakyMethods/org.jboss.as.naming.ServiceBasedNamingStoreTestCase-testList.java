@Test public void testList() throws Exception {
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
  List<NameClassPair> list=store.list(new CompositeName(""));
  assertEquals(5,list.size());
  assertContains(list,"TestBean",Object.class);
  assertContains(list,"foo",Context.class);
  assertContains(list,"fo",Context.class);
  assertContains(list,"foos",Context.class);
  assertContains(list,"other",Context.class);
  list=store.list(new CompositeName("foo"));
  assertEquals(4,list.size());
  assertContains(list,"TestBean",Object.class);
  assertContains(list,"ba",Context.class);
  assertContains(list,"bart",Context.class);
  assertContains(list,"bar",Context.class);
}
