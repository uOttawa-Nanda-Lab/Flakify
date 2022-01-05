@Test public void should_validate_not_clustered_counter() throws Exception {
  ClusteredEntityWithCounter entity=new ClusteredEntityWithCounter();
  when(proxifier.<ClusteredEntityWithCounter>deriveBaseClass(entity)).thenReturn(ClusteredEntityWithCounter.class);
  when(entityMetaMap.get(ClusteredEntityWithCounter.class)).thenReturn(entityMeta);
  when(entityMeta.isClusteredCounter()).thenReturn(false);
  entityValidator.validateNotClusteredCounter(entity,entityMetaMap);
}
