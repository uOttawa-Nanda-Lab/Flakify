@Test public void olderThanMessage() throws Exception {
  ConditionOutcome outcome=this.condition.getMatchOutcome(Range.OLDER_THAN,JavaVersion.SEVEN,JavaVersion.SIX);
  assertThat(outcome.getMessage(),equalTo("Required JVM version " + "older than 1.6 found 1.7"));
}
