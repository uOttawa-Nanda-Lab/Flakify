@Test public void should_trigger_before_and_after_when_steps_is_both() throws Throwable {
  final StringBuilder witness=new StringBuilder();
  resource=new AchillesTestResource(Steps.BOTH,"table"){
    @Override protected void truncateTables(){
      witness.append("called");
    }
  }
;
  resource.before();
  assertThat(witness.toString()).isEqualTo("called");
  witness.delete(0,witness.length());
  resource.after();
  assertThat(witness.toString()).isEqualTo("called");
}
