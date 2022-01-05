/** 
 * test for task inclusion/exclusion when additional filter is specified (like assignee), no order.
 */
public void testQueryByAssigneeExcludeSubtasksPaginated() throws Exception {
  TaskQuery query=taskService.createTaskQuery().taskAssignee("gonzo");
  assertEquals(7,query.count());
  assertEquals(2,query.listPage(0,2).size());
  query=taskService.createTaskQuery().taskAssignee("gonzo").excludeSubtasks();
  assertEquals(2,query.count());
  assertEquals(1,query.listPage(0,1).size());
  query=taskService.createTaskQuery().taskAssignee(KERMIT);
  assertEquals(0,query.count());
  assertEquals(0,query.listPage(0,2).size());
  assertNull(query.singleResult());
  query=taskService.createTaskQuery().taskAssignee(KERMIT).excludeSubtasks();
  assertEquals(0,query.count());
  assertEquals(0,query.listPage(0,2).size());
  assertNull(query.singleResult());
}
