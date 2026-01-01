package com.paulcoding.fesamples.core.domain.repository

import com.paulcoding.fesamples.core.model.Post
import com.paulcoding.fesamples.core.network.datasource.KtorPostDataSource
import com.paulcoding.fesamples.core.network.model.NetworkPost
import com.paulcoding.fesamples.core.network.model.asExternalModel
import com.paulcoding.fesamples.core.network.util.apiCall

class HomeRepository(private val dataSource: KtorPostDataSource) {
    suspend fun getPost(): Result<List<Post>> = apiCall { dataSource.getPosts().map(NetworkPost::asExternalModel) }
}
