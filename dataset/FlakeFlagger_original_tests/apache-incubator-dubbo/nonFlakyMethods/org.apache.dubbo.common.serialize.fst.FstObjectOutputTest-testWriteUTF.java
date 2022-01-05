@Test public void testWriteUTF() throws IOException {
  this.fstObjectOutput.writeUTF("I don’t know 知りません Не знаю");
  this.flushToInput();
  String result=this.fstObjectInput.readUTF();
  assertThat(result,is("I don’t know 知りません Не знаю"));
}
