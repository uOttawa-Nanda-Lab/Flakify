@Test public void should_pass_if_floats_are_equal(){
  floats.assertEqual(someInfo(),new Float(8f),8f,offset(1f));
}
