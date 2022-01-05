@Test public void should_trigger_only_after_when_steps_is_after() throws Throwable {
  final StringBuilder witness=new StringBuilder();
  resource=new AchillesTestResource(Steps.AFTER_TEST,"table"){
    @Override protected void truncateTables(){
      witness.append("called");
    }
  }
;
  resource.after();
  assertThat(witness.toString()).isEqualTo("called");
  witness.delete(0,witness.length());
  resource.before();
  assertThat(witness.toString()).isEmpty();
}
