@Test public void testIsModifiable() throws Exception {
  ServerConfigImpl sc=newServerConfigImpl();
  sc.create();
  assertTrue(sc.isModifiable());
  sc.incrementWSDeploymentCount();
  assertFalse(sc.isModifiable());
  sc.decrementWSDeploymentCount();
  assertTrue(sc.isModifiable());
  sc.incrementWSDeploymentCount();
  sc.incrementWSDeploymentCount();
  assertFalse(sc.isModifiable());
  sc.create();
  assertTrue(sc.isModifiable());
}
