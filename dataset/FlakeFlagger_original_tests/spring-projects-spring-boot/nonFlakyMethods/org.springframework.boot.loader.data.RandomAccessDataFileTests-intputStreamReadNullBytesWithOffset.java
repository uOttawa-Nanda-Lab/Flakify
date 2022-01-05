@Test public void intputStreamReadNullBytesWithOffset() throws Exception {
  this.thrown.expect(NullPointerException.class);
  this.thrown.expectMessage("Bytes must not be null");
  this.inputStream.read(null,0,1);
}
