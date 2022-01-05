@Test public void testWriteObject() throws IOException, ClassNotFoundException {
  Image image=new Image("http://dubbo.io/logo.png","logo",300,480,Image.Size.SMALL);
  this.fastJsonObjectOutput.writeObject(image);
  this.flushToInput();
  Image readObjectForImage=fastJsonObjectInput.readObject(Image.class);
  assertThat(readObjectForImage,not(nullValue()));
  assertThat(readObjectForImage,is(image));
}
