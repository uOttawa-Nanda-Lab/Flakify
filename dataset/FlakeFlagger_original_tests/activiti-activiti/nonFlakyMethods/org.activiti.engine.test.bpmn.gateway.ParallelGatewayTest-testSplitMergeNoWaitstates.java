/** 
 * Case where there is a parallel gateway that splits into 3 paths of execution, that are immediately joined, without any wait states in between. In the end, no executions should be in the database.
 */
@Deployment public void testSplitMergeNoWaitstates(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("forkJoinNoWaitStates");
  assertTrue(processInstance.isEnded());
}
