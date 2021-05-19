package com.polinema.movieapp.utils.api

data class Envelope<T>(var page: Int, var results: T, var total_pages: Int, var total_results: Int)