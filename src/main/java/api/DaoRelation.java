package api;

import java.util.List;
import java.util.Optional;

public interface DaoRelation {

    public Optional<Object> getObjectByKeyIdAndListId(Integer key, Integer objectId);

}
