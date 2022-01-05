@Test public void should_match_if_condition_does_not_match(){
  condition.shouldMatch(false);
  assertTrue(not.matches("Yoda"));
}
