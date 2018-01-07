package com.tool.taxonomy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "findDrugsByNameStartWith",
                query = "from Drug d where d.name like CONCAT(:nameLike, '%') ORDER BY d.name",
                hints={@QueryHint(name="org.hibernate.cacheable", value="true"),
                        @QueryHint(name="org.hibernate.cacheMode", value="NORMAL"),}
        )
})
@Entity
@Table(name = "drugs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Drug extends Taxonomy {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Drug parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Drug> children = new HashSet<>();

    public Drug() {
        super();
    }

    public Drug(final Long id, final Long parentId, final String name) {
        super(id, parentId, name);
    }

    public Set<Drug> getChildren() {
        return children;
    }

    public void setChildren(final Set<Drug> children) {
        this.children = children;
    }

    public Drug getParent() {
        return parent;
    }

    public void setParent(final Drug parent) {
        this.parent = parent;
    }
}
