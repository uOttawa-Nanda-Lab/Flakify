@Test public void should_exception_when_null_consistency_level() throws Exception {
  exception.expect(AchillesException.class);
  exception.expectMessage("ConsistencyLevel for slice query for entity 'entityClass' should not be null");
  builder.partitionComponentsInternal(10L).consistencyLevelInternal(null);
}
