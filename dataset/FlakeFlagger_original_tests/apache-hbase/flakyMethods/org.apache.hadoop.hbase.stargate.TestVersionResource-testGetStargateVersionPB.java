public void testGetStargateVersionPB() throws IOException {
  Response response=client.get("/version",MIMETYPE_PROTOBUF);
  assertTrue(response.getCode() == 200);
  VersionModel model=new VersionModel();
  model.getObjectFromMessage(response.getBody());
  validate(model);
  LOG.info("success retrieving Stargate version as protobuf");
}
