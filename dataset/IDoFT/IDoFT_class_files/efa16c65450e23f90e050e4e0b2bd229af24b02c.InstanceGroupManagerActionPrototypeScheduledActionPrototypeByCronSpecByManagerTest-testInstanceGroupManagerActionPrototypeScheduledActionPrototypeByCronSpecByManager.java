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

import com.ibm.cloud.is.vpc.v1.model.InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager;
import com.ibm.cloud.is.vpc.v1.model.InstanceGroupManagerScheduledActionManagerPrototypeAutoScalePrototypeById;
import com.ibm.cloud.is.vpc.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager model.
 */
public class InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerTest {
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  @Test
  public void testInstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager() throws Throwable {
    InstanceGroupManagerScheduledActionManagerPrototypeAutoScalePrototypeById instanceGroupManagerScheduledActionManagerPrototypeModel = new InstanceGroupManagerScheduledActionManagerPrototypeAutoScalePrototypeById.Builder()
      .maxMembershipCount(Long.valueOf("10"))
      .minMembershipCount(Long.valueOf("10"))
      .id("1e09281b-f177-46fb-baf1-bc152b2e391a")
      .build();
    assertEquals(instanceGroupManagerScheduledActionManagerPrototypeModel.maxMembershipCount(), Long.valueOf("10"));
    assertEquals(instanceGroupManagerScheduledActionManagerPrototypeModel.minMembershipCount(), Long.valueOf("10"));
    assertEquals(instanceGroupManagerScheduledActionManagerPrototypeModel.id(), "1e09281b-f177-46fb-baf1-bc152b2e391a");

    InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModel = new InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager.Builder()
      .name("my-instance-group-manager-action")
      .cronSpec("*/5 1,2,3 * * *")
      .manager(instanceGroupManagerScheduledActionManagerPrototypeModel)
      .build();
    assertEquals(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModel.name(), "my-instance-group-manager-action");
    assertEquals(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModel.cronSpec(), "*/5 1,2,3 * * *");
    assertEquals(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModel.manager(), instanceGroupManagerScheduledActionManagerPrototypeModel);

    String json = TestUtilities.serialize(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModel);

    InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModelNew = TestUtilities.deserialize(json, InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager.class);
    assertTrue(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModelNew instanceof InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager);
    assertEquals(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModelNew.name(), "my-instance-group-manager-action");
    assertEquals(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModelNew.cronSpec(), "*/5 1,2,3 * * *");
    assertEquals(instanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerModelNew.manager().toString(), instanceGroupManagerScheduledActionManagerPrototypeModel.toString());
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManagerError() throws Throwable {
    new InstanceGroupManagerActionPrototypeScheduledActionPrototypeByCronSpecByManager.Builder().build();
  }

}