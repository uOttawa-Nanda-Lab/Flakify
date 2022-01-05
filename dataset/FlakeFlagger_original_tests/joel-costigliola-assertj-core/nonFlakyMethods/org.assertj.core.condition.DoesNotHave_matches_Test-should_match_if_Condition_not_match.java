@Test public void should_match_if_Condition_not_match(){
  condition.shouldMatch(false);
  assertTrue(doesNotHave.matches("Yoda"));
}
