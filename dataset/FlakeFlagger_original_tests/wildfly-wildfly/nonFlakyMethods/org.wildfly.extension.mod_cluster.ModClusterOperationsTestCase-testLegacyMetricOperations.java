@Test public void testLegacyMetricOperations() throws Exception {
  KernelServices services=this.buildKernelServices();
  ModelNode op=createLegacyRemoveMetricOperation("mem");
  ModelNode result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  op=createLegacyAddMetricOperation("mem");
  op.get("weight").set("5");
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),SUCCESS,result.get(OUTCOME).asString());
  op=createLegacyAddMetricOperation("invalid-metric");
  result=services.executeOperation(op);
  Assert.assertEquals(result.get(FAILURE_DESCRIPTION).asString(),FAILED,result.get(OUTCOME).asString());
}
