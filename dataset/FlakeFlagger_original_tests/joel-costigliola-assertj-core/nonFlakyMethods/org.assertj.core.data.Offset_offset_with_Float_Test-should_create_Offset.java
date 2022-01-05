@Test public void should_create_Offset(){
  Float value=0.8f;
  Offset<Float> offset=Offset.offset(value);
  assertSame(value,offset.value);
}
