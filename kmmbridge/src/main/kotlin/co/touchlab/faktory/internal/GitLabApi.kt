/*
 * Copyright (c) 2024 Touchlab.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package co.touchlab.faktory.internal

import co.touchlab.faktory.findStringProperty
import org.gradle.api.Project
import java.net.URLEncoder

internal val Project.gitlabPublishTokenOrNull: String?
    get() = project.property("GITLAB_PUBLISH_TOKEN") as String?

internal val Project.gitlabPublishUser: String?
    get() = project.findStringProperty("GITLAB_PUBLISH_USER")

internal val Project.gitlabRepoOrNull: String?
    get() {
        val repo = project.findStringProperty("GITHUB_REPO") ?: return null
        // The GitLab API accepts repo id or url-encoded path
        val repoId = repo.toIntOrNull()
        return repoId?.toString() ?: URLEncoder.encode(repo, "UTF-8")
    }
