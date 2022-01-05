@Test public void should_create_Offset(){
  Double value=0.8d;
  Offset<Double> offset=Offset.offset(value);
  assertSame(value,offset.value);
}
