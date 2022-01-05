/** 
 * Asserts  {@link ExternalContextsNavigableSet#getParentExternalContext(ServiceName)}
 * @throws Exception
 */
@Test public void testGetParentContext() throws Exception {
  final ServiceName nameA=ServiceName.JBOSS.append("a");
  final ServiceName nameP=ServiceName.JBOSS.append("p");
  final ServiceName namePC=ServiceName.JBOSS.append("p","c");
  final ServiceName nameZ=ServiceName.JBOSS.append("z");
  ExternalContextsNavigableSet set=new ExternalContextsNavigableSet();
  set.addExternalContext(nameP);
  assertNull(set.getParentExternalContext(nameA));
  assertNull(set.getParentExternalContext(nameP));
  assertNotNull(set.getParentExternalContext(namePC));
  assertEquals(nameP,set.getParentExternalContext(namePC));
  assertNull(set.getParentExternalContext(nameZ));
}
