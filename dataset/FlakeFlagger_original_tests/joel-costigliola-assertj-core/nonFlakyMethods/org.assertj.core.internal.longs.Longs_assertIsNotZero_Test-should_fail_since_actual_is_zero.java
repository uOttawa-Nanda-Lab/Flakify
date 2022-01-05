@Test public void should_fail_since_actual_is_zero(){
  thrown.expectAssertionError("\nExpecting:\n <0L>\nnot to be equal to:\n <0L>\n");
  longs.assertIsNotZero(someInfo(),0l);
}
