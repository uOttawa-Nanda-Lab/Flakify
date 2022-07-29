/*
 * (C) Copyright IBM Corp. 2021.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.ibm.cloud.is.vpc.v1.model;

import com.google.gson.JsonParser;
import com.ibm.cloud.is.vpc.v1.model.DedicatedHostGroupPrototypeDedicatedHostByZoneContext;
import com.ibm.cloud.is.vpc.v1.model.DedicatedHostProfileIdentityByName;
import com.ibm.cloud.is.vpc.v1.model.DedicatedHostPrototypeDedicatedHostByZone;
import com.ibm.cloud.is.vpc.v1.model.ResourceGroupIdentityById;
import com.ibm.cloud.is.vpc.v1.model.ZoneIdentityByName;
import com.ibm.cloud.is.vpc.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the DedicatedHostPrototypeDedicatedHostByZone model.
 */
public class DedicatedHostPrototypeDedicatedHostByZoneTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testDedicatedHostPrototypeDedicatedHostByZone() throws Throwable {
    DedicatedHostProfileIdentityByName dedicatedHostProfileIdentityModel = new DedicatedHostProfileIdentityByName.Builder()
      .name("mx2-host-152x1216")
      .build();
    assertEquals(dedicatedHostProfileIdentityModel.name(), "mx2-host-152x1216");

    ResourceGroupIdentityById resourceGroupIdentityModel = new ResourceGroupIdentityById.Builder()
      .id("fee82deba12e4c0fb69c3b09d1f12345")
      .build();
    assertEquals(resourceGroupIdentityModel.id(), "fee82deba12e4c0fb69c3b09d1f12345");

    DedicatedHostGroupPrototypeDedicatedHostByZoneContext dedicatedHostGroupPrototypeDedicatedHostByZoneContextModel = new DedicatedHostGroupPrototypeDedicatedHostByZoneContext.Builder()
      .name("my-host-group")
      .resourceGroup(resourceGroupIdentityModel)
      .build();
    assertEquals(dedicatedHostGroupPrototypeDedicatedHostByZoneContextModel.name(), "my-host-group");
    assertEquals(dedicatedHostGroupPrototypeDedicatedHostByZoneContextModel.resourceGroup(), resourceGroupIdentityModel);

    ZoneIdentityByName zoneIdentityModel = new ZoneIdentityByName.Builder()
      .name("us-south-1")
      .build();
    assertEquals(zoneIdentityModel.name(), "us-south-1");

    DedicatedHostPrototypeDedicatedHostByZone dedicatedHostPrototypeDedicatedHostByZoneModel = new DedicatedHostPrototypeDedicatedHostByZone.Builder()
      .instancePlacementEnabled(true)
      .name("my-host")
      .profile(dedicatedHostProfileIdentityModel)
      .resourceGroup(resourceGroupIdentityModel)
      .group(dedicatedHostGroupPrototypeDedicatedHostByZoneContextModel)
      .zone(zoneIdentityModel)
      .build();
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModel.instancePlacementEnabled(), Boolean.valueOf(true));
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModel.name(), "my-host");
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModel.profile(), dedicatedHostProfileIdentityModel);
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModel.resourceGroup(), resourceGroupIdentityModel);
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModel.group(), dedicatedHostGroupPrototypeDedicatedHostByZoneContextModel);
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModel.zone(), zoneIdentityModel);

    String json = TestUtilities.serialize(dedicatedHostPrototypeDedicatedHostByZoneModel);

    DedicatedHostPrototypeDedicatedHostByZone dedicatedHostPrototypeDedicatedHostByZoneModelNew = TestUtilities.deserialize(json, DedicatedHostPrototypeDedicatedHostByZone.class);
    assertTrue(dedicatedHostPrototypeDedicatedHostByZoneModelNew instanceof DedicatedHostPrototypeDedicatedHostByZone);
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModelNew.instancePlacementEnabled(), Boolean.valueOf(true));
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModelNew.name(), "my-host");
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModelNew.profile().toString(), dedicatedHostProfileIdentityModel.toString());
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModelNew.resourceGroup().toString(), resourceGroupIdentityModel.toString());
    assertEquals(JsonParser.parseString(dedicatedHostPrototypeDedicatedHostByZoneModelNew.group().toString()), JsonParser.parseString(dedicatedHostGroupPrototypeDedicatedHostByZoneContextModel.toString()));
    assertEquals(dedicatedHostPrototypeDedicatedHostByZoneModelNew.zone().toString(), zoneIdentityModel.toString());
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDedicatedHostPrototypeDedicatedHostByZoneError() throws Throwable {
    new DedicatedHostPrototypeDedicatedHostByZone.Builder().build();
  }

}