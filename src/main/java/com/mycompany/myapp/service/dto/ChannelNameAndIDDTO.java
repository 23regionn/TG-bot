package com.mycompany.myapp.service.dto;

import java.io.Serializable;

/**
 * A ChannelNameAndIDDTO.
 */
public class ChannelNameAndIDDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;

    public ChannelNameAndIDDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ChannelNameAndIDDTO name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ChannelNameAndIDDTO{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
