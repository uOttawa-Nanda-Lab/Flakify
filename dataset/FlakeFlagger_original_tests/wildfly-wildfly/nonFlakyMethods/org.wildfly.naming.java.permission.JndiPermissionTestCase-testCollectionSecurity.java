@Test public void testCollectionSecurity(){
  final PermissionCollection permissionCollection=new JndiPermission("","").newPermissionCollection();
  permissionCollection.add(new JndiPermission("foo/bar","unbind,rebind"));
  permissionCollection.setReadOnly();
  try {
    permissionCollection.add(new JndiPermission("fob/baz","unbind,rebind"));
    fail("Expected exception");
  }
 catch (  SecurityException ignored) {
  }
}
