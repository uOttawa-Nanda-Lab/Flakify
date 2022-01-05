@Test public void should_trigger_only_before_when_steps_is_before() throws Throwable {
  final StringBuilder witness=new StringBuilder();
  resource=new AchillesTestResource(Steps.BEFORE_TEST,"table"){
    @Override protected void truncateTables(){
      witness.append("called");
    }
  }
;
  resource.before();
  assertThat(witness.toString()).isEqualTo("called");
  witness.delete(0,witness.length());
  resource.after();
  assertThat(witness.toString()).isEmpty();
}
