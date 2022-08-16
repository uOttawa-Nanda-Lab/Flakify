/*
 * Copyright (c) 2016 VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices
 * and license terms. Your use of these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package com.vmware.admiral.request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.vmware.admiral.adapter.common.VolumeOperationType;
import com.vmware.admiral.common.ManagementUriParts;
import com.vmware.admiral.compute.ResourceType;
import com.vmware.admiral.compute.container.CompositeComponentService.CompositeComponent;
import com.vmware.admiral.compute.container.CompositeDescriptionService.CompositeDescription;
import com.vmware.admiral.compute.container.ContainerDescriptionService.ContainerDescription;
import com.vmware.admiral.compute.container.ContainerService.ContainerState;
import com.vmware.admiral.compute.container.GroupResourcePlacementService.GroupResourcePlacementState;
import com.vmware.admiral.compute.container.volume.ContainerVolumeDescriptionService;
import com.vmware.admiral.compute.container.volume.ContainerVolumeDescriptionService.ContainerVolumeDescription;
import com.vmware.admiral.compute.container.volume.ContainerVolumeService;
import com.vmware.admiral.compute.container.volume.ContainerVolumeService.ContainerVolumeState;
import com.vmware.admiral.compute.container.volume.ContainerVolumeService.ContainerVolumeState.PowerState;
import com.vmware.admiral.request.RequestBrokerService.RequestBrokerState;
import com.vmware.admiral.request.RequestBrokerService.RequestBrokerState.SubStage;
import com.vmware.admiral.request.util.TestRequestStateFactory;
import com.vmware.photon.controller.model.resources.ComputeDescriptionService.ComputeDescription;
import com.vmware.photon.controller.model.resources.ComputeService.ComputeState;
import com.vmware.photon.controller.model.resources.ResourcePoolService.ResourcePoolState;
import com.vmware.xenon.common.Service;
import com.vmware.xenon.common.TaskState.TaskStage;
import com.vmware.xenon.common.UriUtils;

public class ContainerVolumeProvisionTaskServiceTest extends RequestBaseTest {

    @Test
    public void volumeProvisionAndDelete() throws Throwable {
        // Setup Docker host and resource pool.
        ResourcePoolState resourcePool = createResourcePool();
        ComputeDescription dockerHostDesc = createDockerHostDescription();

        ComputeState dockerHost1 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost1);

        // setup Group Placement:
        GroupResourcePlacementState groupPlacementState = createGroupResourcePlacement(
                resourcePool);

        ContainerVolumeDescription volumeDesc = new ContainerVolumeDescription();
        volumeDesc.name = "Postgres";
        volumeDesc.external = false;
        volumeDesc.customProperties = new HashMap<>();
        volumeDesc.customProperties.put("__containerHostId", Service.getId(dockerHost1.documentSelfLink));
        volumeDesc.instanceAdapterReference = UriUtils
                .buildUri(ManagementUriParts.ADAPTER_DOCKER_VOLUME);
        volumeDesc = doPost(volumeDesc, ContainerVolumeDescriptionService.FACTORY_LINK);

        // 1. Request a composite container:
        RequestBrokerState request = TestRequestStateFactory.createRequestState(
                ResourceType.VOLUME_TYPE.getName(), volumeDesc.documentSelfLink);

        request.tenantLinks = groupPlacementState.tenantLinks;
        host.log("########  Start of request ######## ");
        request = startRequest(request);

        // wait for request completed state:
        request = waitForRequestToComplete(request);

        String volumeLink = request.resourceLinks.iterator().next();
        ContainerVolumeState volume = getDocument(ContainerVolumeState.class, volumeLink);

        assertNotNull(volume);

        assertEquals(PowerState.CONNECTED, volume.powerState);
        assertEquals("local", volume.scope);

        request = TestRequestStateFactory.createRequestState(ResourceType.VOLUME_TYPE.getName(), null);
        request.tenantLinks = groupPlacementState.tenantLinks;
        request.resourceLinks = new HashSet<>();
        request.resourceLinks.add(volumeLink);
        request.operation = VolumeOperationType.DELETE.id;
        request = startRequest(request);

        // wait for request completed state:
        request = waitForRequestToComplete(request);
        assertEquals(SubStage.COMPLETED, request.taskSubStage);
        assertEquals(TaskStage.FINISHED, request.taskInfo.stage);

        volume = getDocumentNoWait(ContainerVolumeState.class, volumeLink);

        assertTrue(volume == null);
    }


    @Test
    public void testVolumeProvisioningTask() throws Throwable {

        String volumeName = "Posgres";
        ContainerVolumeDescription volumeDesc = TestRequestStateFactory
                .createContainerVolumeDescription(volumeName);
        volumeDesc.documentSelfLink = UUID.randomUUID().toString();

        // Create ContainerDescription with above volume.
        ContainerDescription container1Desc = TestRequestStateFactory.createContainerDescription();
        container1Desc.name = "container1";
        container1Desc.volumes = new String[] { volumeName + ":/etc/pgdata/postgres" };

        // Create another ContainerDescription without volume and placed it in different host.
        ContainerDescription container2Desc = TestRequestStateFactory.createContainerDescription();
        container2Desc.name = "container2";
        container2Desc.affinity = new String[] { "!container1:hard" };
        container2Desc.volumes = new String[] {};

        String container1DescLink = container1Desc.documentSelfLink;

        // Setup Docker host and resource pool.
        ResourcePoolState resourcePool = createResourcePool();
        ComputeDescription dockerHostDesc = createDockerHostDescription();

        ComputeState dockerHost1 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost1);

        ComputeState dockerHost2 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost2);

        CompositeDescription compositeDesc = createCompositeDesc(volumeDesc, container1Desc,
                container2Desc);
        assertNotNull(compositeDesc);
        assertEquals(3, compositeDesc.descriptionLinks.size());

        // setup Group Placement:
        GroupResourcePlacementState groupPlacementState = createGroupResourcePlacement(
                resourcePool);

        // 1. Request a composite container:
        RequestBrokerState request = TestRequestStateFactory.createRequestState(
                ResourceType.COMPOSITE_COMPONENT_TYPE.getName(), compositeDesc.documentSelfLink);

        request.tenantLinks = groupPlacementState.tenantLinks;
        host.log("########  Start of request ######## ");
        request = startRequest(request);

        // wait for request completed state:
        request = waitForRequestToComplete(request);

        CompositeComponent cc = getDocument(CompositeComponent.class,
                request.resourceLinks.iterator().next());

        assertNotNull(cc.componentLinks);
        assertEquals(cc.componentLinks.size(), 3);

        String volumeLink = null;
        String containerLink1 = null;
        String containerLink2 = null;

        Iterator<String> iterator = cc.componentLinks.iterator();

        while (iterator.hasNext()) {
            String link = iterator.next();
            if (link.startsWith(ContainerVolumeService.FACTORY_LINK)) {
                volumeLink = link;
            } else if (containerLink1 == null) {
                containerLink1 = link;
            } else {
                containerLink2 = link;
            }
        }

        ContainerState cont1 = getDocument(ContainerState.class, containerLink1);
        ContainerState cont2 = getDocument(ContainerState.class, containerLink2);
        assertNotNull(cont1);
        assertNotNull(cont2);

        assertTrue("Containers should be on different hosts.",
                !cont1.parentLink.equals(cont2.parentLink));

        ContainerVolumeState volume = getDocument(ContainerVolumeState.class, volumeLink);
        assertNotNull(volume);
        assertEquals(PowerState.CONNECTED, volume.powerState);
        assertEquals("local", volume.scope);
        String volumeDescProp = volume.customProperties.get("volume propKey string");
        assertNotNull(volumeDescProp);
        assertEquals("volume customPropertyValue string", volumeDescProp);

        // Volume must be provisioned on same host where ContainerDesc with volume is.
        if (cont1.descriptionLink.equals(container1DescLink)) {
            assertTrue("Volume is provisioned on wrong host.",
                    volume.originatingHostLink.equals(cont1.parentLink));

            assertEquals(cont1.volumes.length, 1);
            assertTrue("Host volume name is different than Container volume name.",
                    cont1.volumes[0].contains(volume.name));
        } else {
            assertTrue("Volume is provisioned on wrong host.",
                    volume.originatingHostLink.equals(cont2.parentLink));
            assertEquals(cont2.volumes.length, 1);
            assertTrue("Host volume name is different than Container volume name.",
                    cont2.volumes[0].contains(volume.name));
        }
    }

    @Test
    public void testVolumeProvisioningTaskWithSoftAntiaffinity() throws Throwable {

        String volumeName = "Posgres";
        ContainerVolumeDescription volumeDesc = TestRequestStateFactory
                .createContainerVolumeDescription(volumeName);
        volumeDesc.documentSelfLink = UUID.randomUUID().toString();

        // Create ContainerDescription with above volume.
        ContainerDescription container1Desc = TestRequestStateFactory.createContainerDescription();
        container1Desc.name = "container1";
        container1Desc.volumes = new String[] { volumeName + ":/etc/pgdata/postgres" };

        // Create another ContainerDescription with same local volume and try to place it in
        // different host (soft antiaffinity).
        ContainerDescription container2Desc = TestRequestStateFactory.createContainerDescription();
        container2Desc.name = "container2";
        container2Desc.affinity = new String[] { "!container1:soft" };
        container2Desc.volumes = new String[] { volumeName + ":/etc/pgdata/postgres" };

        // Setup 2 Docker hosts and resource pool.
        ResourcePoolState resourcePool = createResourcePool();
        ComputeDescription dockerHostDesc = createDockerHostDescription();

        ComputeState dockerHost1 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost1);

        ComputeState dockerHost2 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost2);

        CompositeDescription compositeDesc = createCompositeDesc(volumeDesc, container1Desc,
                container2Desc);
        assertNotNull(compositeDesc);
        assertEquals(3, compositeDesc.descriptionLinks.size());

        // setup Group Placement:
        GroupResourcePlacementState groupPlacementState = createGroupResourcePlacement(
                resourcePool);

        // 1. Request a composite container:
        RequestBrokerState request = TestRequestStateFactory.createRequestState(
                ResourceType.COMPOSITE_COMPONENT_TYPE.getName(), compositeDesc.documentSelfLink);

        request.tenantLinks = groupPlacementState.tenantLinks;
        host.log("########  Start of request ######## ");
        request = startRequest(request);

        // wait for request completed state:
        request = waitForRequestToComplete(request);

        CompositeComponent cc = getDocument(CompositeComponent.class,
                request.resourceLinks.iterator().next());

        assertNotNull(cc.componentLinks);
        assertEquals(cc.componentLinks.size(), 3);

        String volumeLink = null;
        String containerLink1 = null;
        String containerLink2 = null;

        Iterator<String> iterator = cc.componentLinks.iterator();

        while (iterator.hasNext()) {
            String link = iterator.next();
            if (link.startsWith(ContainerVolumeService.FACTORY_LINK)) {
                volumeLink = link;
            } else if (containerLink1 == null) {
                containerLink1 = link;
            } else {
                containerLink2 = link;
            }
        }

        ContainerState cont1 = getDocument(ContainerState.class, containerLink1);
        ContainerState cont2 = getDocument(ContainerState.class, containerLink2);
        assertNotNull(cont1);
        assertNotNull(cont2);

        assertTrue("Containers should be on the same host.",
                cont1.parentLink.equals(cont2.parentLink));

        ContainerVolumeState volume = getDocument(ContainerVolumeState.class, volumeLink);
        String volumeDescProp = volume.customProperties.get("volume propKey string");
        assertNotNull(volumeDescProp);
        assertEquals("volume customPropertyValue string", volumeDescProp);

        // Volume must be provisioned on same host where both ContainerDesc with volume are.
        assertTrue("Volume is provisioned on wrong host.",
                volume.originatingHostLink.equals(cont1.parentLink));
        assertEquals(cont1.volumes.length, 1);
        assertTrue("Host volume name is different than Container volume name.",
                cont1.volumes[0].contains(volume.name));

        assertTrue("Volume is provisioned on wrong host.",
                volume.originatingHostLink.equals(cont2.parentLink));
        assertEquals(cont2.volumes.length, 1);
        assertTrue("Host volume name is different than Container volume name.",
                cont2.volumes[0].contains(volume.name));
    }

    @Test
    public void testVolumeProvisioningTaskWithProvidedHostIds() throws Throwable {

        ContainerVolumeDescription volumeDesc = TestRequestStateFactory
                .createContainerVolumeDescription("My-Vol");
        volumeDesc.documentSelfLink = UUID.randomUUID().toString();
        volumeDesc = doPost(volumeDesc, ContainerVolumeDescriptionService.FACTORY_LINK);

        ComputeDescription dockerHostDesc = createDockerHostDescription();
        if (dockerHostDesc.customProperties == null) {
            dockerHostDesc.customProperties = new HashMap<>();
        }

        ComputeState dockerHost1 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost1);

        ComputeState dockerHost2 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost2);

        RequestBrokerState request = TestRequestStateFactory.createRequestState(
                ResourceType.VOLUME_TYPE.getName(), volumeDesc.documentSelfLink);

        String hostIds = dockerHost1.id + "," + dockerHost2.id;
        request.customProperties = new HashMap<>();
        request.customProperties
                .put(ReservationAllocationTaskService.CONTAINER_HOST_ID_CUSTOM_PROPERTY, hostIds);

        host.log("########  Start of request ######## ");
        request = startRequest(request);

        // wait for request completed state:
        request = waitForRequestToComplete(request);

        Set<String> volumeLinks = request.resourceLinks;
        Iterator<String> volumeLinkIterator = volumeLinks.iterator();
        ContainerVolumeState vol1 = getDocument(ContainerVolumeState.class,
                volumeLinkIterator.next());
        ContainerVolumeState vol2 = getDocument(ContainerVolumeState.class,
                volumeLinkIterator.next());

        assertNotNull(vol1);
        assertNotNull(vol2);

        List<String> hostLinks = Arrays.asList(dockerHost1.documentSelfLink,
                dockerHost2.documentSelfLink);

        // Volumes are provisioned on the provided hosts
        assertTrue(hostLinks.contains(vol1.originatingHostLink));
        assertTrue(hostLinks.contains(vol2.originatingHostLink));
        assertFalse(vol1.originatingHostLink.equals(vol2.originatingHostLink));
    }

    @Test
    public void testVolumeProvisioningTaskWithDefaultDriver() throws Throwable {

        String volumeName = "Posgres";
        ContainerVolumeDescription volumeDesc = TestRequestStateFactory
                .createContainerVolumeDescription(volumeName);
        volumeDesc.documentSelfLink = UUID.randomUUID().toString();

        volumeDesc.driver = null; // no driver -> default driver

        // Create ContainerDescription with above volume.
        ContainerDescription container1Desc = TestRequestStateFactory.createContainerDescription();
        container1Desc.name = "container1";
        container1Desc.volumes = new String[] { volumeName + ":/etc/pgdata/postgres" };

        // Setup Docker host and resource pool.
        ResourcePoolState resourcePool = createResourcePool();
        ComputeDescription dockerHostDesc = createDockerHostDescription();

        ComputeState dockerHost1 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost1);

        ComputeState dockerHost2 = createDockerHost(dockerHostDesc, resourcePool, true);
        addForDeletion(dockerHost2);

        CompositeDescription compositeDesc = createCompositeDesc(volumeDesc, container1Desc);
        assertNotNull(compositeDesc);
        assertEquals(2, compositeDesc.descriptionLinks.size());

        // setup Group Placement:
        GroupResourcePlacementState groupPlacementState = createGroupResourcePlacement(
                resourcePool);

        // 1. Request a composite container:
        RequestBrokerState request = TestRequestStateFactory.createRequestState(
                ResourceType.COMPOSITE_COMPONENT_TYPE.getName(), compositeDesc.documentSelfLink);

        request.tenantLinks = groupPlacementState.tenantLinks;
        host.log("########  Start of request ######## ");
        request = startRequest(request);

        // wait for request completed state:
        request = waitForRequestToComplete(request);

        CompositeComponent cc = getDocument(CompositeComponent.class,
                request.resourceLinks.iterator().next());

        assertNotNull(cc.componentLinks);
        assertEquals(cc.componentLinks.size(), 2);

        String volumeLink = null;
        String containerLink1 = null;

        Iterator<String> iterator = cc.componentLinks.iterator();

        while (iterator.hasNext()) {
            String link = iterator.next();
            if (link.startsWith(ContainerVolumeService.FACTORY_LINK)) {
                volumeLink = link;
            } else if (containerLink1 == null) {
                containerLink1 = link;
            }
        }

        ContainerState cont1 = getDocument(ContainerState.class, containerLink1);
        assertNotNull(cont1);

        ContainerVolumeState volume = getDocument(ContainerVolumeState.class, volumeLink);
        String volumeDescProp = volume.customProperties.get("volume propKey string");
        assertNotNull(volumeDescProp);
        assertEquals("volume customPropertyValue string", volumeDescProp);

        // Volume must be provisioned on same host where ContainerDesc with volume is.
        assertTrue("Volume is provisioned on wrong host.",
                volume.originatingHostLink.equals(cont1.parentLink));

        assertEquals(cont1.volumes.length, 1);
        assertTrue("Host volume name is different than Container volume name.",
                cont1.volumes[0].contains(volume.name));
    }

}
