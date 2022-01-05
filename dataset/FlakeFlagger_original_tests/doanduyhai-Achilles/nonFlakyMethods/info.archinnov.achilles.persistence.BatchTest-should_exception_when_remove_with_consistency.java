@Test public void should_exception_when_remove_with_consistency() throws Exception {
  exception.expect(AchillesException.class);
  exception.expectMessage("Runtime custom Consistency Level cannot be set for batch mode. Please set the Consistency Levels at batch start with 'startBatch(consistencyLevel)'");
  manager.remove(new CompleteBean(),OptionsBuilder.withConsistency(ONE));
}
