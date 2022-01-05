@Test public void testWriteUTF() throws IOException {
  myObjectOutput.writeUTF("Pace");
  myObjectOutput.writeUTF("和平");
  myObjectOutput.writeUTF(" Мир");
  flushToInput();
  assertThat(myObjectInput.readUTF(),CoreMatchers.is("Pace"));
  assertThat(myObjectInput.readUTF(),CoreMatchers.is("和平"));
  assertThat(myObjectInput.readUTF(),CoreMatchers.is(" Мир"));
}
