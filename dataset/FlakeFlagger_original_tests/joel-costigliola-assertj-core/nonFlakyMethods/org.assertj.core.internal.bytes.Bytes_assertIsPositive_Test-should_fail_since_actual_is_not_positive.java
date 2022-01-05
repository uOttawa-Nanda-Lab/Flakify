@Test public void should_fail_since_actual_is_not_positive(){
  thrown.expectAssertionError("\nExpecting:\n <-1>\nto be greater than:\n <0>");
  bytes.assertIsPositive(someInfo(),(byte)-1);
}
