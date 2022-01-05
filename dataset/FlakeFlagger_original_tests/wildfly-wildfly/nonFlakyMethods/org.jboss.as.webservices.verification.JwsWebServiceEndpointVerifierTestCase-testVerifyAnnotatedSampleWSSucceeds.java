@Test public void testVerifyAnnotatedSampleWSSucceeds() throws DeploymentUnitProcessingException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(AnnotatedSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verify();
  assertFalse(sut.failed());
}
