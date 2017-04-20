package marvel.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class CreatorList extends AbstractList {
    private List<CreatorSummary> items;

    public List<CreatorSummary> getItems() {
        return items;
    }

    public void setItems(List<CreatorSummary> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
