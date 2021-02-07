public enum HttpStatusCode {

    CLIENT_ERROR_400_BAD_REQUEST(400,"Bad Request"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(400,"Method Not Allowed"),
    CLIENT_ERROR_414_BAD_REQUEST(414,"URI To Long"),
    SERVER_ERROR_500_BAD_REQUEST(500,"Internal Server Error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501,"Not Implemented");


    public final int STATUS_CODE;
    public final String MESSAGE;

    HttpStatusCode( int STATUS_CODE, String MESSAGE){
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }
}
