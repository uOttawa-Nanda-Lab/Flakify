@Test public void testDuplicateConditionAndOutcomes(){
  ConditionAndOutcome outcome1=new ConditionAndOutcome(this.condition1,new ConditionOutcome(true,"Message 1"));
  ConditionAndOutcome outcome2=new ConditionAndOutcome(this.condition2,new ConditionOutcome(true,"Message 2"));
  ConditionAndOutcome outcome3=new ConditionAndOutcome(this.condition3,new ConditionOutcome(true,"Message 2"));
  assertThat(outcome1,equalTo(outcome1));
  assertThat(outcome1,not(equalTo(outcome2)));
  assertThat(outcome2,equalTo(outcome3));
  ConditionAndOutcomes outcomes=new ConditionAndOutcomes();
  outcomes.add(this.condition1,new ConditionOutcome(true,"Message 1"));
  outcomes.add(this.condition2,new ConditionOutcome(true,"Message 2"));
  outcomes.add(this.condition3,new ConditionOutcome(true,"Message 2"));
  assertThat(getNumberOfOutcomes(outcomes),equalTo(2));
}
