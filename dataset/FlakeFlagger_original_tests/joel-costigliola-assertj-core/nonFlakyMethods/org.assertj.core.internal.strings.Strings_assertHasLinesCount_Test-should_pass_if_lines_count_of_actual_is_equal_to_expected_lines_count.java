@Test public void should_pass_if_lines_count_of_actual_is_equal_to_expected_lines_count(){
  strings.assertHasLineCount(someInfo(),"Begin" + LINE_SEPARATOR + "Middle\nEnd",3);
}
