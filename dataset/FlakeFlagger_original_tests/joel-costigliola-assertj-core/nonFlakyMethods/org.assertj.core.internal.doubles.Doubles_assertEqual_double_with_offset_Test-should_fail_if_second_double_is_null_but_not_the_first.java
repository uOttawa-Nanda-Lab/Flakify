@Test public void should_fail_if_second_double_is_null_but_not_the_first(){
  AssertionInfo info=someInfo();
  Offset<Double> offset=offset(1d);
  try {
    doubles.assertEqual(info,6d,null,offset);
    shouldHaveThrown(NullPointerException.class);
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("The given number should not be null");
  }
}
