package com.skyfe.middlewhare

import org.springframework.http.HttpStatus

class DataNotFoundException(httpStatus: HttpStatus, message: String): Exception ()