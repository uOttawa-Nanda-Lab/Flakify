@Test public void should_fail_since_actual_is_not_positive(){
  thrown.expectAssertionError("\nExpecting:\n <-6>\nto be greater than:\n <0>");
  shorts.assertIsPositive(someInfo(),(short)-6);
}
