@Test public void testFindEndpointImplMethodMatching() throws NoSuchMethodException, SecurityException {
  JwsWebServiceEndpointVerifier sut=new JwsWebServiceEndpointVerifier(GoodSampleWSImpl.class,SampleWS.class,deploymentReflectionIndex);
  Method endpointInterfaceMethod=SampleWS.class.getMethod("performWork");
  Method seiMethod=sut.findEndpointImplMethodMatching(endpointInterfaceMethod);
  assertNotNull(seiMethod);
  assertEquals("performWork",seiMethod.getName());
}
