@Test public void should_fail_if_actual_is_null(){
  thrown.expectAssertionError(actualIsNull());
  final byte[] actual=null;
  assertThat(actual).hasSameSizeAs(new byte[]{2,3});
}
