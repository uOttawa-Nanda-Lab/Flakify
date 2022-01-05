@Test public void should_fail_if_second_float_is_null_but_not_the_first(){
  AssertionInfo info=someInfo();
  Offset<Float> offset=offset(1f);
  try {
    floats.assertEqual(info,6f,null,offset);
    shouldHaveThrown(NullPointerException.class);
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("The given number should not be null");
  }
}
