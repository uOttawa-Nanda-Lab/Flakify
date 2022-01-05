@Test public void getBeans() throws ClassNotFoundException, IOException {
  MarshalledValue<Map<String,Object>,MarshallingContext> value=mock(MarshalledValue.class);
  Map<String,Object> beans=Collections.singletonMap("id",new Object());
  when(this.entry.getBeans()).thenReturn(value);
  when(value.get(this.context)).thenReturn(beans);
  Assert.assertSame(beans.keySet(),this.group.getBeans());
}
