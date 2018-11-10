package ming.abner.com.universallibrary.listener;


public interface HttpListener {
    void success(String data);
    void fail(String error);
}
