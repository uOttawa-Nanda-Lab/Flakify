@Test public void testVerifySucceeds() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(GoodSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verify();
  assertFalse(sut.failed());
}
