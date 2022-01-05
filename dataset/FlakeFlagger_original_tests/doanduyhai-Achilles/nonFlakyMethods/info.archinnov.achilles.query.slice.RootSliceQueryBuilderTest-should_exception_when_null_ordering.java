@Test public void should_exception_when_null_ordering() throws Exception {
  exception.expect(AchillesException.class);
  exception.expectMessage("Ordering mode for slice query for entity 'entityClass' should not be null");
  builder.partitionComponentsInternal(10L).ordering(null);
}
