package com.example.agentportalbackend.model;
import com.example.agentportalbackend.enums.DocumnetType;

import javax.persistence.*;

@Entity
@Table(name = "educational_info")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private DocumnetType docType;

    @Lob
    @Column(nullable = false)
    private byte[] data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DocumnetType getDocType() {
        return docType;
    }

    public void setDocType(DocumnetType docType) {
        this.docType = docType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
