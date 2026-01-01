package com.paulcoding.fesamples.core.network.datasource

import com.paulcoding.fesamples.core.network.model.NetworkPost

interface PostDataSource {
    suspend fun getPosts(): List<NetworkPost>
}