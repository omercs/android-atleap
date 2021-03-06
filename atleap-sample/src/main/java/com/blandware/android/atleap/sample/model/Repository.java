/*
 * Copyright (C) 2013 Blandware (http://www.blandware.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blandware.android.atleap.sample.model;

/**
 * Created by agrebnev on 22.12.13.
 */

import com.blandware.android.atleap.sample.provider.DefaultContract;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@DatabaseTable(tableName = DefaultContract.Repository.TABLE)
public class Repository {

    @JsonProperty("id")
    @DatabaseField(id = true, columnName = DefaultContract.Repository._ID)
    private int id;

    @JsonProperty("name")
    @DatabaseField(columnName = DefaultContract.Repository.NAME)
    public String name;

    @JsonProperty("full_name")
    @DatabaseField(columnName = DefaultContract.Repository.FULL_NAME)
    public String fullName;

    @JsonProperty("html_url")
    @DatabaseField(columnName = DefaultContract.Repository.HTML_URL)
    public String htmlUrl;

    @JsonProperty("description")
    @DatabaseField(columnName = DefaultContract.Repository.DESCRIPTION)
    public String description;

    @JsonProperty("stargazers_count")
    @DatabaseField(columnName = DefaultContract.Repository.STARGAZERS_COUNT)
    public int stargazersCount;

    @JsonProperty("created_at")
    @DatabaseField(columnName = DefaultContract.Repository.CREATED_AT)
    public Date createdAt;

    @JsonProperty("owner")
    private User owner;

    @DatabaseField(columnName = DefaultContract.Repository.OWNER_ID, useGetSet = true)
    private int ownerId = 0;

    public int getOwnerId() {
        if (ownerId == 0 && owner != null) {
            ownerId = owner.id;
        }
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @ForeignCollectionField(eager = false)
    private Collection<User> users;

    public Collection<User> getUsers() {
        if (users == null && owner != null) {
            users = new ArrayList<User>();
            users.add(owner);
        }
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    @DatabaseField(foreign = true)
    private RepositoriesResult result;


    @JsonIgnoreProperties(ignoreUnknown = true)
    @DatabaseTable(tableName = "repository_result")
    public static class RepositoriesResult {

        @DatabaseField(id = true)
        private int id = 0;

        @JsonProperty("total_count")
        @DatabaseField(columnName = "total_count")
        public int totalCount;

        @ForeignCollectionField(eager = false)
        private Collection<Repository> items;

        public Collection<Repository> getItems() {
            return items;
        }

        public void setItems(Collection<Repository> items) {
            this.items = items;
        }
    }


}
