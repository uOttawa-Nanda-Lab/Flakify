@Test public void testLogFailures() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(BrokenSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verify();
  sut.logFailures();
}
