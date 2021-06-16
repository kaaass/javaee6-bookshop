package net.kaaass.bookshop.service.metadata;

import java8.util.function.Consumer;
import java8.util.function.Function;
import java8.util.function.Supplier;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.NoArgsConstructor;
import lombok.val;
import lombok.var;
import net.kaaass.bookshop.dao.entity.MetadataEntity;
import net.kaaass.bookshop.dao.entity.ProductMetadataEntity;
import net.kaaass.bookshop.dao.repository.MetadataRepository;
import net.kaaass.bookshop.dao.repository.ProductMetadataRepository;
import net.kaaass.bookshop.util.TimeUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Map;

@Stateless
public class MetadataManager {

    @Inject
    private MetadataRepository metadataRepository;

    @Inject
    private ProductMetadataRepository productMetadataRepository;

    public String get(String key, String defValue) {
        return metadataRepository.findByKey(key)
                .map(new Function<MetadataEntity, String>() {
                    @Override
                    public String apply(MetadataEntity metadataEntity) {
                        return metadataEntity.getValue();
                    }
                })
                .orElse(defValue);
    }

    public String get(String key) {
        return get(key, "");
    }

    public Map<String, String> getAll() {
        return StreamSupport.stream(metadataRepository.findAll())
                .collect(Collectors.toMap(new Function<MetadataEntity, String>() {
                    @Override
                    public String apply(MetadataEntity metadataEntity) {
                        return metadataEntity.getKey();
                    }
                }, new Function<MetadataEntity, String>() {
                    @Override
                    public String apply(MetadataEntity metadataEntity) {
                        return metadataEntity.getValue();
                    }
                }));
    }

    public void set(final String key, String value) {
        val metadata = metadataRepository.findByKey(key)
                .orElseGet(new Supplier<MetadataEntity>() {
                    @Override
                    public MetadataEntity get() {
                        val newEntity = new MetadataEntity();
                        newEntity.setKey(key);
                        return newEntity;
                    }
                });
        metadata.setValue(value);
        metadata.setLastUpdateTime(TimeUtils.nowTimestamp());
        metadataRepository.save(metadata);
    }

    /*
     * Product
     */

    public String getForProduct(String productId, String key, String defValue) {
        return productMetadataRepository.findByProductIdAndKey(productId, key)
                .map(new Function<ProductMetadataEntity, String>() {
                    @Override
                    public String apply(ProductMetadataEntity productMetadataEntity) {
                        return productMetadataEntity.getValue();
                    }
                })
                .orElse(defValue);
    }

    public String getForProduct(String productId, String key) {
        return getForProduct(productId, key, "");
    }

    public Map<String, String> getAllForProduct(String productId) {
        return StreamSupport.stream(productMetadataRepository.findAllByProductId(productId))
                .collect(Collectors.toMap(new Function<ProductMetadataEntity, String>() {
                    @Override
                    public String apply(ProductMetadataEntity productMetadataEntity) {
                        return productMetadataEntity.getKey();
                    }
                }, new Function<ProductMetadataEntity, String>() {
                    @Override
                    public String apply(ProductMetadataEntity productMetadataEntity) {
                        return productMetadataEntity.getValue();
                    }
                }));
    }

    public void setForProduct(final String productId, final String key, String value) {
        val metadata = productMetadataRepository.findByProductIdAndKey(productId, key)
                .orElseGet(new Supplier<ProductMetadataEntity>() {
                    @Override
                    public ProductMetadataEntity get() {
                        val newEntity = new ProductMetadataEntity();
                        newEntity.setProductId(productId);
                        newEntity.setKey(key);
                        return newEntity;
                    }
                });
        metadata.setValue(value);
        metadata.setLastUpdateTime(TimeUtils.nowTimestamp());
        productMetadataRepository.save(metadata);
    }

    public void deleteForProduct(String productId, String key) {
        productMetadataRepository.findByProductIdAndKey(productId, key)
                .ifPresent(new Consumer<ProductMetadataEntity>() {
                    @Override
                    public void accept(ProductMetadataEntity entity) {
                        productMetadataRepository.delete(entity);
                    }
                });
    }
}
