@Test public void should_not_match_Conditions_match(){
  condition.shouldMatch(true);
  assertFalse(doesNotHave.matches("Yoda"));
}
