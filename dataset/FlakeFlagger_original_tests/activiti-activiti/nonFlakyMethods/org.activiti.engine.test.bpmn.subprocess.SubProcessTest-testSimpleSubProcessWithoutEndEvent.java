/** 
 * @see <a href="https://activiti.atlassian.net/browse/ACT-1072">https://activiti.atlassian.net/browse/ACT-1072</a>
 */
@Deployment public void testSimpleSubProcessWithoutEndEvent(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("testSimpleSubProcessWithoutEndEvent");
  assertProcessEnded(pi.getId());
}
