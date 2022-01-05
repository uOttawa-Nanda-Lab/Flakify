@Test public void should_fail_since_actual_is_not_positive(){
  thrown.expectAssertionError("\nExpecting:\n <-6L>\nto be greater than:\n <0L>");
  longs.assertIsPositive(someInfo(),-6l);
}
