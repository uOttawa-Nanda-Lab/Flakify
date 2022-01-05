@Test public void createValue(){
  SessionCreationMetaData creationMetaData=mock(SessionCreationMetaData.class);
  SessionAccessMetaData accessMetaData=mock(SessionAccessMetaData.class);
  AtomicReference<Object> localContext=new AtomicReference<>();
  InfinispanSessionMetaData<Object> metaData=new InfinispanSessionMetaData<>(creationMetaData,accessMetaData,localContext);
  Object attributes=new Object();
  String id="id";
  when(this.metaDataFactory.createValue(id,null)).thenReturn(metaData);
  when(this.attributesFactory.createValue(id,null)).thenReturn(attributes);
  Map.Entry<InfinispanSessionMetaData<Object>,Object> result=this.factory.createValue(id,null);
  assertNotNull(result);
  assertSame(metaData,result.getKey());
  assertSame(attributes,result.getValue());
}
