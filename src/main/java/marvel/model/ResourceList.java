package marvel.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class ResourceList {
    private int available;
    private int returned;
    private String collectionURI;
    private List items;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
