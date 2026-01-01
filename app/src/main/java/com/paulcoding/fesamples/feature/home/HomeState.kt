package com.paulcoding.fesamples.feature.home

import com.paulcoding.fesamples.core.model.Post

data class HomeState(
    val isFetchingPosts: Boolean = false,
    val posts: List<Post> = emptyList(),
    val error: Throwable? = null,
)