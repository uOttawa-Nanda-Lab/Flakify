/** 
 * test for task inclusion/exclusion when additional filter is specified (like assignee), no order.
 */
public void testQueryByAssigneeExcludeSubtasks() throws Exception {
  TaskQuery query=taskService.createTaskQuery().taskAssignee("gonzo");
  assertEquals(7,query.count());
  assertEquals(7,query.list().size());
  query=taskService.createTaskQuery().taskAssignee("gonzo").excludeSubtasks();
  assertEquals(2,query.count());
  assertEquals(2,query.list().size());
  query=taskService.createTaskQuery().taskAssignee(KERMIT);
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  assertNull(query.singleResult());
  query=taskService.createTaskQuery().taskAssignee(KERMIT).excludeSubtasks();
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  assertNull(query.singleResult());
}
