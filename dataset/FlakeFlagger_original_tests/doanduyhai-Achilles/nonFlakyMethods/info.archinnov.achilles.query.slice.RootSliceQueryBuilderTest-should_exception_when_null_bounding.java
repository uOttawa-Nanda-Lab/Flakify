@Test public void should_exception_when_null_bounding() throws Exception {
  exception.expect(AchillesException.class);
  exception.expectMessage("Bounding mode for slice query for entity 'entityClass' should not be null");
  builder.partitionComponentsInternal(10L).bounding(null);
}
