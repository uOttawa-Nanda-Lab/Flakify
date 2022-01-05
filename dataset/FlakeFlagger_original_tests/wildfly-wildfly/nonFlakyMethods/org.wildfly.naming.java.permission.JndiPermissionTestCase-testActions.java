@Test public void testActions(){
  assertEquals(new JndiPermission("foo","*"),new JndiPermission("foo","all"));
  assertEquals(new JndiPermission("foo","*"),new JndiPermission("foo","lookup,bind,rebind,unbind,list,listBindings,createSubcontext,destroySubcontext,addNamingListener"));
  assertEquals(new JndiPermission("foo","*"),new JndiPermission("foo","unbind,list,listBindings,createSubcontext,destroySubcontext,addNamingListener,lookup,bind,rebind"));
  assertTrue(new JndiPermission("foo","*").implies(new JndiPermission("foo","lookup")));
  assertTrue(new JndiPermission("foo","").implies(new JndiPermission("foo","")));
  assertTrue(new JndiPermission("foo","*").implies(new JndiPermission("foo","")));
  assertFalse(new JndiPermission("foo","").implies(new JndiPermission("foo","bind")));
  assertTrue(new JndiPermission("foo","").withActions("bind").implies(new JndiPermission("foo","bind")));
  assertFalse(new JndiPermission("foo","unbind").withoutActions("unbind").implies(new JndiPermission("foo","unbind")));
}
