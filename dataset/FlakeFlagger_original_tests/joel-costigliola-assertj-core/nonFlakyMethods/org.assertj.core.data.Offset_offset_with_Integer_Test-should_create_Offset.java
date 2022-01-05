@Test public void should_create_Offset(){
  Integer value=8;
  Offset<Integer> offset=Offset.offset(value);
  assertSame(value,offset.value);
}
