package mobilesearch.com.br.alca_mobile.model;

import java.io.Serializable;

import lombok.SneakyThrows;

public abstract class BaseEntity<ID extends Serializable> implements
        Serializable, Comparable<BaseEntity<ID>> {

    public abstract ID getId();

    public String getDescription() {
        return "";
    }

    @Override
    public String toString() {
        return getId() + " " + getDescription();
    }

    @SneakyThrows
    public <T extends BaseEntity> T createEntity(Class<T> entity) {
        return entity.newInstance();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getId() == null || getClass() != obj.getClass()) {
            return false;
        }
        return getId().equals(((BaseEntity<ID>) obj).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public int compareTo(BaseEntity<ID> o) {
        if (o == null || getId() == null) {
            return -1;
        }
        if (getId() instanceof Number) {
            Long out = ((Number) o.getId()).longValue();
            Long in = ((Number) getId()).longValue();
            return in.compareTo(out);
        }
        return o.getId().toString().compareTo(getId().toString());
    }

}
