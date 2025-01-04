package grsh.grdv.service

class ServiceCodeException(httpCode: String?, message: String?, cause: Throwable?) : Exception(message) {
    constructor(message: String) : this(null, message, null)
}