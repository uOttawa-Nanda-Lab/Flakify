public void testQueryByTenantIdLike(){
  runtimeService.startProcessInstanceByKeyAndTenantId("noopProcess","One%");
  runtimeService.startProcessInstanceByKeyAndTenantId("noopProcess","Two_");
  HistoricActivityInstanceQuery query=historyService.createHistoricActivityInstanceQuery().activityId("noop").activityTenantIdLike("%\\%%");
  assertEquals("One%",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=historyService.createHistoricActivityInstanceQuery().activityId("noop").activityTenantIdLike("%\\_%");
  assertEquals("Two_",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
