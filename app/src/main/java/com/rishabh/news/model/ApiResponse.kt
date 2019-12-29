package com.funtoolearn.model

class ApiResponse<T> {
    var message: String? = null
    var status: String? = null
    var articles: T? = null
    var code: String? = null
}