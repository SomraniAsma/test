package com.example.testmobileca.base

import com.example.testmobileca.retrofit.APIClient


abstract class BaseRepository(
    protected val apiClient: APIClient,
    )