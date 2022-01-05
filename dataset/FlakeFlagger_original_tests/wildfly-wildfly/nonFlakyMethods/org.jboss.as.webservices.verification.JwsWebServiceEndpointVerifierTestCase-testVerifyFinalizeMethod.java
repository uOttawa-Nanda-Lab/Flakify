@Test public void testVerifyFinalizeMethod(){
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(BrokenSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verifyFinalizeMethod();
  assertTrue(sut.failed());
  assertEquals(1,sut.verificationFailures.size());
  assertTrue(sut.verificationFailures.get(0) instanceof ImplementationHasFinalize);
}
