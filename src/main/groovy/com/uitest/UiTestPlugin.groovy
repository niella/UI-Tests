/*
* Copyright 2022 the original author or authors.
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
*/
package com.uitest

import com.morpheusdata.core.Plugin
import com.morpheusdata.views.HandlebarsRenderer
import com.morpheusdata.model.Permission


class UiTestPlugin extends Plugin {

    @Override
    String getCode() {
        return 'uiTest'
    }

    @Override
    void initialize() {
        this.setName("UI Test")
        
		this.registerProvider(new UiTestGenericProvider(this,this.morpheus))
        this.setRenderer(new HandlebarsRenderer(this.classLoader))
        this.setPermissions([Permission.build('Test Controller','testController', [Permission.AccessType.none, Permission.AccessType.full])])
        this.controllers.add(new TestController(this, morpheus))
    }

    /**
     * Called when a plugin is being removed from the plugin manager (aka Uninstalled)
     */
    @Override
    void onDestroy() {
        //nothing to do for now
    }

    @Override
    public List<Permission> getPermissions() {
        Permission permission = new Permission('Test UI Plugin', 'testUIPlugin', [Permission.AccessType.full])
        return [permission];
    }
}
