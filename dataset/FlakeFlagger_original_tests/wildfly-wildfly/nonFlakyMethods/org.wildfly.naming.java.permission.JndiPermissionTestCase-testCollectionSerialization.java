@Test public void testCollectionSerialization(){
  final PermissionCollection permissionCollection=new JndiPermission("","").newPermissionCollection();
  permissionCollection.add(new JndiPermission("foo/bar","createSubcontext,rebind"));
  permissionCollection.add(new JndiPermission("foo","addNamingListener"));
  permissionCollection.add(new JndiPermission("-","lookup,rebind"));
  final PermissionCollection other=(PermissionCollection)((SerializedJndiPermissionCollection)((JndiPermissionCollection)permissionCollection).writeReplace()).readResolve();
  Enumeration<Permission> e;
  assertNotNull(e=other.elements());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("foo/bar","createSubcontext,rebind"),e.nextElement());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("foo","addNamingListener"),e.nextElement());
  assertTrue(e.hasMoreElements());
  assertEquals(new JndiPermission("-","lookup,rebind"),e.nextElement());
  assertFalse(e.hasMoreElements());
}
