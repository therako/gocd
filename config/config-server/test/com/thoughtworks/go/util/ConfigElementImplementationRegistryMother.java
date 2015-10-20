/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.util;

import com.thoughtworks.go.config.registry.ConfigElementImplementationRegistrar;
import com.thoughtworks.go.config.registry.ConfigElementImplementationRegistry;
import com.thoughtworks.go.config.registry.NoPluginsInstalled;
import com.thoughtworks.go.plugin.infra.PluginManager;

import static org.mockito.Mockito.mock;

public class ConfigElementImplementationRegistryMother {
    public static ConfigElementImplementationRegistry withNoPlugins() {
        ConfigElementImplementationRegistry registry = new ConfigElementImplementationRegistry(new NoPluginsInstalled());
        new ConfigElementImplementationRegistrar(mock(PluginManager.class), registry).initialize();
        return registry;
    }
}
