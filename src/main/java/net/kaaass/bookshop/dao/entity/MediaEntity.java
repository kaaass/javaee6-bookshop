package net.kaaass.bookshop.dao.entity;

import lombok.Data;
import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "media")
public class MediaEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "uploader_uid")
    private String uploaderUid;

    @OneToMany(mappedBy = "thumbnail", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<ProductEntity> products = new ArrayList<>();

    @Column(name = "upload_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Timestamp uploadTime;
}
