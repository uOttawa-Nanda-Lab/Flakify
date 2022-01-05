/** 
 * @see <a href="https://activiti.atlassian.net/browse/ACT-1072">https://activiti.atlassian.net/browse/ACT-1072</a>
 */
@Deployment public void testNestedSubProcessesWithoutEndEvents(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("testNestedSubProcessesWithoutEndEvents");
  assertProcessEnded(pi.getId());
}
