@Test public void testVerifySimpleWSSucceeds() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(SimpleWSImpl.class,null,deploymentReflectionIndex);
  sut.verify();
  assertFalse(sut.failed());
}
