@Test public void should_pass_if_floats_are_equal_within_offset(){
  floats.assertEqual(someInfo(),new Float(6f),8f,offset(2f));
}
