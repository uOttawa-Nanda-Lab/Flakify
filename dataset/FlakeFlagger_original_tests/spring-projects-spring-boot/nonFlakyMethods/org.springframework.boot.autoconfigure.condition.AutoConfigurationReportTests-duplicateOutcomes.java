@Test public void duplicateOutcomes(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DuplicateConfig.class);
  ConditionEvaluationReport report=ConditionEvaluationReport.get(context.getBeanFactory());
  String autoconfigKey=MultipartAutoConfiguration.class.getName();
  ConditionAndOutcomes outcomes=report.getConditionAndOutcomesBySource().get(autoconfigKey);
  assertThat(outcomes,not(nullValue()));
  assertThat(getNumberOfOutcomes(outcomes),equalTo(2));
  List<String> messages=new ArrayList<String>();
  for (  ConditionAndOutcome outcome : outcomes) {
    messages.add(outcome.getOutcome().getMessage());
    System.out.println(outcome.getOutcome().getMessage());
  }
  Matcher<String> onClassMessage=containsString("@ConditionalOnClass " + "classes found: javax.servlet.Servlet,org.springframework.web.multipart.support.StandardServletMultipartResolver");
  assertThat(messages,hasItem(onClassMessage));
  context.close();
}
