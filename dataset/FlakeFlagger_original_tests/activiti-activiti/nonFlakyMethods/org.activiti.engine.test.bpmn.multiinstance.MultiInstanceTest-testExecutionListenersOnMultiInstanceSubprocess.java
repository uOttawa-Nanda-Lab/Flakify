@Deployment public void testExecutionListenersOnMultiInstanceSubprocess(){
  resetTestCounts();
  Map<String,Object> variableMap=new HashMap<String,Object>();
  List<String> assignees=new ArrayList<String>();
  assignees.add("john");
  assignees.add("jane");
  assignees.add("matt");
  variableMap.put("assignees",assignees);
  runtimeService.startProcessInstanceByKey("MultiInstanceTest",variableMap);
  assertEquals(3,TestStartExecutionListener.countWithLoopCounter.get());
  assertEquals(3,TestEndExecutionListener.countWithLoopCounter.get());
  assertEquals(1,TestStartExecutionListener.countWithoutLoopCounter.get());
  assertEquals(1,TestEndExecutionListener.countWithoutLoopCounter.get());
}
