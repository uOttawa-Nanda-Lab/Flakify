@Test public void should_fail_if_elements_to_filter_do_not_have_property_used_by_filter(){
  try {
    filterIterable(players,"nickname","dude");
    fail("IntrospectionError expected");
  }
 catch (  IntrospectionError e) {
    assertThat(e).hasMessage("No getter for property 'nickname' in org.assertj.core.test.Player");
  }
}
