@Test public void recordConditionEvaluations() throws Exception {
  this.outcome1=new ConditionOutcome(false,"m1");
  this.outcome2=new ConditionOutcome(false,"m2");
  this.outcome3=new ConditionOutcome(false,"m3");
  this.report.recordConditionEvaluation("a",this.condition1,this.outcome1);
  this.report.recordConditionEvaluation("a",this.condition2,this.outcome2);
  this.report.recordConditionEvaluation("b",this.condition3,this.outcome3);
  Map<String,ConditionAndOutcomes> map=this.report.getConditionAndOutcomesBySource();
  assertThat(map.size(),equalTo(2));
  Iterator<ConditionAndOutcome> iterator=map.get("a").iterator();
  ConditionAndOutcome conditionAndOutcome=iterator.next();
  assertThat(conditionAndOutcome.getCondition(),equalTo(this.condition1));
  assertThat(conditionAndOutcome.getOutcome(),equalTo(this.outcome1));
  conditionAndOutcome=iterator.next();
  assertThat(conditionAndOutcome.getCondition(),equalTo(this.condition2));
  assertThat(conditionAndOutcome.getOutcome(),equalTo(this.outcome2));
  assertThat(iterator.hasNext(),equalTo(false));
  iterator=map.get("b").iterator();
  conditionAndOutcome=iterator.next();
  assertThat(conditionAndOutcome.getCondition(),equalTo(this.condition3));
  assertThat(conditionAndOutcome.getOutcome(),equalTo(this.outcome3));
  assertThat(iterator.hasNext(),equalTo(false));
}
