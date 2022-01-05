public void testOrderBy(){
  ModelQuery query=repositoryService.createModelQuery();
  assertEquals(1,query.orderByCreateTime().asc().count());
  assertEquals(1,query.orderByLastUpdateTime().asc().count());
  assertEquals(1,query.orderByModelCategory().asc().count());
  assertEquals(1,query.orderByModelId().desc().count());
  assertEquals(1,query.orderByModelName().desc().count());
  assertEquals(1,query.orderByModelVersion().desc().count());
  assertEquals(1,query.orderByModelKey().desc().count());
}
