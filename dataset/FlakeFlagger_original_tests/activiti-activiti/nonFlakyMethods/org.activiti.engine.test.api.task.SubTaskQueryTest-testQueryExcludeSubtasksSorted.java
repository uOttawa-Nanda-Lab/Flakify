/** 
 * test for task inclusion/exclusion (no other filters, order by task assignee )
 */
public void testQueryExcludeSubtasksSorted() throws Exception {
  TaskQuery query=taskService.createTaskQuery().orderByTaskAssignee().asc();
  assertEquals(10,query.count());
  assertEquals(10,query.list().size());
  query=taskService.createTaskQuery().excludeSubtasks().orderByTaskAssignee().desc();
  assertEquals(3,query.count());
  assertEquals(3,query.list().size());
}
