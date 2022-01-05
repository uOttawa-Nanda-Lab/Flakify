@Test public void testHandlesMode(){
  assertTrue(deploymentStrategy.handlesMode(SingleResourceAutoDeploymentStrategy.DEPLOYMENT_MODE));
  assertFalse(deploymentStrategy.handlesMode("other-mode"));
  assertFalse(deploymentStrategy.handlesMode(null));
}
