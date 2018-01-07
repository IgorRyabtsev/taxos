package com.tool.taxonomy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "findPkparameterByNameStartWith",
                query = "from Pkparameter p where p.name like CONCAT(:nameLike, '%') ORDER BY p.name",
                hints={@QueryHint(name="org.hibernate.cacheable", value="true"),
                        @QueryHint(name="org.hibernate.cacheMode", value="NORMAL"),}
        )
})
@Entity
@Table(name = "pkparameters")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pkparameter extends Taxonomy {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Pkparameter parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Pkparameter> children = new HashSet<>();

    public Pkparameter() {
        super();
    }

    public Pkparameter(final Long id, final Long parentId, final String name) {
        super(id, parentId, name);
    }

    public Set<Pkparameter> getChildren() {
        return children;
    }

    public void setChildren(final Set<Pkparameter> children) {
        this.children = children;
    }

    public Pkparameter getParent() {
        return parent;
    }

    public void setParent(final Pkparameter parent) {
        this.parent = parent;
    }
}
