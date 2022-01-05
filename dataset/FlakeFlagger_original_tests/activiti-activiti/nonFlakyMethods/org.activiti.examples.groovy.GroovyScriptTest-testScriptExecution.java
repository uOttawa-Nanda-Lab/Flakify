@Deployment public void testScriptExecution(){
  int[] inputArray=new int[]{1,2,3,4,5};
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("scriptExecution",CollectionUtil.singletonMap("inputArray",inputArray));
  Integer result=(Integer)runtimeService.getVariable(pi.getId(),"sum");
  assertEquals(15,result.intValue());
}
