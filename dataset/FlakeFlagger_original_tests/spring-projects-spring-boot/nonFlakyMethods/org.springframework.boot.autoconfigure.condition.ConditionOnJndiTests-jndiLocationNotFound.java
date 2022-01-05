@Test public void jndiLocationNotFound(){
  ConditionOutcome outcome=this.condition.getMatchOutcome(null,mockMetaData("java:/a"));
  assertThat(outcome.isMatch(),equalTo(false));
}
