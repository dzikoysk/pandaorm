/*
 * Copyright (c) 2019-2020 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.orm.entity;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.panda_lang.orm.repository.RepositoryModel;

import java.io.IOException;

public final class EntityFactory {

    private static final EntityModelLoader ENTITY_SCHEME_LOADER = new EntityModelLoader();
    private static final EntityGenerator ENTITY_GENERATOR = new EntityGenerator();

    public EntityModel createEntityModel(Class<?> entityClass) {
        return ENTITY_SCHEME_LOADER.load(entityClass);
    }

    public Class<? extends DataEntity> generateEntityClass(RepositoryModel scheme) throws CannotCompileException, NotFoundException, IOException, ReflectiveOperationException {
        return ENTITY_GENERATOR.generate(scheme);
    }

}
