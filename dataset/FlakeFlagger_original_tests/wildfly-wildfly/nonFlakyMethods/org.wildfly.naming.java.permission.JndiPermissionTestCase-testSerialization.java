@Test public void testSerialization(){
  final JndiPermission jndiPermission=new JndiPermission("foo/blap/-","bind,lookup");
  assertEquals(jndiPermission,((SerializedJndiPermission)jndiPermission.writeReplace()).readResolve());
}
