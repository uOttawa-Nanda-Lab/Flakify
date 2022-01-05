@Test public void testVerifyWebMethodSucceeds() throws NoSuchMethodException, SecurityException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(GoodSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  sut.verifyWebMethod(SampleWS.class.getMethod("performWork"));
  assertFalse(sut.failed());
  assertEquals(0,sut.verificationFailures.size());
}
