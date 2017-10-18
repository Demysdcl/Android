package mobilesearch.com.br.alca_mobile.model;

/**
 * Created by LimaD01 on 31/01/2017.
 */


import java.io.Serializable;


public class SystemWrapper<T> implements Serializable{

    private String description;

    private T value;

    private String msg;

    public SystemWrapper() {
    }

    public SystemWrapper(String description, T value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return this.description;
    }

    public T getValue() {
        return this.value;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setMsg(String message) {
        this.msg = message;
    }


    @Override
    public String toString() {
        return "SystemWrapper{" +
                "description='" + description + '\'' +
                ", value=" + value +
                ", message='" + msg + '\'' +
                '}';
    }
}
