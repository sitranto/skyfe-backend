package com.skyfe.middlewhare

import org.springframework.http.HttpStatus

class DataAlreadyExistsException(httpStatus: HttpStatus, message: String): Exception ()