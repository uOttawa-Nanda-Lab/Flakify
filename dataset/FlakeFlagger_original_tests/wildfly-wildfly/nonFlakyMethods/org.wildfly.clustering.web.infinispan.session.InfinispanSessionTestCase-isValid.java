@Test public void isValid(){
  when(this.metaData.isValid()).thenReturn(true);
  assertTrue(this.session.isValid());
  when(this.metaData.isValid()).thenReturn(false);
  assertFalse(this.session.isValid());
}
