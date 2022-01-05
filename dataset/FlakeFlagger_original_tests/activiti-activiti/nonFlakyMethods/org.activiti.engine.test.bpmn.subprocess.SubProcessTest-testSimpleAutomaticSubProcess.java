/** 
 * Same test case as before, but now with all automatic steps
 */
@Deployment public void testSimpleAutomaticSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcessAutomatic");
  assertTrue(pi.isEnded());
  assertProcessEnded(pi.getId());
}
