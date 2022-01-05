@Test public void testHandlesMode(){
  assertTrue(deploymentStrategy.handlesMode(DefaultAutoDeploymentStrategy.DEPLOYMENT_MODE));
  assertFalse(deploymentStrategy.handlesMode("other-mode"));
  assertFalse(deploymentStrategy.handlesMode(null));
}
