public class HttpRequest extends HttpMessage{

    private String method;
    private String requestType;
    private String httpVersion;

    HttpRequest(){

    }

    void setMethod(String method) {
        this.method = method;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}


