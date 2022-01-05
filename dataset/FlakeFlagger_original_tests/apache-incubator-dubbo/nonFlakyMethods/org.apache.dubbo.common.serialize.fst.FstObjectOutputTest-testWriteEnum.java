@Test public void testWriteEnum() throws IOException, ClassNotFoundException {
  this.fstObjectOutput.writeObject(AnimalEnum.cat);
  this.flushToInput();
  AnimalEnum animalEnum=(AnimalEnum)this.fstObjectInput.readObject();
  assertThat(animalEnum,is(AnimalEnum.cat));
}
