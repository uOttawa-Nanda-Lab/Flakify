@Test public void testWriteUTF() throws IOException {
  this.fastJsonObjectOutput.writeUTF("Pace Hasîtî 和平 Мир");
  this.flushToInput();
  assertThat(fastJsonObjectInput.readUTF(),is("Pace Hasîtî 和平 Мир"));
}
