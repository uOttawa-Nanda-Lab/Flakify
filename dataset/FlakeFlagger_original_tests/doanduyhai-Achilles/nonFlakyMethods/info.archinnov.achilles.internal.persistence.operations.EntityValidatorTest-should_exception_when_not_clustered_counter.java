@Test public void should_exception_when_not_clustered_counter() throws Exception {
  ClusteredEntityWithCounter entity=new ClusteredEntityWithCounter();
  when(proxifier.<ClusteredEntityWithCounter>deriveBaseClass(entity)).thenReturn(ClusteredEntityWithCounter.class);
  when(entityMetaMap.get(ClusteredEntityWithCounter.class)).thenReturn(entityMeta);
  when(entityMeta.isClusteredCounter()).thenReturn(true);
  exception.expect(AchillesException.class);
  exception.expectMessage("The entity '" + entity + "' is a clustered counter and does not support insert/update with TTL");
  entityValidator.validateNotClusteredCounter(entity,entityMetaMap);
}
