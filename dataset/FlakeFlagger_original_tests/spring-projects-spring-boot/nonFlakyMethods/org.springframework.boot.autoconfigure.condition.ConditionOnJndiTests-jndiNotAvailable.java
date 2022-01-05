@Test public void jndiNotAvailable(){
  this.condition.setJndiAvailable(false);
  ConditionOutcome outcome=this.condition.getMatchOutcome(null,mockMetaData());
  assertThat(outcome.isMatch(),equalTo(false));
}
