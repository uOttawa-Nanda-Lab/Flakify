/** 
 * Test that the war which uses <security-domain flushOnSessionInvalidation="true"> in the jboss-web.xml does not have any jaas security domain cache entries after the web session has been invalidated.
 */
@Test public void testFlushOnSessionInvalidation() throws Exception {
  AssumeTestGroupUtil.assumeElytronProfileEnabled();
  log.trace("+++ testFlushOnSessionInvalidation");
  final ModelNode addr=new ModelNode();
  addr.add(ModelDescriptionConstants.SUBSYSTEM,"security");
  addr.add("security-domain","other");
  addr.protect();
  final ModelNode listCachedPrincipalsOperation=new ModelNode();
  listCachedPrincipalsOperation.get(ModelDescriptionConstants.OP_ADDR).set(addr);
  listCachedPrincipalsOperation.get(ModelDescriptionConstants.OP).set("list-cached-principals");
  doSecureGetWithLogin("restricted/SecuredServlet");
  final ModelNode node=ManagementOperations.executeOperation(managementClient.getControllerClient(),listCachedPrincipalsOperation);
  assertNotNull(node);
  final Set<String> cachedPrincipals=createSetOfPrincipals(node);
  assertTrue(USERNAME + " should be cached now.",cachedPrincipals.contains(USERNAME));
  doSecureGet("Logout");
  final ModelNode node2=ManagementOperations.executeOperation(managementClient.getControllerClient(),listCachedPrincipalsOperation);
  assertNotNull(node2);
  final Set<String> cachedPrincipals2=createSetOfPrincipals(node2);
  assertFalse(USERNAME + " should no longer be cached.",cachedPrincipals2.contains(USERNAME));
}
