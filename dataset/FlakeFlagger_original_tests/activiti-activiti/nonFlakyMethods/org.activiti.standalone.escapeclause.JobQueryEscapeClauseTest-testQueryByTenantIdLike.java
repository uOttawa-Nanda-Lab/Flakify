public void testQueryByTenantIdLike(){
  TimerJobQuery query=managementService.createTimerJobQuery().jobTenantIdLike("%\\%%");
  assertEquals("tenant%",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=managementService.createTimerJobQuery().jobTenantIdLike("%\\_%");
  assertEquals("tenant_",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  query=managementService.createTimerJobQuery().jobTenantIdLike("%test%");
  assertEquals("test",query.singleResult().getTenantId());
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
}
