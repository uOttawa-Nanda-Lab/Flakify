@Test public void should_pass_if_doubles_are_equal_within_offset(){
  doubles.assertEqual(someInfo(),new Double(6d),new Double(8d),offset(2d));
}
