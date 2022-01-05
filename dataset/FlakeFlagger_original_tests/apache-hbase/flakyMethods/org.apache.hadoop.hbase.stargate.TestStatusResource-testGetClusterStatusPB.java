public void testGetClusterStatusPB() throws IOException {
  Response response=client.get("/status/cluster",MIMETYPE_PROTOBUF);
  assertEquals(response.getCode(),200);
  StorageClusterStatusModel model=new StorageClusterStatusModel();
  model.getObjectFromMessage(response.getBody());
  validate(model);
}
