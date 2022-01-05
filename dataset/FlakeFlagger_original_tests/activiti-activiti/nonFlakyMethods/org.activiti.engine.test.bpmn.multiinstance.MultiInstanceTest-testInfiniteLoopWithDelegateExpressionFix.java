@Deployment public void testInfiniteLoopWithDelegateExpressionFix(){
  Map<Object,Object> originalBeans=processEngineConfiguration.getExpressionManager().getBeans();
  try {
    Map<Object,Object> newBeans=new HashMap<Object,Object>();
    newBeans.put("SampleTask",new TestSampleServiceTask());
    processEngineConfiguration.getExpressionManager().setBeans(newBeans);
    Map<String,Object> params=new HashMap<String,Object>();
    params.put("sampleValues",Arrays.asList("eins","zwei","drei"));
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("infiniteLoopTest",params);
    assertNotNull(processInstance);
  }
  finally {
    processEngineConfiguration.getExpressionManager().setBeans(originalBeans);
  }
}
