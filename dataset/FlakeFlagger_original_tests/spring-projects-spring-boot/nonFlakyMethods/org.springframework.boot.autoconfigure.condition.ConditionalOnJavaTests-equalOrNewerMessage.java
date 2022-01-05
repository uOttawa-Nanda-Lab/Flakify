@Test public void equalOrNewerMessage() throws Exception {
  ConditionOutcome outcome=this.condition.getMatchOutcome(Range.EQUAL_OR_NEWER,JavaVersion.SEVEN,JavaVersion.SIX);
  assertThat(outcome.getMessage(),equalTo("Required JVM version " + "1.6 or newer found 1.7"));
}
