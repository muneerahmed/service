package com.learn.service.resource;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Customer {

    private Long id;
    private Name name;

    /**
     * Returns whether the {@link Customer} has the given id.
     *
     * @param id
     * @return
     */
    public boolean hasId(Long id) {

        return this.id.equals(id);
    }
}

