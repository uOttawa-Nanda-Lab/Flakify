/** 
 * test for task inclusion/exclusion when additional filter is specified (like assignee), ordered.
 */
public void testQueryByAssigneeExcludeSubtasksOrdered() throws Exception {
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
  TaskQuery query=taskService.createTaskQuery().taskAssignee("gonzo").orderByTaskCreateTime().desc();
  assertEquals(7,query.count());
  assertEquals(7,query.list().size());
  assertEquals(sdf.parse("02/01/2009 01:01:01.000"),query.list().get(0).getCreateTime());
  query=taskService.createTaskQuery().taskAssignee("gonzo").excludeSubtasks().orderByTaskCreateTime().asc();
  assertEquals(2,query.count());
  assertEquals(2,query.list().size());
  assertEquals(sdf.parse("01/02/2008 02:02:02.000"),query.list().get(0).getCreateTime());
  assertEquals(sdf.parse("05/02/2008 02:02:02.000"),query.list().get(1).getCreateTime());
  query=taskService.createTaskQuery().taskAssignee(KERMIT).orderByTaskCreateTime().asc();
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  assertNull(query.singleResult());
  query=taskService.createTaskQuery().taskAssignee(KERMIT).excludeSubtasks().orderByTaskCreateTime().desc();
  assertEquals(0,query.count());
  assertEquals(0,query.list().size());
  assertNull(query.singleResult());
}
