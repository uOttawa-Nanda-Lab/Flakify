/** 
 * test for task inclusion/exclusion when additional filter is specified (like assignee), ordered.
 */
public void testQueryByAssigneeExcludeSubtasksOrderedAndPaginated() throws Exception {
  SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SSS");
  TaskQuery query=taskService.createTaskQuery().taskAssignee("gonzo").orderByTaskCreateTime().asc();
  assertEquals(7,query.count());
  assertEquals(1,query.listPage(0,1).size());
  assertEquals(sdf.parse("01/02/2008 02:02:02.000"),query.listPage(0,1).get(0).getCreateTime());
  assertEquals(1,query.listPage(1,1).size());
  assertEquals(sdf.parse("05/02/2008 02:02:02.000"),query.listPage(1,1).get(0).getCreateTime());
  assertEquals(2,query.listPage(0,2).size());
  assertEquals(sdf.parse("01/02/2008 02:02:02.000"),query.listPage(0,2).get(0).getCreateTime());
  assertEquals(sdf.parse("05/02/2008 02:02:02.000"),query.listPage(0,2).get(1).getCreateTime());
  query=taskService.createTaskQuery().taskAssignee("gonzo").excludeSubtasks().orderByTaskCreateTime().desc();
  assertEquals(2,query.count());
  assertEquals(1,query.listPage(1,1).size());
  assertEquals(sdf.parse("01/02/2008 02:02:02.000"),query.listPage(1,1).get(0).getCreateTime());
  assertEquals(1,query.listPage(0,1).size());
  assertEquals(sdf.parse("05/02/2008 02:02:02.000"),query.listPage(0,1).get(0).getCreateTime());
  query=taskService.createTaskQuery().taskAssignee(KERMIT).orderByTaskCreateTime().asc();
  assertEquals(0,query.count());
  assertEquals(0,query.listPage(0,2).size());
  assertNull(query.singleResult());
  query=taskService.createTaskQuery().taskAssignee(KERMIT).excludeSubtasks().orderByTaskCreateTime().desc();
  assertEquals(0,query.count());
  assertEquals(0,query.listPage(0,2).size());
  assertNull(query.singleResult());
}
