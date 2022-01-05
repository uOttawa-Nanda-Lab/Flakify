@Test public void should_fail_if_elements_to_filter_do_not_have_property_used_by_filter(){
  try {
    filter(players).with("nickname").in("dude","al");
    fail("IntrospectionError expected");
  }
 catch (  IntrospectionError e) {
    assertEquals("No getter for property 'nickname' in org.assertj.core.test.Player",e.getMessage());
  }
}
