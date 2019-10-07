package com.foodora.repository.api

import com.foodora.repository.database.model.DbCatalog
import io.reactivex.Observable
import retrofit2.http.GET

interface CatalogService {

    @GET(ApiUrl.GET_CATALOG)
    fun getCatalog(): Observable<DbCatalog>

}