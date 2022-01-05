@Test public void testGetType(){
  Resource resource=new ResourceImpl(Resource.Type.Cluster);
  Assert.assertEquals(Resource.Type.Cluster,resource.getType());
  resource=new ResourceImpl(Resource.Type.Service);
  Assert.assertEquals(Resource.Type.Service,resource.getType());
  resource=new ResourceImpl(Resource.Type.Host);
  Assert.assertEquals(Resource.Type.Host,resource.getType());
  resource=new ResourceImpl(Resource.Type.Component);
  Assert.assertEquals(Resource.Type.Component,resource.getType());
  resource=new ResourceImpl(Resource.Type.HostComponent);
  Assert.assertEquals(Resource.Type.HostComponent,resource.getType());
}
