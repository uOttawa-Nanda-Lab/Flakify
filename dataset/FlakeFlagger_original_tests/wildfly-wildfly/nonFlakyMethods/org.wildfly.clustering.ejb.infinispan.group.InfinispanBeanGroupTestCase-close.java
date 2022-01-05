@Test public void close() throws ClassNotFoundException, IOException {
  MarshalledValue<Map<String,Object>,MarshallingContext> value=mock(MarshalledValue.class);
  when(this.entry.getBeans()).thenReturn(value);
  when(value.get(this.context)).thenReturn(Collections.<String,Object>emptyMap());
  this.group.close();
  verify(this.remover).remove(this.id);
  verify(this.mutator,never()).mutate();
  reset(this.remover,this.mutator);
  when(value.get(this.context)).thenReturn(Collections.singletonMap("id",new Object()));
  this.group.close();
  verify(this.mutator).mutate();
  verify(this.remover,never()).remove(this.id);
}
