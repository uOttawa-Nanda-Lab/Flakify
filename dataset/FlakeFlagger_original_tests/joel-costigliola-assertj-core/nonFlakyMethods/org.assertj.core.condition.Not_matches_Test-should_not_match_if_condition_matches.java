@Test public void should_not_match_if_condition_matches(){
  condition.shouldMatch(true);
  assertFalse(not.matches("Yoda"));
}
